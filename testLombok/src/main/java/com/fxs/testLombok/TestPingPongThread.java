package com.fxs.testLombok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestPingPongThread {

	public static void main(String[] args) {
		A1 a = new A1();
		new Thread(() -> {
			for (int i = 1; i <= 52; i++) {
				try {
					a.print1(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "线程1").start();
		new Thread(() -> {
			for (int i = 1; i <= 26; i++) {
				try {
					a.printA("ccc");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "线程2").start();

		// ShareDate sd = new ShareDate();

		// new Thread(() -> {
		// for (int i = 0; i < 10; i++) {
		// try {
		// sd.increament();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }, "aa").start();
		// new Thread(() -> {
		// for (int i = 0; i < 10; i++) {
		// try {
		// sd.decreament();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// }, "bb").start();
		// new Thread(() -> {
		// for (int i = 0; i < 10; i++) {
		// try {
		// sd.increament();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// }, "cc").start();
		// new Thread(() -> {
		// for (int i = 0; i < 10; i++) {
		// try {
		// sd.decreament();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// }, "dd").start();

	}

}

/*
 * 卖票
 */
class ShareDate {
	private static int number = 0;
	private Lock lock = new ReentrantLock();

	public synchronized void increament() throws Exception {
		try {
			// 判断
			while (number != 0) {
				// 说明有产量，不需要生产，等待
				this.wait();
			}
			// 干活
			++number;
			System.out.println(Thread.currentThread().getName() + "\t剩余：" + number);
			// 唤醒
			this.notifyAll();
		} finally {
		}

	}

	public synchronized void decreament() throws Exception {
		try {
			// 判断
			while (number == 0) {
				// 说明没有产量，不能消费，等待
				this.wait();
			}
			// 干活
			--number;
			System.out.println(Thread.currentThread().getName() + "\t剩余：" + number);
			// 唤醒
			this.notifyAll();
		} finally {
		}

	}
}

class A1 {
	ReentrantLock lock = new ReentrantLock();
//	int number= 0 ;
//	
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();

	public void printA(String a) throws InterruptedException {

		lock.lock();
		try {
			c1.await();
			System.out.println(a);
			c2.signal();
		} finally {
			lock.unlock();
		}
	}

	public void print1(int a) throws Exception {
		lock.lock();
		try {
			if(a!=1) {
				if(a%2==1) {
					//说明等于奇数 不打印 ，让行
					c2.await();
				}
				
				System.out.println(a);
				if(a%2==0) {
					c1.signal();
				}
				
			}else {
				System.out.println(a);
			}
		} finally {
			lock.unlock();
		}
	}
}
