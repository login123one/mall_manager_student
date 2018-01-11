package com.fxs.testLombok;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {
	public static void main(String[] args) {
		Semaphore  sh = new Semaphore(3);
		//模拟6辆车 进入 三个车位
		for(int i=1;i<=6;i++) {
			new Thread(() -> {
				try {
					sh.acquire();//如果调用这个方法，这必须获取一个信号，否则当前线程阻塞
					System.out.println(Thread.currentThread().getName()+ "抢的停车位");
					int time = new Random().nextInt(10);
					System.out.println(Thread.currentThread().getName()+ "停留的时间："+time);
					
					TimeUnit.SECONDS.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					System.out.println(Thread.currentThread().getName()+ "离开停车位");
					sh.release();
				}
				
				
			}, String.valueOf(i)).start();
		}
		
	}
}
