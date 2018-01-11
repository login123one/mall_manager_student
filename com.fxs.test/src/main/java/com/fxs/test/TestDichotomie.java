package com.fxs.test;

import java.util.Arrays;

public class TestDichotomie {
	public static void main(String[] args) {

		int[] s = new int[] { 1, 6, 7, 7, 28, 52, 32, 71, 25 };
		Arrays.sort(s);
		for (int i : s) {
			System.out.println(i);
		}

		// 测试二分法
		int temp = 5;
		int start = 0;
		int end = s.length - 1;
		
		
		int count =0;
		
		while (start <= end) {
			count+=1;
			int a = (start + end) / 2;
			if (temp == s[a]) {
				System.out.println("下坐标为 : " + a);
				break ;
			} else if (temp >= s[a]) {
				// 说明在数组的有半部分
				start = a +1;
			} else {
				// 说明在数组的左半部
				end = a -1;
			}
		}
		System.out.println(444);

	}
}
