package com.fxs.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fxs.server.TestServer;
import com.fxs.server.TestServerService;

public class TestWebServer {
	
	public static void main(String[] args) {
//		TestServerService testServerService = new TestServerService();
//		TestServer testServerPort = testServerService.getTestServerPort();
//		
//		String test = testServerPort.test("我最帅");
//		
		List<Object> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		List<Object> list2 = Collections.synchronizedList(list);
		for (Object object : list2) {
			System.out.println(object);
		}
		
// 		System.out.println(test);
		
		
	}

}


