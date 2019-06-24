/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import java.util.List;

import com.everythingisdata.datawarehouse.entity.AccumulativeDealsEntity;

/**
 * @author everythingisdata
 *
 */ 
public interface AccumulativeDealsRepositoryCustom {

	/**
	 * 
	 * @param tmpAccumulativeDealsEntity
	 */
	void bulkSaveAccumulativeDealsEntity(List<AccumulativeDealsEntity> tmpAccumulativeDealsEntity);

}
