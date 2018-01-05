package com.fxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.SET_T_MALL_ATTR;
import com.fxs.mapper.AttrMapper;
import com.fxs.service.AttrService;

@Service
public class AttrServiceImpl implements AttrService {
	@Autowired
	AttrMapper attrMapper;

	@Override
	public List<MODEL_T_MALL_ATTR> get_attr_list(int class_2_id) {
		List<MODEL_T_MALL_ATTR> attr_list = attrMapper.select_attr_list_by_class_2_id(class_2_id);
	
		return attr_list;
		
	}

	@Override
	public void save_attr_value(List<MODEL_T_MALL_ATTR> list, int class_2_id) {
		// 保存属性表
		//循环属性集合，逐一插入属性，返回主键
		for(int i=0;i<list.size();i++) {
			//给分类编号二赋值
			list.get(i).setFlbh2(class_2_id);
			attrMapper.insert_attr(list.get(i));
			//给属性值得表赋值
			
			attrMapper.insert_value(list.get(i).getId(),list.get(i).getAttr_values());
			
		}
		
	}






}
