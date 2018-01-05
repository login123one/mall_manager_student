package com.fxs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.service.CartService;
import com.fxs.util.MyCartSum;
import com.fxs.util.MyJsonUtil;

@Controller
public class CartController {
	@Autowired	
	private CartService cartService;
	
 
	@RequestMapping("add_cart.do")
	public ModelAndView cart_list(T_MALL_SHOPPINGCAR cart,@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie,
			HttpSession session,HttpServletResponse response ) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		
		//判断用户是否登录
		if(cart.getYh_id()==0) {//用户未登录
			
			if(StringUtils.isBlank(list_cart_cookie)) {//判断cookie是否是空值
				//cookie为空值，可以直接添加到cookie中
				list_cart.add(cart);
			}else {
				//cookie不是空值
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				boolean b = if_new_cart(list_cart, cart);//tures为新值
					if(!b) {//判断当前传递过来的商品是否在cookie中有
						for(int i=0;i<list_cart.size();i++) {
							if(list_cart.get(i).getSku_id()==cart.getSku_id()) {
								//有重复的，跟新后，覆盖cookie
								T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = list_cart.get(i);
								t_MALL_SHOPPINGCAR.setTjshl(t_MALL_SHOPPINGCAR.getTjshl()+cart.getTjshl());
								t_MALL_SHOPPINGCAR.setHj(t_MALL_SHOPPINGCAR.getSku_jg()*t_MALL_SHOPPINGCAR.getTjshl());
							}
						}
					}else {
						//没有重复的cookie，添加cart到cookie中
						list_cart.add(cart);	
					}
				
			}
			Cookie cookie = new Cookie("list_cart_cookie",MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			
		}else {
			//用户已登录,则操作数据库，同时通过session
			list_cart=(List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			
			if(list_cart == null || list_cart.size()==0) {//判断数据库的session中的购物项是否为空
				//session为空
				//直接把购物项添加到数据库
				cartService.add_cart(cart);
				//同时要同步到session
				list_cart = new ArrayList<>();
				list_cart.add(cart);
				session.setAttribute("list_cart_session",list_cart );
				
			}else {
				//session不为空
				//判断是否是新的cart
				boolean b = if_new_cart(list_cart, cart);//true为新值
				if(b) {
					//cart为新值
					//同步session
					list_cart.add(cart);
					//保存到数据库
					cartService.add_cart(cart);
				}else {
					//不为新值,说明在list中有一个是一样的
					for(int i =0 ;i<list_cart.size();i++) {
						if(list_cart.get(i).getSku_id()==cart.getSku_id()) {
							//在list中是重复的
							
							//同步session
							T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = list_cart.get(i);//从session中取出
							t_MALL_SHOPPINGCAR.setTjshl(t_MALL_SHOPPINGCAR.getTjshl()+cart.getTjshl());
							t_MALL_SHOPPINGCAR.setHj(t_MALL_SHOPPINGCAR.getTjshl()*t_MALL_SHOPPINGCAR.getSku_jg());
							
							//跟新数据库中的值
							cartService.updata_cart(t_MALL_SHOPPINGCAR);
						}
					}
					
				}
				
				
			}
			

		}

		ModelAndView mav = new ModelAndView("redirect:/cart_success.do");
		return mav;
	}
	public static  boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart,T_MALL_SHOPPINGCAR cart) {
		//先假设是一个新的cart
		boolean b = true;
		for(int i =0;i<list_cart.size();i++) {
			if(list_cart.get(i).getSku_id()==cart.getSku_id()) {
				b= false; 
			}
		}
		
		return b;
	}
	
	
	@RequestMapping("cart_success.do")
	public String cart_success() {
		
		return "sale_cart_success";
	}
	
	
	@RequestMapping("mincart_list.do")
	public String mincart_list(@CookieValue("list_cart_cookie") String list_cart_cookie,   HttpSession session,ModelMap map) {
		//判断是否登录
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart= null;
		if(user==null) {
			//从cookie中取数据
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			
		}else{
			//从session中取数据
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			
		}
		
		map.put("list_cart", list_cart);
		
		return "sale_mincart_list";
	}
	
	@RequestMapping("goto_cart_list.do")
	public String sale_cart_list(@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie,   HttpSession session,ModelMap map) {
		//判断是否登录
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart= null;
		if(user==null) {
			//从cookie中取数据
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		}else{
			//从session中取数据
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("list_cart", list_cart);
		map.put("sum", MyCartSum.getMyCartSum(list_cart));
		return "sale_cart_list";
	}
	
	@RequestMapping("change_status.do")
	public String change_status(T_MALL_SHOPPINGCAR cart,@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie,
			HttpSession session,HttpServletResponse response,ModelMap map) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		//判断用户是否登录
		if(user==null) {
			//用户未登录，取出cookie中的数据
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie,T_MALL_SHOPPINGCAR.class);
		}else {
			//用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		
		for(int i=0;i<list_cart.size();i++) {
			//获取需要跟新的cart
			if(list_cart.get(i).getSku_id()==cart.getSku_id()) {
				list_cart.get(i).setShfxz(cart.getShfxz());
				if(user==null) {
					//用户未登录，需要跟新cookie中的数据
					Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
				}else {
					//用户登录，需要跟新db
					cartService.updata_cart(cart);
					
				}
			}
		}
		
		map.put("list_cart", list_cart);
		map.put("sum", MyCartSum.getMyCartSum(list_cart));
		return "sale_cart_list_inner";
	}
	
	
	
	
	
	
}
