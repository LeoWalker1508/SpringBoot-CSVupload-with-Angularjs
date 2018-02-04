package com.progresssoft.datawarehouse.utills;

import java.util.Date;

public class ProgressSoftUtil {

	private ProgressSoftUtil() {
		throw new IllegalStateException("this is Utility class");
	}

	public static Date convertStringToDate(String strDate) { 
		return new Date(strDate);
	}

}
