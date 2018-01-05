package com.fxs.server;

import java.util.List;

import javax.jws.WebService;

import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServer {
	
	public void addUserAddress(T_MALL_ADDRESS address,T_MALL_USER_ACCOUNT user);
	public void removeUserAddress(T_MALL_ADDRESS address);
	public void updateAddress(T_MALL_ADDRESS address);
	public List<T_MALL_ADDRESS> getUserAddress(T_MALL_USER_ACCOUNT user);
	public T_MALL_ADDRESS getUserAddress_by_addressid(int address_id);
}
