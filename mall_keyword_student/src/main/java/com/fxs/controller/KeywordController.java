package com.fxs.controller;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.bean.MODEL_T_MALL_SKU_KEYWORD;
import com.fxs.service.KeywordService;
import com.fxs.util.MySolrUtil;

@Controller
public class KeywordController {
	@Autowired
	private KeywordService keywordService;
	
	public  void importData(int Class_2_id) {
		keywordService.importData_solr(Class_2_id);
	}
	

	@RequestMapping("/keyword_search/{keyword}.do")
	@ResponseBody
	public List<MODEL_T_MALL_SKU_KEYWORD> keyword_search(@PathVariable("keyword") String keyword) {
		// 这是从solr中取出数据
//		importData(28);
		
		List<MODEL_T_MALL_SKU_KEYWORD> beans =null;
		// 获取solr服务器
		HttpSolrServer mySolr = MySolrUtil.getMySolr();

		// 确定以哪种方式查询
		SolrQuery solrQuery = new SolrQuery();
		// 设置关键字查询
		solrQuery.setQuery("copy_item:" + keyword);
		solrQuery.setRows(50);
		try {
			//从solr中得到的数据
			QueryResponse query = mySolr.query(solrQuery);
			//从solr中得到的数据转化为bean
			 beans = query.getBeans(MODEL_T_MALL_SKU_KEYWORD.class);
			
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return beans;
	}
}
