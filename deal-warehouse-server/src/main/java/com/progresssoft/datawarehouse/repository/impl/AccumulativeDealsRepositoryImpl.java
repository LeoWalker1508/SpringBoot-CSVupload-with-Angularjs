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

import com.progresssoft.datawarehouse.entity.AccumulativeDetailsEntity;
import com.progresssoft.datawarehouse.repository.AccumulativeDealsRepositoryCustom;

/**
 * @author s727953
 *
 */
@Repository
public class AccumulativeDealsRepositoryImpl implements AccumulativeDealsRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccumulativeDealsRepositoryImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void bulkSaveAccumulativeDealsEntity(List<AccumulativeDetailsEntity> tmpAccumulativeDealsEntity) {

		LOGGER.info("Invoked:  bulkSaveAccumulativeDealsEntity() ");
		long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		int batchSize = 100;
		int i = 0;
		for (AccumulativeDetailsEntity tmpEntity : tmpAccumulativeDealsEntity) {
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

	public <T extends AccumulativeDetailsEntity> T persistObject(T t) {
		if (null != t.getId()) {
			entityManager.persist(t);

		} else {
			entityManager.merge(t);
		}

		return t;
	}

}
