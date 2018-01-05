package com.fxs.service;

import java.math.BigDecimal;

import com.fxs.bean.OBJECT_T_MALL_ORDER;
import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.exception.OverSaleException;

public interface OrderService {

	void save_order(T_MALL_ADDRESS address, BigDecimal zje, OBJECT_T_MALL_ORDER order);

	void order_pay(OBJECT_T_MALL_ORDER order) throws OverSaleException;

}
