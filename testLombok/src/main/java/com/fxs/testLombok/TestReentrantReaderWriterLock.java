package com.fxs.testLombok;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写机制  读锁可共享 ，写锁定排他 
//预见情况可以让一个类里面 可以读写都可以 不用加 重锁
//一个人写，一百个人读
public class TestReentrantReaderWriterLock {
	public static void main(String[] args) {
		ShareData5 sd = new ShareData5();
		new Thread(() -> {
			try {
				TimeUnit.NANOSECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sd.write("Object test");
		}, "这是写锁").start();
		
		for(int i=0;i<100;i++) {
			new Thread(() -> {
				sd.read();
			}, String.valueOf(i) ).start();
		}
	}
	
}

class ShareData5{
	Object obj = null;
	ReentrantReadWriteLock wl = new ReentrantReadWriteLock();
	
	
	public void read() { 
		wl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"------读锁"+ obj);
		} finally {
			wl.readLock().unlock();
		}
	}
	public void write(Object obj) {
		
		wl.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"------写锁"+ obj);
		} finally {
			wl.writeLock().unlock();
		}
	}
}


