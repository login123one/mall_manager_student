package com.fxs.ec.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fxs.ec.MySqlSessionFactory;
import com.fxs.ec.bean.T_MALL_CLASS_1;
import com.fxs.ec.bean.T_MALL_CLASS_2;
import com.fxs.ec.mapper.Class1Mapper;
import com.google.gson.Gson;

public class Test2 {
	public static void main(String[] args) throws Exception {
		//获取sqlsessionFacroty对象
		SqlSessionFactory sqlSessionFactory = MySqlSessionFactory.getSqlSessionFactory();
		//获取一次session回话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//获取mapper
		Class1Mapper mapper = sqlSession.getMapper(Class1Mapper.class);
		//获取分类一的全部数据
		List<T_MALL_CLASS_1> select_class_1 = mapper.select_class_1();
		
		Gson gson = new Gson();
		
		for (T_MALL_CLASS_1 t_MALL_CLASS_1 : select_class_1) {
			List<T_MALL_CLASS_2> select_class_2 = mapper.select_class_2(t_MALL_CLASS_1.getId());
			//转为json对象
			String json = gson.toJson(select_class_2);
			//获取文件输出流
			FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/FinalVersion/class_2_"+t_MALL_CLASS_1.getId()+".js"));
			
			fileOutputStream.write(json.getBytes("utf-8"));
			
			System.out.println(json);
		}
		
		

	
		
		
		
	}
	

}
