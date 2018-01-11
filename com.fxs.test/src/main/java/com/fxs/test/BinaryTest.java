package com.fxs.test;

import java.util.Arrays;

public class BinaryTest {

	public static void main(String[] args) {
		int[] array = {1,23,5,57,3,3,8};
		Arrays.sort(array);
		int binary = binary(array, 4);
		System.out.println(binary);
	}

	public static int binary(int[] array, int value) {
		int start =0;
		int end = array.length-1;
		
		while(start <= end) {
			int temp = (start+end)/2;
			if( value  == array[temp]) {
				return value;
			}else if(value  > array[temp]) {
				start = temp+1;
			}else{
				end = temp-1;
			}
		}
		return -1;
	}

}
