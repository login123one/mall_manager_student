package com.fxs.testLombok;


/**
 * 笔记
一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法

加个普通方法后发现和同步锁无关
换成两个对象后，不是同一把锁了，情况立刻变化。


都换成静态同步方法后，情况又变化
所有的非静态同步方法用的都是同一把锁——实例对象本身，

也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

所有的静态同步方法用的也是同一把锁——类对象本身，这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 *
 */




/*
* @author zhouyang
*多线程的锁（8锁）
*1	标准访问，先打印苹果还是Android
*2 新增暂停4秒钟方法，先打印苹果还是Android
*3 新增hello方法，先打印苹果还是hello
*4 有两部手机，先打印苹果还是Android
*5 两个静态同步方法，有一部手机，先打印苹果还是Android
*6 两个静态同步方法，有两部手机，先打印苹果还是Android
*7 1个普通同步方法，1个静态同步方法，有1部手机，先打印苹果还是Android
*8 1个普通同步方法，1个静态同步方法，有2部手机，先打印苹果还是Android
*/
public class Test8Lock {
	public static void main(String[] args){
		ShareDate3 shareDate1 = new ShareDate3();
		ShareDate3 shareDate2 = new ShareDate3();

		new Thread(() -> {

			try {
				shareDate1.getApple();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "aa").start();

		new Thread(() -> {
			shareDate2.getAndroid();
		}, "bb").start();
		
//		new Thread(() -> {
//			shareDate1.getHello();
//		}, "cc").start();
	}
}

class ShareDate3 {
	public  synchronized void getApple() throws Exception {
		Thread.sleep(1000*3);
		System.out.println("*********打印  Apple   苹果");
	}
 
	public synchronized void getAndroid() {
		System.out.println("*********打印 Android   安卓");
	}
	
	public void getHello(){
		System.out.println("*********打印Hello   你好");
	}
}
