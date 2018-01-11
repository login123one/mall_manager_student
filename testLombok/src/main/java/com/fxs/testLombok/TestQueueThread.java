package com.fxs.testLombok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestQueueThread {
	public static void main(String[] args) {
		ShareData4 sd = new ShareData4();
		
		new Thread(() -> {
			for(int i=0;i< 5;i++) {
				try {
					sd.printA();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}, "aaa").start();
		new Thread(() -> {
			for(int i=0;i< 5;i++) {
				try {
					sd.printB();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}, "bbbb").start();
		new Thread(() -> {
			for(int i=0;i< 5;i++) {
				try {
					sd.printC();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}, "ccc").start();
	}
	

}

class ShareData4 {
	int number = 1;
	Lock lock = new ReentrantLock();
	
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void printA() throws Exception {
		lock.lock();
		try {
			//判断
			if(number != 1 ) {
				//不等1，就等待
				c1.await();
			}
			//干活
			for(int i=0;i<5;i++) {
				System.out.println(Thread.currentThread().getName() +"第" + (i+1) + "个AA");
			}
			number =2;
			//唤醒
			c2.signal();
		} finally {
			lock.unlock();
		}
		
	}
	public void printB() throws Exception {
		lock.lock();
		try {
			//判断
			if(number != 2 ) {
				//不等2，就等待
				c2.await();
			}
			//干活
			for(int i=0;i<10;i++) {
				System.out.println(Thread.currentThread().getName() +"第" + i + "个BB");
			}
			number = 3;
			//唤醒
			c3.signal();
		} finally {
			lock.unlock();
		}
	}
	public void printC() throws Exception {
		lock.lock();
		try {
			//判断
			if(number != 3 ) {
				//不等1，就等待
				c3.await();
			}
			//干活
			for(int i=0;i<15;i++) {
				System.out.println(Thread.currentThread().getName() +"第" + i + "个CC");
			}
			number = 1;
			//唤醒
			c1.signal();
		} finally {
			lock.unlock();
		}
	}
}