package com.fxs.util;

import java.util.Calendar;
import java.util.Date;

public class MyDataUtil {
	
	public static Date getMyTime(int a) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE, a);
		Date time = instance.getTime();
	
		return time;
	}
}
