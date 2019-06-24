/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everythingisdata.datawarehouse.entity.AccumulativeDealsEntity;

/**
 * @author everythingisdata
 *
 */
@Repository
public interface AccumulativeDealsRepository
		extends JpaRepository<AccumulativeDealsEntity, String>, AccumulativeDealsRepositoryCustom {

}
