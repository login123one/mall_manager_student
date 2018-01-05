package com.fxs.service;


import java.util.List;

import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.fxs.bean.T_MALL_USER_ACCOUNT;

public interface CartService {

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void updata_cart(T_MALL_SHOPPINGCAR cart);


	List<T_MALL_SHOPPINGCAR> get_list_cart_by_user(T_MALL_USER_ACCOUNT user);


}
