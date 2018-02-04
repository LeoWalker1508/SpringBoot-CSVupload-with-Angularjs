package com.progresssoft.datawarehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DataWarehouseApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataWarehouseApplication.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(DataWarehouseApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("Environment : {}", env.getActiveProfiles());

	}

}
