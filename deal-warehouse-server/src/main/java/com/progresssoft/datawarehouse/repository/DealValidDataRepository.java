/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progresssoft.datawarehouse.entity.DealValidDataEntity;

/**
 * @author s727953
 *
 */
@Repository
public interface DealValidDataRepository
		extends JpaRepository<DealValidDataEntity, Integer>, DealValidDataRepositoryCustom {

}
