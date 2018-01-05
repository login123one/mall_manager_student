package com.fxs.mapper;

import java.util.List;

import com.fxs.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {

	void insert_cart(T_MALL_SHOPPINGCAR cart);

	void updata_cart(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> select_list_cart(int id);

}
