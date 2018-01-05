package com.fxs.service;

import java.util.List;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {

	List<T_MALL_PRODUCT> sku_get_shp(int pp_id, int flbh1, int flbh2);

	List<MODEL_T_MALL_ATTR> get_attr_sku(int class_2_id);

	void save_sku(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_av);

}
