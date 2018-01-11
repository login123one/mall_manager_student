package com.fxs.test;

public class TestMultifly {
	public static void main(String[] args) {
		
		System.out.println(multifly(1));
		
	}

	private static long multifly(int i) {
		
		if(i<0) {
			System.out.println("请输入大于0的数");
			return -1;
		}else {
			if(i==0 || i ==1 ) {
				return 1;
			}else {
				return i* multifly(i-1);
			}
		}
		
	}
}
