package com.fxs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.SET_T_MALL_SKU_ATTR_VALUE;
import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.service.SkuService;

@Controller
public class SkuController {
	@Autowired
	SkuService skuService;
	
	@RequestMapping("goto_sku.do")
	public String goto_sku() {
		return "sku/manager_sku";
	}
	
	@RequestMapping("sku_get_shp.do")
	@ResponseBody
	public List<T_MALL_PRODUCT> sku_get_shp(int pp_id,int flbh1,int flbh2,ModelMap map) {
		//获取商品的信息，得到的是一个集合
		List<T_MALL_PRODUCT> list_spu = skuService.sku_get_shp(pp_id,flbh1,flbh2);
		map.put("list_spu", list_spu);
		return list_spu;
	}
	
	@RequestMapping("sku_get_attr_list.do")
	public String sku_get_attr_list(int class_2_id,ModelMap map) {
		
		List<MODEL_T_MALL_ATTR> attr_list_sku =  skuService.get_attr_sku(class_2_id);
		map.put("attr_list_sku", attr_list_sku);
		
		return "sku/manager_sku_attr_list";
	}
	
	@RequestMapping("save_sku.do")
	public ModelAndView save_sku(T_MALL_SKU sku,SET_T_MALL_SKU_ATTR_VALUE sku_attr_value) {
		//为防止重复提交表单，要用重定向
		ModelAndView mv= new ModelAndView("redirect:/goto_sku.do");
		skuService.save_sku(sku,sku_attr_value.getList_av());		
		return mv;
		
	}
	
	
}
