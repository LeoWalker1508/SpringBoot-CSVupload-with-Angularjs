/**
 * 
 */
package com.progresssoft.datawarehouse.repository.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.progresssoft.datawarehouse.entity.DealValidDataEntity;
import com.progresssoft.datawarehouse.repository.DealValidDataRepositoryCustom;

/**
 * @author s727953
 *
 */
@Repository

public class DealValidDataRepositoryImpl implements DealValidDataRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(DealValidDataRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void bulkSaveValidDealData(List<DealValidDataEntity> dealInValidDataLst) {

		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		LOGGER.info("Invoked:  bulkSaveInValidDealData() ");

		int batchSize = 3500;
		int i = 0;
		for (DealValidDataEntity dealInValidData : dealInValidDataLst) {
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

	public <T extends DealValidDataEntity> T persistObject(T t) {
		if (null != t.getDealUniqueId()) {
			entityManager.persist(t);

		} else {
			entityManager.merge(t);
		}

		return t;
	}

}
