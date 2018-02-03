/**
 * 
 */
package com.progresssoft.datawarehouse.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.datawarehouse.entity.AccumulativeDetailsEntity;
import com.progresssoft.datawarehouse.entity.DealInValidDataEntity;
import com.progresssoft.datawarehouse.entity.DealValidDataEntity;
import com.progresssoft.datawarehouse.entity.UploadedFileEntity;
import com.progresssoft.datawarehouse.repository.AccumulativeDealsRepository;
import com.progresssoft.datawarehouse.repository.DealInValidDataRepository;
import com.progresssoft.datawarehouse.repository.DealValidDataRepository;
import com.progresssoft.datawarehouse.repository.UploadedFileRepository;
import com.progresssoft.datawarehouse.services.DataWarehouseService;
import com.progresssoft.datawarehouse.to.ResponseWSTO;
import com.progresssoft.datawarehouse.utills.ProgressSoftConstant;
import com.progresssoft.datawarehouse.utills.ProgressSoftUtil;

/**
 * @author s727953
 *
 */
@Service
@Validated
@Transactional
public class DataWarehouseServiceImpl implements DataWarehouseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataWarehouseServiceImpl.class);

	@Autowired
	private DealValidDataRepository dealValidDataRepository;

	@Autowired
	private DealInValidDataRepository dealInValidDataRepository;

	@Autowired
	private UploadedFileRepository uploadedFileRepository;

	@Autowired
	private AccumulativeDealsRepository accumulativeDealsRepository;

	@Autowired
	private Validator validator;

	List<AccumulativeDetailsEntity> accumulativeDeals = new ArrayList<>();

	@Override
	public String checkFileStatus(String fileName) {
		LOGGER.info("Invoked :  checkFileStatus()");
		UploadedFileEntity fileDetails = uploadedFileRepository.findByFileName(fileName);
		if (null == fileDetails) {
			return ProgressSoftConstant.FILENOTFOUND;
		}
		LOGGER.info("Exit :  checkFileStatus() ");
		return ProgressSoftConstant.FILEFOUND;
	}

	@Override
	@Transactional(readOnly = false)
	public UploadedFileEntity saveFileDetails(String uploadFileName) {
		LOGGER.info("Invoked :  saveFileUploadFileInfo()");
		UploadedFileEntity uploadedFile = new UploadedFileEntity(uploadFileName);
		uploadedFile = uploadedFileRepository.save(uploadedFile);
		LOGGER.info("Exit :  saveFileUploadFileInfo()");
		return uploadedFile;
	}

	@Override
	public List<ResponseWSTO> saveDealData(MultipartFile file, UploadedFileEntity fileEntity) throws IOException {
		LOGGER.info("Invoked :  saveDealData() ");
		fileEntity = parseUploadedCsvToDTO(file, fileEntity);
		ResponseWSTO target = new ResponseWSTO();
		BeanUtils.copyProperties(fileEntity, target);
		List<ResponseWSTO> list = new ArrayList<>();
		list.add(target);
		LOGGER.info("exit :  saveDealData() ");
		return list;

	}

	@Transactional(readOnly = false, noRollbackFor = ConstraintViolationException.class, propagation = Propagation.REQUIRES_NEW)
	public UploadedFileEntity parseUploadedCsvToDTO(MultipartFile file, UploadedFileEntity fileEntity)
			throws IOException {
		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.info("Invoked :  parseUploadedCsvToDTO()  . ");

		List<DealInValidDataEntity> dealInValidDataLst = new ArrayList<>();
		List<DealValidDataEntity> dealValidDataLst = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
			Pattern pattern = Pattern.compile(",");

			List<DealValidDataEntity> uploadedDealList = in.lines().skip(1).map(tmpLine -> {
				String[] objLine = pattern.split(tmpLine);
				DealValidDataEntity dataEntity = new DealValidDataEntity(fileEntity.getFileId());
				int columnIdx = 0;
				for (String tmpData : objLine) {
					if (columnIdx == 0) {
						dataEntity.setDealUniqueId(tmpData);
					}
					if (columnIdx == 1) {
						dataEntity.setFromCurrency(tmpData);
					}
					if (columnIdx == 2) {
						dataEntity.setToCurrency(tmpData);
					}
					if (columnIdx == 3) {
						if (null != tmpData && !tmpData.isEmpty()) {
							dataEntity.setDealTimeStamp(ProgressSoftUtil.convertStringToDate(tmpData));
						}
					}
					if (columnIdx == 4) {
						dataEntity.setDealAmount(tmpData);
					}
					columnIdx++;
				}

				Set<ConstraintViolation<DealValidDataEntity>> violations = validator.validate(dataEntity);

				if (violations.isEmpty()) {
					dealValidDataLst.add(dataEntity);
				} else {
					DealInValidDataEntity tmpInValid = new DealInValidDataEntity();
					BeanUtils.copyProperties(dataEntity, tmpInValid);
					dealInValidDataLst.add(tmpInValid);
				}

				return dataEntity;

			}).collect(Collectors.toList());

			long validSecond1 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			LOGGER.info("Invoked :  validSecond1>>>() >>>{} ", (validSecond1 - validSecond1),
					dealInValidDataLst.size());

			dealValidDataRepository.bulkSaveValidDealData(dealValidDataLst);

			long validSecond11 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			LOGGER.info("Invoked :  validSecond11() >>>{} ", (validSecond1 - validSecond11));

			dealInValidDataRepository.bulkSaveInValidDealData(dealInValidDataLst);

			Map<String, Long> frequencyCount = uploadedDealList.stream()
					.collect(Collectors.groupingBy(DealValidDataEntity::getFromCurrency, Collectors.counting()));

			//
			// uploadedDealList.stream().forEach(dealObj -> {
			// try {
			// dealValidDataRepository.save(dealObj);
			// } catch (Exception e) {
			// DealInValidDataEntity tmpInValid = new DealInValidDataEntity();
			// BeanUtils.copyProperties(dealObj, tmpInValid);
			// dealInValidDataLst.add(tmpInValid);
			// }
			// });
			long validSecond2 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			LOGGER.info("Validaiton Time {}", validSecond2 - validSecond1);

			long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			LOGGER.warn("Time taken {} s", (endSecond - startSecond));
			LOGGER.info("Exit :  parseUploadedCsvToDTO() ");

			frequencyCount.forEach((key, val) -> accumulativeDeals.add(new AccumulativeDetailsEntity(key, val)));

			fileEntity.setNoOfTotalDeal(uploadedDealList.size());
			fileEntity.setNoOfInValidDeal(dealInValidDataLst.size());
			fileEntity.setProcessingTime((endSecond - startSecond));
			uploadedFileRepository.save(fileEntity);

		}
		return fileEntity;

	}

	@Override
	@Transactional(readOnly = false)
	public void bulkSaveInValidDealData() {
		LOGGER.info("Invoked :  bulkSaveAccumulativeDealsEntity()");
		// dealInValidDataRepository.bulkSaveInValidDealData(dealInValidDataLst);
		LOGGER.info("Exit :  bulkSaveAccumulativeDealsEntity()");

	}

	@Override
	@Transactional(readOnly = false)
	public void bulkSaveAccumulativeDealsEntity() {
		LOGGER.info("Invoked :  bulkSaveAccumulativeDealsEntity()");
		LOGGER.info(" {}", accumulativeDeals);
		accumulativeDeals.addAll(accumulativeDealsRepository.findAll());

		List<AccumulativeDetailsEntity> tmp = new ArrayList<>();
		accumulativeDealsRepository.deleteAll();

		accumulativeDeals.stream()
				.collect(Collectors.groupingBy(AccumulativeDetailsEntity::getCurrencyIsoCode,
						Collectors.summingLong(AccumulativeDetailsEntity::getDealsCount)))
				.forEach((key, val) -> tmp.add(new AccumulativeDetailsEntity(key, val)));

		LOGGER.info(" {}", tmp);
		accumulativeDealsRepository.bulkSaveAccumulativeDealsEntity(tmp);
		LOGGER.info("Exit :  bulkSaveAccumulativeDealsEntity()");

	}

	@Override
	public List<ResponseWSTO> fileSummaryByFileName(String fileName) {
		LOGGER.info("Invoked :  fileSummaryByFileName()");
		List<UploadedFileEntity> fileEntityLst = uploadedFileRepository.findByFileNameContaining(fileName);
		List<ResponseWSTO> wsTOLst = new ArrayList<>();
		fileEntityLst.stream().forEach(file -> {
			ResponseWSTO wsto = new ResponseWSTO();
			BeanUtils.copyProperties(file, wsto);
			wsTOLst.add(wsto);
		});
		LOGGER.info("Exit :  fileSummaryByFileName()");
		return wsTOLst;

	}

}
