package com.fxs.util;

import java.io.IOException;
import java.util.Properties;

public class MyPropertyUtil {

	public static String getSaveImgPath(String properties, String key) {
		
		Properties prope = new Properties();
		
		try {
			prope.load(MyPropertyUtil.class.getClassLoader().getResourceAsStream(properties));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String val = prope.getProperty(key);
		return val;
	}

}
