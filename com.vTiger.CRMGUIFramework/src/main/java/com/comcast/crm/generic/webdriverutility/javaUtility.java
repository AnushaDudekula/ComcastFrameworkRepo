package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class javaUtility {
public int getRandomNumber() {
	Random r=new Random();
	int n=r.nextInt(5000);
	return n;
}
public String getSystemDate_yyyy_mm_dd() {
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String actdate=sdf.format(date);
	return actdate;
}
public String getRequiredDate(int days) {
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String actdate=sdf.format(date);
	Calendar cal=sdf.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String dateRequries=sdf.format(cal.getTime());
	return dateRequries;
}
//public static void main(String[] args) {
//	javaUtility j=new javaUtility();
//	System.out.println(j.getSystemDate_yyyy_mm_dd());
//	System.out.println(j.getRequiredDate(30));
//}
}
//Date date=new Date();
//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//String actdate=sdf.format(date);
//Calendar cal=sdf.getCalendar();
//cal.add(Calendar.DAY_OF_MONTH,30);
//String dateRequries=sdf.format(cal.getTime());