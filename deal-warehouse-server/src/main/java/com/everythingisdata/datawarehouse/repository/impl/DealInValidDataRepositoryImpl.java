/**
 * 
 */
package com.everythingisdata.datawarehouse.repository.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.everythingisdata.datawarehouse.entity.DealInValidDataEntity;
import com.everythingisdata.datawarehouse.repository.DealInValidDataRepositoryCustom;

/**
 * @author everythingisdata			
 *
 */
@Repository

public class DealInValidDataRepositoryImpl implements DealInValidDataRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(DealInValidDataRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void bulkSaveInValidDealData(List<DealInValidDataEntity> dealInValidDataLst) {
		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		LOGGER.info("Invoked:  bulkSaveInValidDealData() ");

		int batchSize = 3500;
		int i = 0;
		for (DealInValidDataEntity dealInValidData : dealInValidDataLst) {
			persistObject(dealInValidData);
			i++;
			if (i % batchSize == 0) {
				entityManager.clear();
				entityManager.flush();

			}
		}

		long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.info("Time Taken : {} s ", (endSecond - startSecond));
		LOGGER.info("Exit:  bulkSaveInValidDealData()");
	}

	public <T extends DealInValidDataEntity> T persistObject(T t) {
		if (null != t.getDealUniqueId()) {
			entityManager.persist(t);

		} else {
			entityManager.merge(t);
		}

		return t;
	}

}
