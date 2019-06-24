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

import com.everythingisdata.datawarehouse.entity.AccumulativeDealsEntity;
import com.everythingisdata.datawarehouse.repository.AccumulativeDealsRepositoryCustom;

/**
 * @author everythingisdata
 *
 */
@Repository
public class AccumulativeDealsRepositoryImpl implements AccumulativeDealsRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccumulativeDealsRepositoryImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void bulkSaveAccumulativeDealsEntity(List<AccumulativeDealsEntity> tmpAccumulativeDealsEntity) {

		LOGGER.info("Invoked:  bulkSaveAccumulativeDealsEntity() ");
		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		int batchSize = 100;
		int i = 0;
		for (AccumulativeDealsEntity tmpEntity : tmpAccumulativeDealsEntity) {
			persistObject(tmpEntity);
			i++;
			if (i % batchSize == 0) {
				entityManager.clear();
			}
		}

		long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		LOGGER.info("Time Taken : {} s ", (endSecond - startSecond));
		LOGGER.info("Exit:  bulkSaveAccumulativeDealsEntity()");
	}

	public <T extends AccumulativeDealsEntity> T persistObject(T t) {
		if (null != t.getId()) {
			entityManager.persist(t);

		} else {
			entityManager.merge(t);
		}

		return t;
	}

}
