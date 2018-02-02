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
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.progresssoft.datawarehouse.to.DealInValidDataWSTO;
import com.progresssoft.datawarehouse.to.ResponseWSTO;
import com.progresssoft.datawarehouse.utills.ProgressSoftConstant;
import com.progresssoft.datawarehouse.utills.ProgressSoftUtil;

/**
 * @author s727953
 *
 */
@Service
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

	List<AccumulativeDetailsEntity> accumulativeDeals = new ArrayList<>();
	List<DealInValidDataEntity> dealInValidDataLst = new ArrayList<>();

	@Override
	public String checkFileStatus(String fileName) {
		LOGGER.debug("Invoked :  checkFileStatus()");
		UploadedFileEntity fileDetails = uploadedFileRepository.findByFileName(fileName);
		if (null == fileDetails) {
			return ProgressSoftConstant.FILENOTFOUND;
		}
		LOGGER.debug("Exit :  checkFileStatus() ");
		return ProgressSoftConstant.FILEFOUND;
	}

	@Override
	public UploadedFileEntity saveFileDetails(String uploadFileName) {
		LOGGER.debug("Invoked :  saveFileUploadFileInfo()");
		UploadedFileEntity uploadedFile = new UploadedFileEntity(uploadFileName);
		uploadedFile = uploadedFileRepository.save(uploadedFile);
		LOGGER.debug("Exit :  saveFileUploadFileInfo()");
		return uploadedFile;
	}

	@Override
	public List<ResponseWSTO> saveDealData(MultipartFile file, UploadedFileEntity fileEntity) throws IOException {
		LOGGER.debug("Invoked :  saveDealData() ");
		fileEntity = parseUploadedCsvToDTO(file, fileEntity);
		ResponseWSTO target = new ResponseWSTO();
		BeanUtils.copyProperties(fileEntity, target);
		List<ResponseWSTO> list = new ArrayList<>();
		list.add(target);
		LOGGER.debug("exit :  saveDealData() ");
		return list;

	}

	@Transactional(readOnly = false, noRollbackFor = ConstraintViolationException.class, propagation = Propagation.REQUIRES_NEW)
	public UploadedFileEntity parseUploadedCsvToDTO(MultipartFile file, UploadedFileEntity fileEntity)
			throws IOException {
		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.debug("Invoked :  parseUploadedCsvToDTO()  . ");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
			Pattern pattern = Pattern.compile(",");

			List<DealInValidDataWSTO> uploadedDealList = in.lines().skip(1).map(tmpLine -> {
				String[] objLine = pattern.split(tmpLine);
				DealInValidDataWSTO wsTO = new DealInValidDataWSTO(fileEntity.getFileId());
				int columnIdx = 0;
				for (String tmpData : objLine) {
					if (columnIdx == 0) {
						wsTO.setDealUniqueId(tmpData);
					}
					if (columnIdx == 1) {
						wsTO.setFromCurrency(tmpData);
					}
					if (columnIdx == 2) {
						wsTO.setToCurrency(tmpData);
					}
					if (columnIdx == 3) {
						wsTO.setDealTimeStamp(tmpData);
					}
					if (columnIdx == 4) {
						wsTO.setDealAmount(tmpData);
					}
					columnIdx++;
				}
				return wsTO;
			}).collect(Collectors.toList());

			Map<String, Long> frequencyCount = uploadedDealList.stream()
					.collect(Collectors.groupingBy(DealInValidDataWSTO::getFromCurrency, Collectors.counting()));

			long validSecond1 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			uploadedDealList.stream().forEach(dealObj -> {
				try {
					DealValidDataEntity tmpValid = new DealValidDataEntity();
					BeanUtils.copyProperties(dealObj, tmpValid);
					if (null != dealObj.getDealTimeStamp() && !dealObj.getDealTimeStamp().isEmpty()) {
						tmpValid.setDealTimeStamp(ProgressSoftUtil.convertStringToDate(dealObj.getDealTimeStamp()));
					}
					dealValidDataRepository.save(tmpValid);

				} catch (ConstraintViolationException e) {
					DealInValidDataEntity tmpInValid = new DealInValidDataEntity();
					BeanUtils.copyProperties(dealObj, tmpInValid);
					dealInValidDataLst.add(tmpInValid);
				}
			});
			long validSecond2 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			LOGGER.debug("Validaiton Time {}", validSecond2 - validSecond1);

			long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			LOGGER.warn("Time taken {} s", (endSecond - startSecond));
			LOGGER.debug("Exit :  parseUploadedCsvToDTO() ");

			frequencyCount.forEach((key, val) -> accumulativeDeals.add(new AccumulativeDetailsEntity(key, val)));

			fileEntity.setNoOfTotalDeal(uploadedDealList.size());
			fileEntity.setNoOfInValidDeal(dealInValidDataLst.size());
			fileEntity.setProcessingTime((endSecond - startSecond));
			uploadedFileRepository.save(fileEntity);

		}
		return fileEntity;
	}

	@Override
	@Transactional(readOnly = true)
	public void bulkSaveInValidDealData() {
		LOGGER.debug("Invoked :  bulkSaveAccumulativeDealsEntity()");
		dealInValidDataRepository.bulkSaveInValidDealData(dealInValidDataLst);
		LOGGER.debug("Exit :  bulkSaveAccumulativeDealsEntity()");

	}

	@Override
	@Transactional(readOnly = true)
	public void bulkSaveAccumulativeDealsEntity() {
		LOGGER.debug("Invoked :  bulkSaveAccumulativeDealsEntity()");
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
		LOGGER.debug("Exit :  bulkSaveAccumulativeDealsEntity()");

	}

	@Override
	public List<ResponseWSTO> fileSummaryByFileName(String fileName) {
		LOGGER.debug("Invoked :  fileSummaryByFileName()");
		List<UploadedFileEntity> fileEntityLst = uploadedFileRepository.findByFileNameContaining(fileName);
		List<ResponseWSTO> wsTOLst = new ArrayList<>();
		fileEntityLst.stream().forEach(file -> {
			ResponseWSTO wsto = new ResponseWSTO();
			BeanUtils.copyProperties(file, wsto);
			wsTOLst.add(wsto);
		});
		LOGGER.debug("Exit :  fileSummaryByFileName()");
		return wsTOLst;

	}

}
