/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progresssoft.datawarehouse.entity.DealInValidDataEntity;

/**
 * @author s727953
 *
 */
@Repository
public interface DealInValidDataRepository
		extends JpaRepository<DealInValidDataEntity, String>, DealInValidDataRepositoryCustom {

}
