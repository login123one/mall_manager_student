package com.fxs.testLombok;

//@FunctionalInterface
interface LambdaDemo1{
//	public int add(int a, int b);
	
	default int div(int a ,int b) {
		return a/b;
	} 
}

public class LambdaDemoTest {
	public static void main(String[] args) {
//		LambdaDemo1 foo = (int a,int b) -> { return a +b ;};
//		int add = foo.add(2, 3);
//		System.out.println(add);
		LambdaDemo1 foo = new LambdaDemo1() {};
		int div = foo.div(10, 5);
		System.out.println(div);
	}
}
