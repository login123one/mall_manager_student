package com.fxs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.mapper.LoginMapper;
import com.fxs.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginMapper loginMapper;

	@Override
	public T_MALL_USER_ACCOUNT getSaleUser(String yh_mch, String yh_mm) {
		// TODO Auto-generated method stub
		return loginMapper.selectUser(yh_mch,yh_mm);
	}

}
