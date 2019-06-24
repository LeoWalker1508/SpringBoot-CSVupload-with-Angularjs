/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import java.util.List;

import com.everythingisdata.datawarehouse.entity.DealInValidDataEntity;

/**
 * @author everythingisdata
 *
 */
public interface DealInValidDataRepositoryCustom {

	/**
	 * 
	 * @param dealInValidDataLst
	 */
	void bulkSaveInValidDealData(List<DealInValidDataEntity> dealInValidDataLst);

}
