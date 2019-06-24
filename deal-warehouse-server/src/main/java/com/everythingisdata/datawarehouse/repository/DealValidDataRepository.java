/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everythingisdata.datawarehouse.entity.DealValidDataEntity;

/**
 * @author everythingisdata
 *
 */
@Repository
public interface DealValidDataRepository
		extends JpaRepository<DealValidDataEntity, Integer>, DealValidDataRepositoryCustom {

}
