/**
 * 
 */
package com.everythingisdata.datawarehouse.repository;

import java.util.List;

import com.everythingisdata.datawarehouse.entity.DealValidDataEntity;

/**
 * @author everythingisdata
 *
 */
public interface DealValidDataRepositoryCustom {

	/**
	 * 
	 * @param dealInValidDataLst
	 */
	void bulkSaveValidDealData(List<DealValidDataEntity> dealInValidDataLst);

}
