package com.fxs.test;

import javax.xml.ws.Endpoint;

import com.fxs.server.TestServer;
import com.fxs.server.TestServerImpl;

public class Test {
	public static void main(String[] args) {
		 
		TestServer serverImpl = new TestServerImpl();
		Endpoint.publish("http://192.168.19.65:1234/ws", serverImpl);
		System.out.println("111");
	}
}
