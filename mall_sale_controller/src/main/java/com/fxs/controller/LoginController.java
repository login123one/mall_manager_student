package com.fxs.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.fxs.bean.T_MALL_USER_ACCOUNT;
import com.fxs.server.UserServer;
import com.fxs.service.CartService;
import com.fxs.util.MyJsonUtil;


@Controller
public class LoginController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	UserServer userServer;
	
	@RequestMapping("goto_register.do")
	public String goto_register() {
		
		return "sale_register";
	}
	

	
	@RequestMapping("goto_login.do")
	public String gotoLogin() {
		return "sale_login";
	}
	
	
	
	@RequestMapping("sale_login.do")
	public String sale_login(@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie,HttpSession session,HttpServletResponse response,
			T_MALL_USER_ACCOUNT user,String dataSource_radio){
		
		//T_MALL_USER_ACCOUNT user = loginService.getSaleUser(yh_mch,yh_mm);
		//远程调用远程登录系统
		if("d1".equals(dataSource_radio)) {
			//进入数据源1，查询的数据库为user
			user = userServer.login(user);
		}else if("d2".equals(dataSource_radio)){
			//进入数据源1，查询的数据库为ec
			user = userServer.login_data2(user);
		}else {
			user = userServer.login(user);
		}
		
		//判断是否登录
		if(user==null) {
			//如果用户登陆失败，重定向到登录页面
			return "redirect:/goto_login.do";
		}else {
			//如果用户认证成功，保存在session中，目的是在header中可以一直使用用户的信息
			session.setAttribute("user", user);
			//为了下一次在登录的状态可以显示以前的登陆过的信息，可以放在cookie中
			String yh_nch = user.getYh_nch();
			Cookie cookie = null;
			try {
				cookie = new Cookie("yh_nch",URLEncoder.encode(user.getYh_nch(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//设置cookie的过期时间
			cookie.setMaxAge(60*60*5);
			//发送给客户端
			response.addCookie(cookie);
			
			//合并购物车
			
			//获取数据库中所有的购物项
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartService.get_list_cart_by_user(user);
			//获取cookie中所有的购物项
			List<T_MALL_SHOPPINGCAR> list_cart_cookie_s = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			//合并购物项
			combine_cart(list_cart_db,list_cart_cookie_s,response,session);
			
			
			return "redirect:/sale_index.do";
		}
			
	}
	
	private void combine_cart(List<T_MALL_SHOPPINGCAR> list_cart_db, List<T_MALL_SHOPPINGCAR> list_cart_cookie_s,
			HttpServletResponse response, HttpSession session) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		
		//判断db中是否有数据
		if(list_cart_db==null|| list_cart_db.size()==0) {
			//db中没有数据
			if(list_cart_cookie_s!=null&&list_cart_cookie_s.size()>0) {
				//cookie中有数据
				//直接插入数据库，同步session,清除cookie;
				for(int i=0 ;i<list_cart_cookie_s.size();i++) {
					T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = list_cart_cookie_s.get(i);
					t_MALL_SHOPPINGCAR.setYh_id(user.getId());
					cartService.add_cart(t_MALL_SHOPPINGCAR);
				}
			}
			
		}else {
			//db中有数据
			//判断cookie中是否有数据
			if(list_cart_cookie_s!=null&&list_cart_cookie_s.size()>0) {
				//cookie中有数据
				for(int i=0;i<list_cart_cookie_s.size();i++) {
					
					T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = list_cart_cookie_s.get(i);
					//判断cookie中的每一个购物项在db中是否为新值
					boolean b = if_new_cart(list_cart_db, t_MALL_SHOPPINGCAR);
					if(b) {//b为true为新值
						//添加
						t_MALL_SHOPPINGCAR.setYh_id(user.getId());
						cartService.add_cart(t_MALL_SHOPPINGCAR);
					}else {//不是为新值
						//跟新
						for(int j=0;j<list_cart_db.size();j++) {//判断cookie中的值db中的哪一个值							
							if(list_cart_db.get(j).getSku_id()==t_MALL_SHOPPINGCAR.getSku_id()) {
								//获取在db中的库存，跟新库存
								T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR2 = list_cart_db.get(j);
								
								t_MALL_SHOPPINGCAR2.setTjshl(t_MALL_SHOPPINGCAR2.getTjshl()+t_MALL_SHOPPINGCAR.getTjshl());
								t_MALL_SHOPPINGCAR2.setHj(t_MALL_SHOPPINGCAR2.getTjshl()*t_MALL_SHOPPINGCAR2.getSku_jg());
								cartService.updata_cart(t_MALL_SHOPPINGCAR2);
							}
						}
						
					}
					
				}
				
			}else {
				//cookie没有数据
			}
		}
		
		//同步session
		List<T_MALL_SHOPPINGCAR> tms = cartService.get_list_cart_by_user(user);
		session.setAttribute("list_cart_session", tms);
		//清除cookie中购物项的数据
		Cookie cookie = new Cookie("list_cart_cookie", "");
		response.addCookie(cookie);		
		
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
	
	
	@RequestMapping("sale_logout.do")
	public String sale_logout(HttpSession session) {
		session.invalidate();
		return "redirect:/goto_login.do";
	}
		
}
