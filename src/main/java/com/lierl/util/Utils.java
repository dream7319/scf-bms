package com.lierl.util;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateFormatUtils;

public class Utils {

	public static final String SECRET = "abcoop";

	private static final String HMAC_SHA256 = "HmacSHA256";

	public static String getRandomFileName() {
		String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return str + rannum;// 当前时间
	}

	public static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}

	public static String getHashPath(long id) {
		long first = id % 10;
		long second = (id / 10) % 10;
		return first + File.separator + second + File.separator;
	}

	public static String hashHmac(String data, String secret) {
		try {
			Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
			sha256_HMAC.init(secret_key);
			return Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	public static Date toDate(String date, String pattern) {
		try {
			date = date.replace("\"", "");
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean write(HttpServletResponse response, int status, String message) {
		try {
			response.setStatus(status);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getFirstDayOfWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week);
		return DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
	}

	public static String getLastDayOfWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 6);
		return DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
	}

	public static String getMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
	}

	public static String formatCurrency(double d, Locale l) {
		String s = "";
		try {
			DecimalFormat nf = (DecimalFormat) NumberFormat.getCurrencyInstance(l);
			// nf.applyPattern(pattern);
			s = nf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static double cumulativeProbability(double[] array, double val) {
		double index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == val) {
				index = (i + 1);
				break;
			}
		}
		return (index / array.length);
	}


	public static String formart(double number, int digits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(digits);
		nf.setRoundingMode(RoundingMode.UP);
		return nf.format(number);
	}


	public static Long getDiffDay(Date beginDate, Date endDate) {
		return (endDate.getTime() - beginDate.getTime()) / (1000 * 24 * 60 * 60);
	}

	public static Long getDiffDay(long beginDate, long endDate) {
		return (beginDate - endDate) / (1000 * 24 * 60 * 60);
	}

	public static String getCategory(String str) {
		Matcher ma = Pattern.compile("[A-Z]+").matcher(str);
		while(ma.find())
			return ma.group();
		return str;
	}
	
	private static Calendar calendar;
	public static int getWeekOfYear(Date date){
		if(calendar==null){
			calendar = Calendar.getInstance();  
	        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		}
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	public static int getWeekOfYear(long millis){
		if(calendar==null){
			calendar = Calendar.getInstance();  
	        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		}
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.WEEK_OF_YEAR);
	}
}
