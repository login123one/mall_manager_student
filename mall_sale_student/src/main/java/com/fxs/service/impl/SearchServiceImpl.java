package com.fxs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.MODEL_T_MALL_SKU;
import com.fxs.bean.MODEL_T_MALL_SKU_DETAIL;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;
import com.fxs.mapper.SearchMapper;
import com.fxs.service.SearchService;
import com.fxs.util.MyCacheUtil;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	SearchMapper searchMapper;

	@Override
	public List<MODEL_T_MALL_SKU> get_sku_list(int class_2_id) {
		return searchMapper.select_sku_list(class_2_id);
		
	}

	@Override
	public List<MODEL_T_MALL_ATTR> get_attr_list(int class_2_id) {
		
		return searchMapper.select_attr_list(class_2_id);
	}

	@Override
	public List<MODEL_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> attr_value_list) {
		Map<String,Object> map = new HashMap<>();
		map.put("class_2_id", class_2_id);
		//传递属性的参数
		if(attr_value_list!=null && attr_value_list.size()>0) {
			StringBuffer sql = new StringBuffer();
			
			sql.append(" AND sku.`Id` IN ( SELECT sku0.id FROM ");
			List<Integer> list =new ArrayList<>();
			
			for(int i=0;i<attr_value_list.size();i++) {
					
				sql.append(" (SELECT id FROM t_mall_sku_attr_value WHERE "+ 
				" shxm_id ="+ attr_value_list.get(i).getShxm_id()+
				" and shxzh_id ="+attr_value_list.get(i).getShxzh_id()+") sku"+i);
				if(attr_value_list.size()>1&& i< (attr_value_list.size()-1)) {
					sql.append(",");
				}	
					
			}
			if(attr_value_list.size()>1) {
				sql.append(" WHERE ");
			}
			
			for(int i=0;i<attr_value_list.size();i++) {
				if(attr_value_list.size()>1 && i < (attr_value_list.size()-1) ) {
					sql.append("  sku"+i+".id = sku"+(i+1)+".id ");
					if(attr_value_list.size()>2 && i < (attr_value_list.size()-2) ) {
						sql.append(" and ");
					}
				}
				
			}
			
			sql.append(" ) ");
			
			
			
			map.put("sql", sql.toString());
		}
		
		
		return searchMapper.select_sku_by_attr(map);
	}

	@Override
	public MODEL_T_MALL_SKU_DETAIL get_sku_detail(int sku_id, int spu_id) {
		//查询sku的内容
		MODEL_T_MALL_SKU_DETAIL list_sku = searchMapper.select_list_sku(sku_id);
		
		return list_sku;
	}

	@Override
	public List<T_MALL_SKU> get_sku_list_by_spuid(int spu_id) {
		
		return searchMapper.get_sku_list_by_spuid(spu_id);
	}

	@Override
	public void add_cache(int class_2_id) {
		//获取属性集合
		List<Integer> list_attr_id =searchMapper.select_list_sku_id(class_2_id);
		for(int i=0;i<list_attr_id.size();i++) {
			Integer attr_id = list_attr_id.get(i);
			//获取属性值集合
			List<Integer> list_value_id =searchMapper.select_list_value_id(attr_id);
			for(int j=0; j<list_value_id.size();j++) {
				//准备存放的key
				String key = "attr_"+class_2_id+"_"+attr_id+"_"+list_value_id.get(j);
				
				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				//
				t_MALL_SKU_ATTR_VALUE.setShxm_id(attr_id);
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(list_value_id.get(j));
				
				List<T_MALL_SKU_ATTR_VALUE> list_av = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
				list_av.add(t_MALL_SKU_ATTR_VALUE);
				
				
				List<MODEL_T_MALL_SKU> list_sku = get_sku_list_by_attr(class_2_id, list_av);
				
				MyCacheUtil.setList(key,list_sku);
				
			}
		}
		
		
	}

	
}
