package com.fxs.testLombok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class TestNotSale {
	public static void main(String[] args) {

		Map<Object,Object> map  = new ConcurrentHashMap<>();

		for (int i = 1; i <= 30; i++) {
			int a = i;
			new Thread(() -> {
				
				map.put(UUID.randomUUID().toString(),"1");
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

	public static void setNotSale() {
		Set<String> set = new CopyOnWriteArraySet<>();

		for (int i = 1; i <= 30; i++) {
			int a = i;
			new Thread(() -> {
				try {
					TimeUnit.NANOSECONDS.sleep(a*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				set.add(UUID.randomUUID().toString());
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}

	public static void copyOnWriteArrayList() {
		List<String> list = new CopyOnWriteArrayList<>();

		for (int i = 1; i <= 50; i++) {
			int a = i;
			new Thread(() -> {
				list.add(UUID.randomUUID().toString());
				try {
					TimeUnit.NANOSECONDS.sleep(a);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}
