package com.fxs.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public class MySolrUtil {
	
	public static HttpSolrServer getMySolr() {
		
		HttpSolrServer solrServer = new HttpSolrServer(MyPropertyUtil.getSaveImgPath("mysolr.properties", "sku_solr"));
		solrServer.setConnectionTimeout(10000);
		//设置解析器
		solrServer.setParser(new XMLResponseParser());
		
		return solrServer;
	}
}
