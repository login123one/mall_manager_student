package com.fxs.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.fxs.bean.OBJECT_T_MALL_FLOW;

public interface OrderMapper {

	public void insert_order(Map<String, Object> map);

	
	public void insert_flow(Map<String, Object> map1);

	public void insert_infos(Map<String, Object> map2);

	public void delete_gwch(@Param("list_gwch_id") List<Integer> list_gwch_id);


	public void updata_order(@Param("id")Integer id,@Param("myTime") Date myTime);


	public void updata_flow(OBJECT_T_MALL_FLOW flow);


	public void updata_sku(@Param("sku_id")int sku_id , @Param("sku_shl")int sku_shl);


	public int select_sku_kc(int sku_id);


	public int select_sku_kc_for_update(int sku_id);

}
