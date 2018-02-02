/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progresssoft.datawarehouse.entity.AccumulativeDetailsEntity;

/**
 * @author s727953
 *
 */
@Repository
public interface AccumulativeDealsRepository
		extends JpaRepository<AccumulativeDetailsEntity, String>, AccumulativeDealsRepositoryCustom {

}
