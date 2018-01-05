package com.fxs.server;

import javax.jws.WebService;

import com.fxs.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface UserServer {
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT login_data2(T_MALL_USER_ACCOUNT user);
}
