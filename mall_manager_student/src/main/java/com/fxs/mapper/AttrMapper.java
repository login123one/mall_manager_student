package com.fxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.T_MALL_VALUE;


public interface AttrMapper {

	
	List<MODEL_T_MALL_ATTR> select_attr_list_by_class_2_id(int class_2_id);

	void insert_attr(@Param("attr") MODEL_T_MALL_ATTR model_T_MALL_ATTR);

	void insert_value(@Param("shxm_id") int id, @Param("list_value") List<T_MALL_VALUE> attr_values);

}
