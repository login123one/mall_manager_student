package com.fxs.test;

public class TestGearbageCollection {
	public static void main(String[] args) {
//		long maxMemory = Runtime.getRuntime().maxMemory();
//		System.out.println(maxMemory+"默认的最大内存为："+(maxMemory/(double)1024/1024)+"MB");
//		long totalMemory = Runtime.getRuntime().totalMemory();
//		System.out.println(totalMemory+"开始默认使用的内存"+(totalMemory/(double)1024/1024)+"MB");
		
		String str = "www.atguigu.com" ;
		while(true) {
			str +=str;
		}
		
	}
}
