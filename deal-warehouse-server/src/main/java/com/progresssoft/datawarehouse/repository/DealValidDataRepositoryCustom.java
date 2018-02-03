/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import java.util.List;

import com.progresssoft.datawarehouse.entity.DealValidDataEntity;

/**
 * @author s727953
 *
 */
public interface DealValidDataRepositoryCustom {

	/**
	 * 
	 * @param dealInValidDataLst
	 */
	void bulkSaveValidDealData(List<DealValidDataEntity> dealInValidDataLst);

}
