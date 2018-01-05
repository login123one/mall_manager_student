package com.fxs.server.test;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.mapper.UserMapper;
import com.fxs.server.impl.UserServerImpl;
import com.fxs.util.MyJsonUtil;
import com.google.gson.Gson;


public class TestServerImpl implements TestServer {
	
	@Autowired
	UserMapper userMapper;

	@Override
	@Path("/test")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String ping(@BeanParam T_MALL_USER_ACCOUNT user) {
		
		T_MALL_USER_ACCOUNT login = userMapper.selectUser(user.getYh_mch(), user.getYh_mm());
//		String object_to_json = MyJsonUtil.object_to_json(login);
		Gson gson = new Gson();
		String json = gson.toJson(login);
		
		return json;
	}

}
