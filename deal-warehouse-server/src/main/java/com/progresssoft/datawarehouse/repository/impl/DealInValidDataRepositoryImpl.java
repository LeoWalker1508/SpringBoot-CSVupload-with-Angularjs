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

import com.progresssoft.datawarehouse.entity.DealInValidDataEntity;
import com.progresssoft.datawarehouse.repository.DealInValidDataRepositoryCustom;

/**
 * @author s727953
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

		LOGGER.debug("Invoked:  bulkSaveInValidDealData() ");

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
		LOGGER.debug("Time Taken : {} s ", (endSecond - startSecond));
		LOGGER.debug("Exit:  bulkSaveInValidDealData()");
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
