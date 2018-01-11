package com.fxs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.SET_T_MALL_ATTR;
import com.fxs.service.AttrService;

@Controller
public class AttrController {
	@Autowired
	AttrService attrService;
	
	@RequestMapping("goto_attr.do")
	public String goto_attr() {
		return "attr/manager_attr_select";
	}
	
	@RequestMapping("get_attr_list.do")
	public String get_attr_list(int class_2_id,Map<String,Object> map) {
		//处理二级编号的业务
		List<MODEL_T_MALL_ATTR> attt_values  = attrService.get_attr_list(class_2_id);
	
		map.put("attt_values", attt_values);
		return "attr/manager_attr_list";
	}
	@RequestMapping("get_attr_list_json.do")
	@ResponseBody
	public List<MODEL_T_MALL_ATTR> get_attr_list_json(int class_2_id,Map<String,Object> map) {
		//处理二级编号的业务
		List<MODEL_T_MALL_ATTR> attt_values  = attrService.get_attr_list(class_2_id);
		
		map.put("attt_values", attt_values);
		return attt_values;
	}
	
	@RequestMapping("goto_add_attr.do")
	public String goto_add_attr(int class_2_id,String class_2_name,ModelMap map) {
		
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		
		return "attr/manager_attr_add";
		
	}
	@RequestMapping("save_attr.do")//ModelMap map
	public ModelAndView save_attr(SET_T_MALL_ATTR list_attr_set,int class_2_id,String class_2_name) {
		//表单的提交用重定向
		ModelAndView mav = new ModelAndView("redirect:/index.do");
		mav.addObject("url", "goto_add_attr.do?class_2_id="+class_2_id+"&class_2_name="+class_2_name);
		mav.addObject("title", "属性添加页面");
		
		//保存属性和属性值得业务
		attrService.save_attr_value(list_attr_set.getList_attr_set(),class_2_id);
		
		//这里没有更改生成的js文件，如果需要则要 重新
		
		//重定向到添加页面，所以要把class_2的id 和name重新存到域中
		mav.addObject("class_2_id", class_2_id);
		mav.addObject("class_2_name", class_2_name);
		return mav;	
	}
	
	
	
	
	
	
}
