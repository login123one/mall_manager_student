package com.fxs.mapper;

import java.util.List;

import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.bean.T_MALL_USER_ACCOUNT;

public interface AddressMapper {

	void insertAdress(T_MALL_ADDRESS address, T_MALL_USER_ACCOUNT user);

	void deleteAdress(T_MALL_ADDRESS address);

	void updateAddress(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> selectAddress(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS select_address_by_id(int address_id);

}
