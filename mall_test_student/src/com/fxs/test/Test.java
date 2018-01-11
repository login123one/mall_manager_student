package com.fxs.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;


public class Test {
	
	public void test() {
		
		List<Object> list = new ArrayList<>();
		Iterator<Object> iterator = list.iterator();
		while(iterator.hasNext()) {
			list.add(2);
		}
	}
}
