package com.fxs.testLombok;


public class SingletonDemo {// 线程安全的懒汉式
		private static SingletonDemo instance = null;
		
		private SingletonDemo() {
			System.out.println("***********"+Thread.currentThread().getName());
		}


		public static SingletonDemo getInstance() {
			if (instance == null) {
				synchronized (SingleDemo4.class) {
					if (instance == null) {
						instance = new SingletonDemo();
					}
				}
			}
			return instance;
		}
		
}
