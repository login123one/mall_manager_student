package com.fxs.testLombok;

public class SingleDemo {// 恶汉式
	// public static final SingleDemo SingleDemo = new SingleDemo();
	// private SingleDemo(){};

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println(SingleDemo4.getInstance());
		}, "aa").start();
		new Thread(() -> {
			System.out.println(SingleDemo4.getInstance());
		}, "bb").start();
		new Thread(() -> {
			System.out.println(SingleDemo4.getInstance());
		}, "cc").start();
	}
}
	class SingleDemo2 {// 懒汉式
		private SingleDemo2() {
		};

		private static SingleDemo2 instance = null;

		public SingleDemo2 getInstance() {
			if (instance != null) {
				instance = new SingleDemo2();
			}

			return instance;
		}
	}

	enum SingleDemdo3 {// 枚举类型
		INSTANCE;
	}

class SingleDemo4 {// 线程安全的懒汉式
	private static SingleDemo4 instance = null;
	
	private SingleDemo4() {
		System.out.println("***********"+Thread.currentThread().getName());
	}


	public static SingleDemo4 getInstance() {
		if (instance == null) {
			synchronized (SingleDemo4.class) {
				if (instance == null) {
					instance = new SingleDemo4();
				}
			}
		}
		return instance;
	}
	
}
