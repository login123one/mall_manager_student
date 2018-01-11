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
import com.fxs.ec.mapper.Class1Mapper;
import com.google.gson.Gson;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		//获取sqlsessionFacroty对象
		SqlSessionFactory sqlSessionFactory = MySqlSessionFactory.getSqlSessionFactory();
		//获取一次session回话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//获取mapper
		Class1Mapper mapper = sqlSession.getMapper(Class1Mapper.class);
		//获取分类一的全部数据
		List<T_MALL_CLASS_1> select_class_1 = mapper.select_class_1();
		
		
		//转为json对象
		Gson gson = new Gson();
		String json = gson.toJson(select_class_1);
		//获取文件输出流
		
		FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/FinalVersion/class_1.js"));
		
		try {
			fileOutputStream.write(json.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(json);
		
	}
	

}
