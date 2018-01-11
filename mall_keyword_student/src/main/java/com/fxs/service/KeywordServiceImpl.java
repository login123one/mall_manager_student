package com.fxs.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.bean.MODEL_T_MALL_SKU_KEYWORD;
import com.fxs.mapper.ImportDataMapper;
import com.fxs.util.MyPropertyUtil;

@Service
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	ImportDataMapper importDataMapper;

	@Override
	public void importData_solr(int class_2_id) {
		// 从数据库的查出
		List<MODEL_T_MALL_SKU_KEYWORD> sku_list = importDataMapper.select_sku_list(class_2_id);

		HttpSolrServer solrServer = new HttpSolrServer(MyPropertyUtil.getSaveImgPath("mysolr.properties", "sku_solr"));
		solrServer.setParser(new XMLResponseParser());
		solrServer.setConnectionTimeout(50000);

		try {
			solrServer.addBeans(sku_list);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
