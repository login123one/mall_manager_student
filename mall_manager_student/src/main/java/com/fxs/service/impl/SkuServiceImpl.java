package com.fxs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;
import com.fxs.mapper.SkuMapper;
import com.fxs.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService {
	
	@Autowired
	SkuMapper skuMapper;
	
	@Override
	public List<T_MALL_PRODUCT> sku_get_shp(int pp_id, int flbh1, int flbh2) {
		//通过map进行传参
		Map<String,Integer> map = new HashMap<>();
		map.put("pp_id", pp_id);
		map.put("flbh1", flbh1);
		map.put("flbh2", flbh2);
				
		List<T_MALL_PRODUCT> sku_shp=  skuMapper.sku_get_shp(map);
		return sku_shp;
	}
	@Override
	public List<MODEL_T_MALL_ATTR> get_attr_sku(int class_2_id) {
		
		List<MODEL_T_MALL_ATTR> attr_list_sku = skuMapper.select_attr_list(class_2_id);
		return attr_list_sku;
	}
	@Override
	public void save_sku(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_av) {
		// 先保存sku表，可以返回主键
		skuMapper.insert_sku(sku);
		//再保存属性关联表，批量插入 
		skuMapper.insert_sku_attr_value(sku.getId(),sku.getShp_id(),list_av);
		
		
	}

}
