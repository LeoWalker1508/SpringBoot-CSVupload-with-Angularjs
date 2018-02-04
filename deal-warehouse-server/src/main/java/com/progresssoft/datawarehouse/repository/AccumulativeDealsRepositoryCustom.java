/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import java.util.List;

import com.progresssoft.datawarehouse.entity.AccumulativeDealsEntity;

/**
 * @author s727953
 *
 */ 
public interface AccumulativeDealsRepositoryCustom {

	/**
	 * 
	 * @param tmpAccumulativeDealsEntity
	 */
	void bulkSaveAccumulativeDealsEntity(List<AccumulativeDealsEntity> tmpAccumulativeDealsEntity);

}
