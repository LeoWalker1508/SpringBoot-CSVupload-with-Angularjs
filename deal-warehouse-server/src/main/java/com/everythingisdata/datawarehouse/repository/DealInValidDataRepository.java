/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everythingisdata.datawarehouse.entity.DealInValidDataEntity;

/**
 * @author everythingisdata
 *
 */
@Repository
public interface DealInValidDataRepository
		extends JpaRepository<DealInValidDataEntity, String>, DealInValidDataRepositoryCustom {

}
