package com.fxs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fxs.bean.T_MALL_PRODUCT;
import com.fxs.service.SpuServiceInf;
import com.fxs.util.MyUploadUtil;

@Controller
public class SpuController {
	
	@Autowired
	SpuServiceInf spuServiceInf;
	
	@RequestMapping("goto_spu.do")
	public String goto_spu() {
		return "manager_spu";
	} 
	
	@RequestMapping("goto_spu_add.do")
	public String goto_spu_add() {
		return "manager_spu_add";
	}
	
	@RequestMapping("save_spu.do")
	public ModelAndView save_spu (T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files ) {
		ModelAndView mav = new ModelAndView("redirect:/index.do");
		
		mav.addObject("url", "goto_spu_add.do");
		mav.addObject("title", "商品信息添加");
		
		//保存图片信息，到公共的共享资源平台
		List<String> list_image = MyUploadUtil.upload_image(files);
		
		//在保存到数据库
		spuServiceInf.save_spu(spu,list_image);
		
		return mav;
	}
}
