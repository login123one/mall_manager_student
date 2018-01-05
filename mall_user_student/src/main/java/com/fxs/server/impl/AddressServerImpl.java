package com.fxs.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.mapper.AddressMapper;
import com.fxs.server.AddressServer;

public class AddressServerImpl implements AddressServer {
	
	@Autowired
	AddressMapper userMapper;

	@Override
	public void addUserAddress(T_MALL_ADDRESS address, T_MALL_USER_ACCOUNT user) {
		userMapper.insertAdress(address,user);
	}

	@Override
	public void removeUserAddress(T_MALL_ADDRESS address) {
		userMapper.deleteAdress(address);
	}

	@Override
	public void updateAddress(T_MALL_ADDRESS address) {
		userMapper.updateAddress(address);
		
	}

	@Override
	public List<T_MALL_ADDRESS> getUserAddress(T_MALL_USER_ACCOUNT user) {
	 	List<T_MALL_ADDRESS> list_address =  userMapper.selectAddress(user);
		return list_address;
	}

	@Override
	public T_MALL_ADDRESS getUserAddress_by_addressid(int address_id) {
		
		return userMapper.select_address_by_id(address_id);
	}
	
	
}
