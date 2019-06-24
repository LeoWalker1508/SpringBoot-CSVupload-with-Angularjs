package com.everythingisdata.datawarehouse.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.everythingisdata.datawarehouse.entity.UploadedFileEntity;
import com.everythingisdata.datawarehouse.services.DataWarehouseService;
import com.everythingisdata.datawarehouse.to.ResponseWSTO;
import com.everythingisdata.datawarehouse.utills.EveryThingIsDataConstant;
import com.everythingisdata.warehouse.exception.TwiceUploadException;

@RestController
@RequestMapping("/api/")
public class DataWarehouseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataWarehouseController.class);

	@Autowired
	private DataWarehouseService dataWarehouseService;

	/**
	 * @Desc : this will read and upload CSV file
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/uploaduserstories")
	public ResponseEntity<Object> uploadDealCustomerFile(@Valid @RequestBody MultipartFile file) throws IOException {

		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.debug("Invoked :  uploadDealCustomerFile()");

		String isFileInDB = dataWarehouseService.checkFileStatus(file.getOriginalFilename());

		if (null != isFileInDB && isFileInDB.equalsIgnoreCase(EveryThingIsDataConstant.FILEFOUND)) {
			throw new TwiceUploadException(
					"You are not allowed to uploaded same file twice.Please upload another file ..! ");

		} else {
			UploadedFileEntity objUploadedFile = dataWarehouseService.saveFileDetails(file.getOriginalFilename());
			List<ResponseWSTO> lstWSTo = dataWarehouseService.saveDealData(file, objUploadedFile);
			dataWarehouseService.bulkSaveAccumulativeDealsEntity();
			long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			LOGGER.warn("Time taken {} s", (endSecond - startSecond));
			LOGGER.debug("Exit :  uploadDealCustomerFile()");
			return new ResponseEntity<>(lstWSTo, HttpStatus.OK);
		}

	}

	/**
	 * @Desc: This will search deal summary by file Name
	 * @param fileName
	 * @return
	 */
	@GetMapping("/filesummary")
	public ResponseEntity<Object> searchFileSummaryByFileName(
			@RequestParam(name = "fileName", required = true) String fileName) {
		LOGGER.debug("Invoked :  searchUploadedDealsByFileName()");
		return new ResponseEntity<>(dataWarehouseService.fileSummaryByFileName(fileName), HttpStatus.OK);
	}
}
