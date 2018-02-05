package com.progresssoft.datawarehouse;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.datawarehouse.services.DataWarehouseService;
import com.progresssoft.datawarehouse.services.impl.DataWarehouseServiceImpl;
import com.progresssoft.datawarehouse.to.ResponseWSTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TestData {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataWarehouseServiceImpl.class);

	@Autowired
	private DataWarehouseService dataWarehouseService;

	@Test
	public void testCheckFileStatus() {
		LOGGER.info("TEST INVOKED: testCheckFileStatus()");
		List<ResponseWSTO> responseWSTOLst = this.dataWarehouseService.fileSummaryByFileName("DataWareHouse");
		//assertNull(responseWSTOLst);
	}

}
