/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import java.util.List;

import com.progresssoft.datawarehouse.entity.AccumulativeDetailsEntity;

/**
 * @author s727953
 *
 */ 
public interface AccumulativeDealsRepositoryCustom {

	/**
	 * 
	 * @param tmpAccumulativeDealsEntity
	 */
	void bulkSaveAccumulativeDealsEntity(List<AccumulativeDetailsEntity> tmpAccumulativeDealsEntity);

}
