package com.fxs.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.bean.OBJECT_T_MALL_FLOW;
import com.fxs.bean.OBJECT_T_MALL_ORDER;
import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.bean.T_MALL_ORDER_INFO;
import com.fxs.exception.OverSaleException;
import com.fxs.mapper.OrderMapper;
import com.fxs.service.OrderService;
import com.fxs.util.MyDataUtil;

@Service
//@Transactional(propagation=Propagation.REQUIRED,noRollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public void save_order( T_MALL_ADDRESS address, BigDecimal zje,
			OBJECT_T_MALL_ORDER order) {
		//先保存订单表。返回主键
		Map<String,Object> map = new HashMap<>();
		
		map.put("order", order);
		map.put("address", address);
		orderMapper.insert_order(map);
		
		//保存物流包裹
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for(int i=0;i<list_flow.size();i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			Map<String,Object> map1 = new HashMap<>();
			map1.put("flow", flow);
			map1.put("dd_id", order.getId());
			map1.put("mdd", address.getYh_dz());
			//插入物流表，返回主键
			orderMapper.insert_flow(map1);
			
			
			Map<String,Object> map2 = new HashMap<>();
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			map2.put("list_info", list_info);
			map2.put("dd_id", order.getId());
			map2.put("flow_id", flow.getId());
			
			//批量插入
			orderMapper.insert_infos(map2);
			
			//在购物车中删除已经下单的商品
			List<Integer> list_gwch_id =new ArrayList<>();
			for(int j=0;j<list_info.size();j++) {
				int gwch_id = list_info.get(i).getGwch_id();
				list_gwch_id.add(gwch_id);
			}
			
			orderMapper.delete_gwch(list_gwch_id);
		}
	}
	
	@Override
	public void order_pay(OBJECT_T_MALL_ORDER order) throws OverSaleException {
		Integer id = order.getId();
		
		orderMapper.updata_order(id,MyDataUtil.getMyTime(3));
		
		List<OBJECT_T_MALL_FLOW> list_flow =order.getList_flow();
		for(int i=0;i<list_flow.size();i++) {
			OBJECT_T_MALL_FLOW flow = order.getList_flow().get(i);
			
			flow.setPsshj(MyDataUtil.getMyTime(1));
			flow.setPsmsh("上快递");
			flow.setYwy("老李");
			flow.setLxfsh("110");
			//跟新物流包裹
			orderMapper.updata_flow(flow);
			
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for(int j=0;j<list_info.size();j++) {
				T_MALL_ORDER_INFO info = list_info.get(i);
				//修改库存信息
				int sku_shl = info.getSku_shl();
				int sku_id = info.getSku_id();
				//判断是否库存充足
				//获取当前商品的库存,且当大于30时，加行级锁
				long kc = get_sku_kc_by_sku_id(info.getSku_id(),30);
				if(kc>sku_shl) {
					orderMapper.updata_sku(sku_id,sku_shl);
				}else {
					//发生超卖问题
					throw new OverSaleException("sale over");
				}
			}
		}
	}
	public int get_sku_kc_by_sku_id(int sku_id,int shl) {	
		int a = orderMapper.select_sku_kc(sku_id);
		if( a > shl ) {
			a = orderMapper.select_sku_kc_for_update(sku_id);
		}
		return a;
	}
}
