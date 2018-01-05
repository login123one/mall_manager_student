package com.fxs.service;

import java.util.List;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.MODEL_T_MALL_SKU;
import com.fxs.bean.MODEL_T_MALL_SKU_DETAIL;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchService {

	List<MODEL_T_MALL_SKU> get_sku_list(int class_2_id);

	List<MODEL_T_MALL_ATTR> get_attr_list(int class_2_id);

	List<MODEL_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> attr_value_list);

	MODEL_T_MALL_SKU_DETAIL get_sku_detail(int sku_id, int spu_id);

	List<T_MALL_SKU> get_sku_list_by_spuid(int spu_id);

	void add_cache(int class_2_id);


}
