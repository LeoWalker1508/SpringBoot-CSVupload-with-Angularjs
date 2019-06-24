package com.everythingisdata.datawarehouse.utills;

import java.util.Date;

public class EveryThingIsDataUtil {

	private EveryThingIsDataUtil() {
		throw new IllegalStateException("this is Utility class");
	}

	@SuppressWarnings("deprecation")
	public static Date convertStringToDate(String strDate) {
		if (null != strDate && !strDate.isEmpty()) {
			return new Date(strDate);
		}
		return null;
	}

}
