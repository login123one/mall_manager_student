package com.atguigu;

public class Test {
	public static void main(String[] args) {
		
		Student student = new Student();
		Class<? extends Student> class2 = student.getClass();
		System.out.println(class2);
		
		ClassLoader classLoader = class2.getClassLoader();
		System.out.println(classLoader);
		
		
		
		try {
			Class<?> forName = Class.forName("com.atguigu.Teacher");
			ClassLoader classLoader2 = forName.getClassLoader();
			System.out.println(classLoader2);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
		Class<?> class1 = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.Student");
		System.out.println(class1);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(Class.class);
	}
}
class Student {
	private String name = "aa";
}
class Teacher{
	
}
