package com.fxs.util;

import java.math.BigDecimal;
import java.util.List;

import com.fxs.bean.T_MALL_SHOPPINGCAR;

public class MyCartSum {
	
	public static BigDecimal getMyCartSum(List<T_MALL_SHOPPINGCAR> list_cart) {
		
		BigDecimal bigDecimal = new BigDecimal("0");
		
		
		for(int i=0;i<list_cart.size();i++) {
			if(list_cart.get(i).getShfxz().equals("1")){
				bigDecimal = bigDecimal.add(new BigDecimal(list_cart.get(i).getHj()+""));
			}
		}
		
		return bigDecimal;
	}
}	
