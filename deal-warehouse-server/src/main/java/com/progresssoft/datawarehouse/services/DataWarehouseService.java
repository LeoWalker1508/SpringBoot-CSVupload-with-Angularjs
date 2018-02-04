/**
 * 
 */
package com.progresssoft.datawarehouse.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.datawarehouse.entity.UploadedFileEntity;
import com.progresssoft.datawarehouse.to.ResponseWSTO;

/**
 * @author s727953
 *
 */
public interface DataWarehouseService {
	/**
	 * @ Desc : It will check the given file already uploaded or not
	 * 
	 * @param fileName
	 * @return
	 */

	String checkFileStatus(String fileName);

	/**
	 * @Desc : This will save the uploaded file name in saperate table
	 * @param uploadfile
	 * @return
	 */

	UploadedFileEntity saveFileDetails(String uploadFileName);

	/**
	 * @Desc: This will insert & and update the User's CSV file data in DB
	 * @param file
	 * @param uploadedFileid
	 * @return
	 * @throws IOException
	 */

	List<ResponseWSTO> saveDealData(MultipartFile file, UploadedFileEntity entity) throws IOException;

	/**
	 * @Desc: this will Search uploaded file summary by file name
	 * @param fileName
	 * @return
	 */
	List<ResponseWSTO> fileSummaryByFileName(String fileName);

	/**
	 * 
	 * @param tmpAccumulativeDealsEntity
	 */

	void bulkSaveAccumulativeDealsEntity();

 

}
