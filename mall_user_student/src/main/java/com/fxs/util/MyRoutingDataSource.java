package com.fxs.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource{
	
	private static ThreadLocal<String> key = new ThreadLocal<>();

	public static  String getKey() {
		String data_key = key.get();
		removeKey();
		return data_key;
	}

	public static void setKey(String data_key) {
		key.set(data_key);
	}
	
	public static void removeKey() {
		key.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getKey();
	}
	
}