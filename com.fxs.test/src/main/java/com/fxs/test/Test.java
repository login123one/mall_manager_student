package com.fxs.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {

		Stacket stacket = new Stacket();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<50;i++) {
					stacket.sale();
				}
			}
		}, "aa").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<50;i++) {
					stacket.sale();
				}
			}
		}, "bb").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<50;i++) {
					stacket.sale();
				}
			}
		}, "cc").start();
		

	}
}

// 线程 操作 资源
class Stacket {
	// 定义要操作的资源
	static int stacketNumber = 30;

	public synchronized void sale() {
		if (stacketNumber > 0) {
			System.out.println(
					Thread.currentThread().getName() + "卖出的票号是 ： " + (stacketNumber--) + "还剩余" + stacketNumber);
		}
	}

}
