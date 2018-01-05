package com.fxs.service;

import java.util.List;
import java.util.Map;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.SET_T_MALL_ATTR;
import com.fxs.bean.T_MALL_VALUE;

public interface AttrService {

	List<MODEL_T_MALL_ATTR> get_attr_list(int class_2_id);
	
	void save_attr_value(List<MODEL_T_MALL_ATTR> list, int class_2_id);





}
