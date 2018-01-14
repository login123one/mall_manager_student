package com.fxs.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fxs.bean.OBJECT_T_MALL_FLOW;
import com.fxs.bean.OBJECT_T_MALL_ORDER;
import com.fxs.bean.T_MALL_ADDRESS;
import com.fxs.bean.T_MALL_ORDER_INFO;
import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.exception.OverSaleException;
import com.fxs.server.AddressServer;
import com.fxs.server.UserServer;
import com.fxs.service.CartService;
import com.fxs.service.OrderService;
import com.fxs.util.MyCartSum;

@Controller
@SessionAttributes("order")
public class OrderController {
	@Autowired
	UserServer userServer;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AddressServer addressServer;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("goto_check_account.do")
	public String goto_check_account(HttpSession session,BigDecimal zje,ModelMap map) {
		
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		
		//此时要判断用户是否登录，这里没做判断
		
		
		List<T_MALL_SHOPPINGCAR> list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		
		//收货地址
		List<T_MALL_ADDRESS> userAddress = addressServer.getUserAddress(user);
		
		
		//进行拆单业务
		OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
		order.setZje(zje);
		order.setJdh(0);
		order.setYh_id(user.getId());
		
		//1 获取商品的库存地址 ，取出的库存地址不能重复
		Set<String> list_kudz = new HashSet<String>();
		for(int i=0;i<list_cart.size();i++) {
			if(list_cart.get(i).getShfxz().equals("1")) {
				list_kudz.add(list_cart.get(i).getKcdz());
			}
		}
		//2 根据仓库地址拆分成物流包裹
		List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
		
		Iterator<String> iterator = list_kudz.iterator();
		while(iterator.hasNext()) {
			String kcdz = iterator.next();
			
			OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
			flow.setYh_id(user.getId());
			flow.setPsfsh("尚快递");
			flow.setMqdd("商品待出库");
			
			List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
			
			for(int i=0;i<list_cart.size();i++) {
				if(list_cart.get(i).getShfxz().equals("1") && list_cart.get(i).getKcdz().equals(kcdz)) {
					T_MALL_SHOPPINGCAR cart = list_cart.get(i);
					T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
					info.setSku_id(cart.getSku_id());
					info.setSku_mch(cart.getSku_mch());
					info.setShp_tp(cart.getShp_tp());
					info.setSku_jg(cart.getSku_jg());
					info.setSku_shl(cart.getTjshl());
					info.setSku_kcdz(kcdz);
					info.setGwch_id(cart.getId());
					//把每个相同的地址放在同一个集合中
					list_info.add(info);
				}
			}
			
			//把得到的商品信息放到物流包裹中
			flow.setList_info(list_info);
			list_flow.add(flow);
		}
		//把所有的物流信息放到订单表中
		order.setList_flow(list_flow);
		
		//3 物流包裹封装订单的信息（就是购买的商品的信息）
		map.put("order", order);
		map.put("sum", MyCartSum.getMyCartSum(list_cart));
		map.put("list_address", userAddress);
		return "sale_check_order";
	}
	
	@RequestMapping("save_order.do")
	public String save_order(HttpSession session,@ModelAttribute("order") OBJECT_T_MALL_ORDER order, int address_id,BigDecimal zje) {
		//获取用户
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		//根据用户的地址的id查询地址信息
		T_MALL_ADDRESS address = addressServer.getUserAddress_by_addressid(address_id);
		
		//提交订单，保存订单到数据库
		orderService.save_order(address,zje,order);
		
		//同步session
		session.setAttribute("list_cart_session", cartService.get_list_cart_by_user(user));
		
		//重定向到支付页面
		return "redirect:/goto_cashier.do";
	}
	
	@RequestMapping("goto_cashier")
	public String goto_cashier(@ModelAttribute("order") OBJECT_T_MALL_ORDER order,ModelMap map) {
		//处理支付业务
		return "sale_cashier";
	}
	
	@RequestMapping("order_pay.do")
	public String order_pay(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		//这一步是支付成功后，开始处理库存
		try {
			orderService.order_pay(order);
		} catch (OverSaleException e) {
			return "redirect:/order_fail/"+e+".do";
		}
		
		return "redirect:/order_success.do";
	}
	
	@RequestMapping("order_success.do")
	public String order_success() {
		
		return "goto_order_success";
	}
	@RequestMapping("order_fail/{emssage}.do")
	public String order_fail() {
		
		return "goto_order_fail";
	}
}
