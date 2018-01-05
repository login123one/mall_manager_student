package com.fxs.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fxs.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;

import net.sf.json.JSONArray;


public class MyJsonUtil {	
	
	//对象转json
	public static <T> String object_to_json(T t) {
		
		Gson json =new Gson();
		String object_to_json = json.toJson(t);
		String encode = " ";
		try {
			encode = URLEncoder.encode(object_to_json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encode;
	}
	//json转对象
	public static <T> T json_to_object(String json,Class<T> t) {
		
		if(StringUtils.isBlank(json)) {
			return null;
		}else {
			String decode =" ";
			try {
				decode = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new Gson();
			T json2 = gson.fromJson(decode, t);
			return json2;
		}
		
	}
	//json转list
	public static <T> List<T> json_to_list(String json,Class<T> t) {
		if(StringUtils.isBlank(json)) {
			return null;
		}else {
			String decode ="";
			try {
				decode = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray fromObject = JSONArray.fromObject(decode);
			List<T> list = (List<T>)JSONArray.toCollection(fromObject,t);		
			
			return list;
		}
		
		
	}
	//list转json
	public static <T> String list_to_json(List<T> t) {
		Gson gson = new Gson();
		String json = gson.toJson(t);
		String encode = "";
		try {
			encode = URLEncoder.encode(json,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encode;
		
	} 
	
	
		

}
