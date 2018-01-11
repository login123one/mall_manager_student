package com.fxs.testLombok;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutorService {
	public static void main(String[] args) {
		//1个银行5个受理窗口，接受15个客户来办理业务
		ExecutorService service = Executors.newFixedThreadPool(3);
//		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> d = null;
		try {
			
			for(int i=0;i<15;i++ ) {
				int temp = i;
				d = service.submit(() -> {
					System.out.println(Thread.currentThread().getName() +"处理的结果是" + temp);
					return new Random().nextInt(10);
				});
				
//				System.out.println("----结果是 ： " + i);
			}
		} finally {
			service.shutdown();
		}
		
	}
}

