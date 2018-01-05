package com.fxs.server.test;

import javax.jws.WebService;

import com.fxs.bean.T_MALL_USER_ACCOUNT;


@WebService
public interface TestServer {

	public String ping(T_MALL_USER_ACCOUNT user);

}
