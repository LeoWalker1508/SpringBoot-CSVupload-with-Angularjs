package com.progresssoft.datawarehouse.utills;

import java.util.Date;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ProgressSoftUtil {

	private ProgressSoftUtil() {
		throw new IllegalStateException("this is Utility class");
	}

	/**
	 * @Desc : Validator Factory
	 * @return
	 */
	public static Validator validatorFactory() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();

	}

	public static Date convertStringToDate(String strDate) {
		return new Date(strDate);
	}

}
