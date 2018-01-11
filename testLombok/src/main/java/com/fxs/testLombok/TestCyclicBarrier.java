package com.fxs.testLombok;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	public static void main(String[] args) throws Exception{
		CyclicBarrier c = new CyclicBarrier(7,() -> { System.out.println("召唤神龙"); }) ;
		//召唤七科龙珠召唤神龙
		for(int i=1;i<=7;i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"召唤龙珠");
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
			
		}
		
		
	}
}
