package com.fxs.mapper;

import java.util.List;
import java.util.Map;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.MODEL_T_MALL_SKU;
import com.fxs.bean.MODEL_T_MALL_SKU_DETAIL;
import com.fxs.bean.T_MALL_SKU;

public interface SearchMapper {

	List<MODEL_T_MALL_SKU> select_sku_list(int class_2_id);

	List<MODEL_T_MALL_ATTR> select_attr_list(int class_2_id);

	List<MODEL_T_MALL_SKU> select_sku_by_attr(Map<String, Object> map);

	MODEL_T_MALL_SKU_DETAIL select_list_sku(int sku_id);

	List<T_MALL_SKU> get_sku_list_by_spuid(int spu_id);

	List<Integer> select_list_value_id(Integer attr_id);

	List<Integer> select_list_sku_id(int class_2_id);

}
