package org.qwan.jpa.util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeUtil {

	public static String get(){
		LocalDate date = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd H");
		String timeNow = date.toString(fmt);
		
		return timeNow;
	}
	
	public static void main2(String[] args){
		String a = get();
		
		System.out.println(a);
	}
}
