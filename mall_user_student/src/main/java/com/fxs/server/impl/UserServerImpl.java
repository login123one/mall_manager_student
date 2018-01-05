package com.fxs.server.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.mapper.UserMapper;
import com.fxs.server.UserServer;
import com.fxs.util.MyRoutingDataSource;

public class UserServerImpl implements UserServer{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		//切换到数据源d1
		MyRoutingDataSource.setKey("d1");
		T_MALL_USER_ACCOUNT selectUser = userMapper.selectUser(user.getYh_mch(), user.getYh_mm());
		return selectUser;
	}
	@Override
	public T_MALL_USER_ACCOUNT login_data2(T_MALL_USER_ACCOUNT user) {
		//切换到数据源d2
		MyRoutingDataSource.setKey("d2");
		T_MALL_USER_ACCOUNT selectUser = userMapper.selectUser(user.getYh_mch(), user.getYh_mm());
		return selectUser;
	}
	
	
} 
