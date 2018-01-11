package com.fxs.testLombok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestPointThread {

	public static void main(String[] args) {
		ShareDate1 sd = new ShareDate1();
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.increament();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "aa").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.decreament();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}, "bb").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.increament();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}, "cc").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.decreament();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}, "dd").start();

	}
}

/*
 * 卖票
 */
class ShareDate1 {
	private static int number = 0;
	private ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void increament() throws Exception {
		lock.lock();
		try {
			// 判断
			while (number != 0) {
				// 说明有产量，不需要生产，等待
//				this.wait();
				condition.await();
			}
			// 干活
			++number;
			System.out.println(Thread.currentThread().getName() + "\t剩余：" + number);
			// 唤醒
//			this.notifyAll();
			number = 1;
			condition.signal();
		} finally {
			lock.unlock();
		}

	}

	public void decreament() throws Exception {
		lock.lock();
		try {
			// 判断
			while (number == 0) {
				// 说明没有产量，不能消费，等待
//				this.wait();
				condition.await();
			}
			// 干活
			--number;
			System.out.println(Thread.currentThread().getName() + "\t剩余：" + number);
			// 唤醒
//			this.notifyAll();
			number = 0;
			condition.signal();
		} finally {
			lock.unlock();
		}

	}
}
