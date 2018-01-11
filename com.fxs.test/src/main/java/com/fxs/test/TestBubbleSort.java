package com.fxs.test;

public class TestBubbleSort {
	static int[] a = { 5, 9, 3, 4,9, 6,8 };

	static int temp = 0;
	public static void main(String[] args) {
		BubbleSort(a);
		
		for (int  b : a) {
			System.out.println(b);
		}
		
		
	}
	public static void BubbleSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
			
//			for(int j = a.length-1;j>i;j--) {
//				if (a[j-1] > a[j]) {
//					temp = a[j - 1];
//					a[j - 1] = a[j];
//					a[j] = temp;
//				}
//			}
		}
	}
}
