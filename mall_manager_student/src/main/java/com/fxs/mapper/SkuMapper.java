package com.fxs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuMapper {

	List<T_MALL_PRODUCT> sku_get_shp(Map<String, Integer> map);

	List<MODEL_T_MALL_ATTR> select_attr_list(int class_2_id);

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_attr_value(@Param("sku_id") int sku_id, @Param("shp_id")int shp_id, @Param("list_av") List<T_MALL_SKU_ATTR_VALUE> list_av);

}
