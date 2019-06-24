/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.everythingisdata.datawarehouse.entity.UploadedFileEntity;

/**
 * @author everythingisdata
 *
 */
public interface UploadedFileRepository extends JpaRepository<UploadedFileEntity, Long> {
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	// @Query("select u from UploadedFile u where u.fileName = :fileName")
	List<UploadedFileEntity> findByFileNameContaining(@Param("fileName") String fileName);

	UploadedFileEntity findByFileName(@Param("fileName") String fileName);
}
