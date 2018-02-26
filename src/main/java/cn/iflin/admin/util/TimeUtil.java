package cn.iflin.admin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 判断时间戳是否在范围内
 * 
 * @author Panzejia
 *
 */
public class TimeUtil {
	public static void main(String[] args) {
		System.out.println(isInRangeDay("2017-12-19 00:00:00",7));
	}
	/**
	 * 判断一个时间是否在当前时间的N天内
	 * @param day 一个时间
	 * @param dayNum N天前
	 * @return
	 */
	public static boolean isInRangeDay(String day,int dayNum){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, -dayNum);
		Date beforeDays = calendar.getTime();
		String before = sdf.format(beforeDays);
		if(day.contains(before)){
			return true;
		}
		return false;
	}
	/**
	 * 获取当前时间 返回 年月日
	 */
	public static String getNowTime(){
		Date date = new Date();
		DateFormat f = new SimpleDateFormat("yy-MM-dd");
		String time = f.format(date);
		return time;
	}
	/**
	 * 判断是否是新的一天
	 */
	public static boolean isNewDay() {
		Date date = new Date();
		DateFormat f = new SimpleDateFormat("HH");
		String time = f.format(date);
		if (time.equals("00") || time.equals("0")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是新的一周
	 */
	public static boolean isNewWeek() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		if (w == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是月末
	 */
	public static boolean isNewMonth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}
	/**
	 * 判断是否到了新的一季
	 */
	public static boolean isNewSeason() {
		Date date = new Date();
		DateFormat f = new SimpleDateFormat("MM-dd");
		String time = f.format(date);
		System.out.println(time);
		if (time.equals("01-01") || time.equals("04-01")|| time.equals("07-01")|| time.equals("10-01")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否到了新的一年
	 */
	public static boolean isNewYear() {
		Date date = new Date();
		DateFormat f = new SimpleDateFormat("MM-dd");
		String time = f.format(date);
		System.out.println(time);
		if (time.equals("01-01")) {
			return true;
		}
		return false;
	}
}
