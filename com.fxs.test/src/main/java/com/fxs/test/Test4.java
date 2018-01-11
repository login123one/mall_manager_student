package com.fxs.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test4 {
	public static void main(String[] args) {
		Stacket4 stacket4 = new Stacket4();

	
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				stacket4.sale();
			}
		}, "aa").start(); 
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				stacket4.sale();
			}
		}, "bb").start();
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				stacket4.sale();
			}
		}, "cc").start();

	}
}

// 线程 操作 资源
class Stacket4 {
	// 定义要操作的资源
	static int stacketNumber = 100;

	private final Lock lock = new ReentrantLock();

	public  void sale() {

		lock.lock();

		try {
			if (stacketNumber > 0) {
				System.out.println(
						Thread.currentThread().getName() + "卖出的票号是 ： " + (stacketNumber--) + "还剩余" + stacketNumber);
			}

		} finally {
			lock.unlock();
		}
	}
}
