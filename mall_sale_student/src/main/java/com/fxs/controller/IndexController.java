package com.fxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("sale_index.do")
	public String index() {
		return "sale_index";
	}
}
