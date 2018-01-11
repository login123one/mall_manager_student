package com.fxs.ec.mapper;

import java.util.List;

import com.fxs.ec.bean.T_MALL_CLASS_1;
import com.fxs.ec.bean.T_MALL_CLASS_2;
import com.fxs.ec.bean.T_MALL_TRADE_MARK;

public interface Class1Mapper {
	
	List<T_MALL_CLASS_1> select_class_1();
	
	List<T_MALL_CLASS_2> select_class_2(int id);
	
	List<T_MALL_TRADE_MARK> select_class_1_tm(int id);
	
}
