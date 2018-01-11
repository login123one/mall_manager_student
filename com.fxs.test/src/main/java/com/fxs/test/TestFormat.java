package com.fxs.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestFormat {
	public static void main(String[] args) throws Exception {
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		
		
//		GregorianCalendar c = new GregorianCalendar(1900, 2, 3, 2, 4);
//		
//		Date time = c.getTime();
//		System.out.println(time);
		
//		LocalDate of = LocalDate.of(1996, 2, 5);
//		
//		LocalTime of2 = LocalTime.of(3, 2, 3);
//		
//		String format = sdf.format(of);
//		
//		System.out.println(of2);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月DD日");
		
		String format = sdf.format(new SimpleDateFormat("yyyyMMDD").parse("19971122"));
		System.out.println(format);
		
		
		
	}
}
