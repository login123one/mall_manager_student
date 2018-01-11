package com.fxs.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
	public static void main(String[] args) {
		Stacket stacket2 = new Stacket();

		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {
					stacket2.sale();
				}
			}
		}, "AA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {
					stacket2.sale();
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {
					stacket2.sale();
				}
			}
		}, "CC").start();
	}

}

// 线程 操作 资源
class Stacket2 {
	// 定义要操作的资源
	static int stacketNumber = 30;

	private final Lock lock = new ReentrantLock();

	public void sale() {

		lock.lock();

		try {
			System.out.println(
					Thread.currentThread().getName() + "卖出的票号是 ： " + (stacketNumber--) + "还剩余" + stacketNumber);

		} finally {
			lock.unlock();
		}
	}
}