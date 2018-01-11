package com.fxs.testLombok;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestCallable {
	public static void main(String[] args) throws Exception {
		MyThred myThred = new MyThred();
		FutureTask<Integer> fu = new FutureTask<>(myThred);

		new Thread(fu,"aa").start();
		new Thread(fu,"BB").start();
		new Thread(fu,"CC").start();
		
		
		int a = 3; 
		System.out.println(a);
		
		Integer integer = fu.get();
		System.out.println(integer+3);

	}
}

class MyThred implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		TimeUnit.SECONDS.sleep(3);
		return 4;
	}

}
