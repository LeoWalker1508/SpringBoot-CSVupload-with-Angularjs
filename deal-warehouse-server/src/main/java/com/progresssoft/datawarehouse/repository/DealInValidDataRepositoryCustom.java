/**
 * 
 */
package com.progresssoft.datawarehouse.repository;

import java.util.List;

import com.progresssoft.datawarehouse.entity.DealInValidDataEntity;

/**
 * @author s727953
 *
 */
public interface DealInValidDataRepositoryCustom {

	/**
	 * 
	 * @param dealInValidDataLst
	 */
	void bulkSaveInValidDealData(List<DealInValidDataEntity> dealInValidDataLst);

}
