package com.fxs.server;

import javax.jws.WebService;

@WebService
public class TestServerImpl implements TestServer {

	@Override
	public String test(String hello) {
		System.out.println(hello);
		return hello;
	}

}
