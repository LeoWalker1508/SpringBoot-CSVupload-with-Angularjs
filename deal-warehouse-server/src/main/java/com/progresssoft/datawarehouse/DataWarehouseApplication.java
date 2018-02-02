package com.progresssoft.datawarehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DataWarehouseApplication {

	@Autowired
	private Environment env;

	{
		System.out.println(env.getActiveProfiles());
	}

	public static void main(String[] args) {
		SpringApplication.run(DataWarehouseApplication.class, args);
	}

}
