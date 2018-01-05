package com.fxs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.mapper.SpuMapper;
import com.fxs.service.SpuServiceInf;

@Service
public class SpuServiceInfImpl implements SpuServiceInf {
	
	@Autowired
	SpuMapper spuMapper;

	@Override
	public void save_spu(T_MALL_PRODUCT spu, List<String> list_image) {
		//把一个当作默认图片
		spu.setShp_tp(list_image.get(0));
		spuMapper.insert_spu(spu);
		
		
		Map<String,Object> map = new HashMap<>();
		map.put("spu", spu);
		map.put("list_image", list_image);
		
		//保存图片信息
		spuMapper.insert_spu_tp(map);
		
	}

}
