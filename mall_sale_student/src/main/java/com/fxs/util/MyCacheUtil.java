package com.fxs.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MyCacheUtil {
	public static <T> List<T> getList(String key,Class<T> t) {
		
		List<T> list_sku = new ArrayList<>();
		//redis为第三方客户端，容易出错，需要抓取异常
		Jedis jedis = new Jedis();
		try {
			jedis = JedisPoolUtils.getJedis();
			Set<String> zrange = jedis.zrange(key, 0, -1);
			Iterator<String> iterator = zrange.iterator();
			while(iterator.hasNext()) {
				String next = iterator.next();
				T object = MyJsonUtil.json_to_object(next, t);
				
				list_sku.add(object);
			}
		} catch (Exception e) {
			list_sku = null;
		}
		jedis.close();
		return list_sku;
	}
	
	public static <T> void setList(String key,List<T> t) {
		
		//redis为第三方客户端，容易出错，需要抓取异常
		Jedis jedis = new Jedis();
		try {
			jedis = JedisPoolUtils.getJedis();
			for(int i=0;i<t.size();i++) {
				T t2 = t.get(i);
				String json = MyJsonUtil.object_to_json(t2);
				jedis.zadd(key, i, json);
			}
			
			jedis.close();
		} catch (Exception e) {
			System.err.println("redis未启动");
		}
		
		
	}
	

	
	
}
