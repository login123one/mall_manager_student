package com.fxs.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fxs.bean.MODEL_T_MALL_ATTR;
import com.fxs.bean.MODEL_T_MALL_SKU;
import com.fxs.bean.MODEL_T_MALL_SKU_DETAIL;
import com.fxs.bean.MODEL_T_MALL_SKU_KEYWORD;
import com.fxs.bean.SET_T_MALL_SKU_ATTR_VALUE;
import com.fxs.bean.T_MALL_SKU;
import com.fxs.bean.T_MALL_SKU_ATTR_VALUE;
import com.fxs.service.SearchService;
import com.fxs.util.JedisPoolUtils;
import com.fxs.util.MyCacheUtil;
import com.fxs.util.MyHttpGetUtil;
import com.fxs.util.MyJsonUtil;
import com.fxs.util.MyPropertyUtil;

import redis.clients.jedis.Jedis;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	@RequestMapping("goto_class_select.do")
	public String goto_class_select(int class_2_id, String class_2_name, ModelMap map) {

		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);

		List<MODEL_T_MALL_SKU> sku_list = new ArrayList<>();
		// 拼接字符串key
		String key = "class_2_" + class_2_id;

		// 从redis中取出sku
		sku_list = MyCacheUtil.getList(key, MODEL_T_MALL_SKU.class);

		if (sku_list == null || sku_list.size() == 0) {
			// 通过数据库查询
			sku_list = searchService.get_sku_list(class_2_id);
			//查询的结果再次放入redis中 
			MyCacheUtil.setList(key, sku_list);

			// 说明没有redis中没有keys，需要把所有class_2_id的sku值放到keys中
			searchService.add_cache(class_2_id);

		}
		map.put("sku_list", sku_list);

		// 通过数据库查询
		List<MODEL_T_MALL_ATTR> attr_list = searchService.get_attr_list(class_2_id);
		map.put("attr_list", attr_list);

		return "sale_search";
	}

	/*
	 * @RequestMapping("attr_value_search.do")
	 * 
	 * @ResponseBody public String attr_value_search(@RequestParam("array_list[]")
	 * String[] array_list) {
	 * 
	 * return "sale_select_sku_list"; }
	 */
	/*
	 * @RequestMapping("attr_value_search.do") public String attr_value_search(int
	 * class_2_id,SET_T_MALL_SKU_ATTR_VALUE list_av,ModelMap map) {
	 * List<T_MALL_SKU_ATTR_VALUE> attr_value_list = list_av.getList_av();
	 * 
	 * 
	 * List<MODEL_T_MALL_SKU> sku_list =
	 * searchService.get_sku_list_by_attr(class_2_id,attr_value_list);
	 * 
	 * map.put("sku_list", sku_list);
	 * 
	 * return "sale_search"; }
	 */
	@RequestMapping("attr_value_search.do")
	public String attr_value_search(int class_2_id, SET_T_MALL_SKU_ATTR_VALUE list_av, ModelMap map) {

		if (list_av.getList_av() == null) {
			// 拼接字符串key
			String key = "class_2_" + class_2_id;
			// 从redis中取出sku
			List<MODEL_T_MALL_SKU> sku_list = MyCacheUtil.getList(key, MODEL_T_MALL_SKU.class);
			map.put("sku_list", sku_list);
		} else {

			List<T_MALL_SKU_ATTR_VALUE> attr_value_list = list_av.getList_av();
			// 创建一个新数组，目的是为了交叉检索
			String[] keys = new String[attr_value_list.size()];

			String dstKey = "dst_";

			// 获取全部的属性id 和 属性值id
			for (int i = 0; i < attr_value_list.size(); i++) {
				T_MALL_SKU_ATTR_VALUE attr_VALUE = attr_value_list.get(i);
				String key = "attr_" + class_2_id + "_" + attr_VALUE.getShxm_id() + "_" + attr_VALUE.getShxzh_id();
				// 把key放入keys中
				keys[i] = key;

				dstKey = dstKey + key;
			}

			// 判断之前key是否存在
			Jedis jedis = JedisPoolUtils.getJedis();
			Boolean exists = jedis.exists(dstKey);

			if (!exists) {
				// 把交叉检索得到的值放在redis中
				jedis.zinterstore(dstKey, keys);
			}

			// 从缓冲区获取list_sku
			List<MODEL_T_MALL_SKU> sku_list = MyCacheUtil.getList(dstKey, MODEL_T_MALL_SKU.class);

			if (sku_list == null || sku_list.size() == 0) {
				// 从数据库取值
				sku_list = searchService.get_sku_list_by_attr(class_2_id, attr_value_list);
			}
			map.put("sku_list", sku_list);
		}

		return "sale_search";
	}

	@RequestMapping("sku_detail.do")
	public String sku_detail(@RequestParam("sku_id") int sku_id, @RequestParam("spu_id") int spu_id, ModelMap map) {
		// 查询sku的详细信息
		MODEL_T_MALL_SKU_DETAIL sku_detail = searchService.get_sku_detail(sku_id, spu_id);
		// 查询sku的列表信息
		List<T_MALL_SKU> get_sku_list = searchService.get_sku_list_by_spuid(spu_id);

		map.put("sku_detail", sku_detail);
		map.put("get_sku_list", get_sku_list);

		return "sale_select_sku_detail";
	}

	@RequestMapping("keyword_search.do")
	public String keyword_search(String keyword, ModelMap map) {
		List<MODEL_T_MALL_SKU_KEYWORD> sku_list = new ArrayList<>();

		// 调用key_solr项目
		try {
			String doGet = MyHttpGetUtil
					.doGet(MyPropertyUtil.getSaveImgPath("keyword.properties", "sku_solr") + keyword + ".do");
			sku_list = MyJsonUtil.json_to_list(doGet, MODEL_T_MALL_SKU_KEYWORD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map.put("keyword", keyword);
		map.put("sku_list", sku_list);

		return "search_keyword_sku_list";
	}

}
