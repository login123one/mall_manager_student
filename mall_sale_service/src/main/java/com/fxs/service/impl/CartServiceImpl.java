package com.fxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.mapper.CartMapper;
import com.fxs.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void add_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.insert_cart(cart);
		
	}

	@Override
	public void updata_cart(T_MALL_SHOPPINGCAR cart) {
		
		cartMapper.updata_cart(cart);
		
	}

	@Override
	public List<T_MALL_SHOPPINGCAR> get_list_cart_by_user(T_MALL_USER_ACCOUNT user) {
		List<T_MALL_SHOPPINGCAR> list_cart = cartMapper.select_list_cart(user.getId());
		return list_cart;
	}


}
