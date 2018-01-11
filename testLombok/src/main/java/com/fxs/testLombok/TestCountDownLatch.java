package com.fxs.testLombok;

import java.util.concurrent.CountDownLatch;
/**
 *让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * 
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 * 
 * 解释：5个同学陆续离开教室后值班同学才可以关门。
 * 也即	秦灭6国，一统华夏
 * main主线程必须要等前面5个线程完成全部工作后，自己才能开干
 * @author zhouyang
 * @version 创建时间：2017年11月22日  下午10:12:33
 */
public class TestCountDownLatch {
	public static void main(String[] args) throws Exception {
		CountDownLatch cd = new CountDownLatch(6);

		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t被灭");
				cd.countDown();
			}, Country.getCountry(i+1).getCuntryName()).start();
		}

		cd.await();

		System.out.println("秦国同一天下");

	}

	public static void counDown() throws InterruptedException {
		CountDownLatch cd = new CountDownLatch(6);

		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t离开教室");
				cd.countDown();
			}, String.valueOf(i)).start();
		}

		cd.await();

		System.out.println("教室关门");
	}
}

enum Country{
	
	ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOURE(4,"韩"),FIVE(5,"赵"),FIX(6,"魏");
	
	private int id;
	private String cuntryName;
	
	
	private Country() {
	}	
	
	private Country(int id, String cuntryName) {
		this.id = id;
		this.cuntryName = cuntryName;
	}

	public static  Country getCountry(int a) {
		for (Country val :values()) {
			int id2 = val.getId();
			if(a == id2) {
				return val;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCuntryName() {
		return cuntryName;
	}



	public void setCuntryName(String cuntryName) {
		this.cuntryName = cuntryName;
	}
	
	
	
	
	
}
