package com.fxs.mapper;


import java.util.Map;

import com.fxs.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	void insert_spu(T_MALL_PRODUCT spu);

	void insert_spu_tp(Map<String, Object> map);

	

}
