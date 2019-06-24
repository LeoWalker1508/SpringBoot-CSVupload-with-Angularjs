/**
 * 
 */
package com.everythingisdata.datawarehouse.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

import com.everythingisdata.datawarehouse.entity.AccumulativeDealsEntity;
import com.everythingisdata.datawarehouse.entity.DealInValidDataEntity;
import com.everythingisdata.datawarehouse.entity.DealValidDataEntity;
import com.everythingisdata.datawarehouse.entity.UploadedFileEntity;
import com.everythingisdata.datawarehouse.repository.AccumulativeDealsRepository;
import com.everythingisdata.datawarehouse.repository.DealInValidDataRepository;
import com.everythingisdata.datawarehouse.repository.DealValidDataRepository;
import com.everythingisdata.datawarehouse.repository.UploadedFileRepository;
import com.everythingisdata.datawarehouse.services.DataWarehouseService;
import com.everythingisdata.datawarehouse.to.ResponseWSTO;
import com.everythingisdata.datawarehouse.utills.EveryThingIsDataConstant;
import com.everythingisdata.datawarehouse.utills.EveryThingIsDataUtil;

/**
 * @author everythingisdata
 *
 */
@Service
@Validated
@Transactional
public class DataWarehouseServiceImpl implements DataWarehouseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataWarehouseServiceImpl.class);

	@Autowired
	private UploadedFileRepository uploadedFileRepository;

	@Autowired
	private DealValidDataRepository dealValidDataRepository;

	@Autowired
	private DealInValidDataRepository dealInValidDataRepository;

	@Autowired
	private AccumulativeDealsRepository accumulativeDealsRepository;

	@Autowired
	private Validator validator;

	List<AccumulativeDealsEntity> accumulativeDeals = new ArrayList<>();

	@Override
	public String checkFileStatus(String fileName) {
		LOGGER.info("Invoked :  checkFileStatus()");
		if (null == uploadedFileRepository.findByFileName(fileName)) {
			return EveryThingIsDataConstant.FILENOTFOUND;
		}
		LOGGER.info("Exit :  checkFileStatus() ");
		return EveryThingIsDataConstant.FILEFOUND;
	}

	@Override
	@Transactional(readOnly = false)
	public UploadedFileEntity saveFileDetails(String uploadFileName) {
		LOGGER.info("Invoked :  saveFileInfo()");
		UploadedFileEntity uploadedFile = uploadedFileRepository.save(new UploadedFileEntity(uploadFileName));
		LOGGER.info("Exit    :  saveFileInfo()");
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
		LOGGER.info("Exit   :  saveDealData() ");
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
			List<DealValidDataEntity> uploadedDealList = in.lines().skip(1).map(tmpLine -> {
				DealValidDataEntity dataEntity = new DealValidDataEntity(fileEntity.getFileId());

				int columnIdx = 0;
				for (String tmpData : tmpLine.split(",")) {

					if (columnIdx == 0) {
						dataEntity.setDealUniqueId(tmpData);
					} else if (columnIdx == 1) {
						dataEntity.setFromCurrency(tmpData);
					} else if (columnIdx == 2) {
						dataEntity.setToCurrency(tmpData);
					} else if (columnIdx == 3) {
						dataEntity.setDealTimeStamp(EveryThingIsDataUtil.convertStringToDate(tmpData));
					} else if (columnIdx == 4) {
						dataEntity.setDealAmount(tmpData);
					}
					columnIdx++;
				}

				if (validator.validate(dataEntity).isEmpty()) {
					dealValidDataLst.add(dataEntity);
				} else {
					DealInValidDataEntity tmpInValid = new DealInValidDataEntity();
					BeanUtils.copyProperties(dataEntity, tmpInValid);
					dealInValidDataLst.add(tmpInValid);
				}

				return dataEntity;

			}).collect(Collectors.toList());

			long endSecond1 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			dealValidDataRepository.bulkSaveValidDealData(dealValidDataLst);

			dealInValidDataRepository.bulkSaveInValidDealData(dealInValidDataLst);
			long endSecond2 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			LOGGER.warn("bulkSaveInValidDealData Processing Time  {} s", (endSecond2 - endSecond1));

			Map<String, Long> frequencyCount = uploadedDealList.stream()
					.collect(Collectors.groupingBy(DealValidDataEntity::getFromCurrency, Collectors.counting()));

			long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			LOGGER.warn("Processing Time  {} s", (endSecond - startSecond));
			LOGGER.info("Exit :  parseUploadedCsvToDTO() ");

			frequencyCount.forEach((key, val) -> accumulativeDeals.add(new AccumulativeDealsEntity(key, val)));

			fileEntity.setNoOfTotalDeal(uploadedDealList.size());
			fileEntity.setNoOfInValidDeal(dealInValidDataLst.size());
			fileEntity.setProcessingTime((endSecond - startSecond));
			uploadedFileRepository.save(fileEntity);

		}
		return fileEntity;

	}

	@Override
	@Transactional(readOnly = false)
	public void bulkSaveAccumulativeDealsEntity() {
		LOGGER.info("Invoked :  bulkSaveAccumulativeDealsEntity()");
		accumulativeDeals.addAll(accumulativeDealsRepository.findAll());
		List<AccumulativeDealsEntity> tmp = new ArrayList<>();
		accumulativeDealsRepository.deleteAll();

		accumulativeDeals.stream()
				.collect(Collectors.groupingBy(AccumulativeDealsEntity::getCurrencyIsoCode,
						Collectors.summingLong(AccumulativeDealsEntity::getDealsCount)))
				.forEach((key, val) -> tmp.add(new AccumulativeDealsEntity(key, val)));

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
