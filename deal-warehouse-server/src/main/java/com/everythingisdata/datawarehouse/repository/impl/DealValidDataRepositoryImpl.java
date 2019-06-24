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

import com.everythingisdata.datawarehouse.entity.DealValidDataEntity;
import com.everythingisdata.datawarehouse.repository.DealValidDataRepositoryCustom;

/**
 * @author everythingisdata
 *
 */
@Repository

public class DealValidDataRepositoryImpl implements DealValidDataRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(DealValidDataRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void bulkSaveValidDealData(List<DealValidDataEntity> entities) {

		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		LOGGER.info("Invoked:  bulkSaveInValidDealData() ");
		int batchSize = 10000;
		int i = 0;
		for (DealValidDataEntity entity : entities) {
			entityManager.persist(entity);
			i++;
			if (i % batchSize == 0) {
				flush();
				clear();
			}
		}

		long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.info("Time Taken : {} s ", (endSecond - startSecond));
		LOGGER.info("Exit:  bulkSaveInValidDealData()");
	}

	private void flush() {
		entityManager.flush();
	}

	private void clear() {
		entityManager.clear();
	}

}
