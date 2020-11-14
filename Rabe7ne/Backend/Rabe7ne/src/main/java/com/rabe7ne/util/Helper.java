package com.rabe7ne.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Base64Utils;

public class Helper {
	
	public static Map<String, Object> error() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", true);
		return model;
	}

	public static Result checkRequiredParams(Result result, Object ... params) {
		for(Object param : params)
			if(param == null || param.toString().trim().isEmpty())
				return result.error("0000");
		return result;
	}
	
	public static String replaceVariables(String str, Map<String, Object> values) {
		if(str == null)
			return null;
		if(values == null || str.length() < 3)
			return str;
		StringBuilder stringBuilder = new StringBuilder();
		int l = str.length() - 1, i = 0;
		for(int j; i < l; i++) {
			if(str.charAt(i) != '$' || str.charAt(i + 1) != '{') {
				stringBuilder.append(str.charAt(i));
				continue;
			}
			j = i + 2;
			for(i = j; i <= l && str.charAt(i) != '}'; i++);
			if(i > l) {
				stringBuilder.append(str.substring(j - 2));
				break;
			}
			stringBuilder.append(values.get(str.substring(j, i)));
		}
		if(i == l)
			stringBuilder.append(str.charAt(l));
		return stringBuilder.toString();
	}
	
	public static String createRandomCode(int length, CharInterval ... charIntervals) {
		List<Character> charList = new LinkedList<Character>();
		if(charIntervals != null)
			for(CharInterval charInterval : charIntervals)
				if(charInterval != null)
		            for(char ch = charInterval.getFrom(); ch <= charInterval.getTo(); ch++)
			            charList.add(ch);
		char[] randomCharArray = new char[charList.size()];
		for(int i = 0; i < randomCharArray.length; i++)
			randomCharArray[i] = charList.remove((int) (Math.random() * (double) charList.size()));
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
			stringBuilder.append(randomCharArray[(int) (Math.random() * (double) randomCharArray.length)]);
		return stringBuilder.toString();
	}
	
	public static long calculateDateDiff(Date from, Date to, int calendarField) {
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTimeInMillis(from.getTime());
		Calendar toCal = Calendar.getInstance();
		toCal.setTimeInMillis(to.getTime());
		return toCal.get(calendarField) - fromCal.get(calendarField);
	}
	
	public static int compareDates(Date date1, Date date2) {
		Calendar date1Cal = Calendar.getInstance();
		date1Cal.setTime(date1);
		Calendar date2Cal = Calendar.getInstance();
		date2Cal.setTime(date2);
		int diff = date1Cal.get(Calendar.YEAR) - date2Cal.get(Calendar.YEAR);
		if(diff != 0)
			return diff;
		diff = date1Cal.get(Calendar.MONTH) - date2Cal.get(Calendar.MONTH);
		if(diff != 0)
			return diff;
		return date1Cal.get(Calendar.DAY_OF_MONTH) - date2Cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Date getDayStart(Date date) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		dateCal.set(Calendar.HOUR_OF_DAY, dateCal.getMinimum(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, dateCal.getMinimum(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, dateCal.getMinimum(Calendar.SECOND));
		dateCal.set(Calendar.MILLISECOND, dateCal.getMinimum(Calendar.MILLISECOND));
		return dateCal.getTime();
	}
	
	public static Date getDayHalf(Date date) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		dateCal.set(Calendar.HOUR_OF_DAY, 12);
		dateCal.set(Calendar.MINUTE, dateCal.getMinimum(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, dateCal.getMinimum(Calendar.SECOND));
		dateCal.set(Calendar.MILLISECOND, dateCal.getMinimum(Calendar.MILLISECOND));
		return dateCal.getTime();
	}

	public static Date getDayEnd(Date date) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		dateCal.set(Calendar.HOUR_OF_DAY, dateCal.getMaximum(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, dateCal.getMaximum(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, dateCal.getMaximum(Calendar.SECOND));
		dateCal.set(Calendar.MILLISECOND, dateCal.getMaximum(Calendar.MILLISECOND));
		return dateCal.getTime();
	}
	
	public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
	
	public static Result checkPaginationStartAndLength(Result result, Integer start, Integer length) {
		if(start == null || length == null || start < 0 || length < 1 || length > 50)
			return result.error("0000");
		return result;
	}
	
	public static boolean isEquals(Object value1, Object value2) {
		if(value1 == null)
			return value2 == null;
		if(value2 == null)
			return false;
		return value1 == value2 || value1.equals(value2);
	}
	
	public static Double calculatePriceAfterDiscount(Double price, Byte discountPercentage) {
		Double discountValue = Math.round(price * discountPercentage.doubleValue()) / 100.0;
		return price - discountValue;
	}
	
	public static String getBaseUrlFromDomain(String domain) {
		String baseUrl = null;
		if(domain.startsWith("http"))
			baseUrl = domain.substring(domain.indexOf("//")+2, domain.length());
		else
			baseUrl = domain;
		if(baseUrl.contains("/"))
			baseUrl = baseUrl.substring(0, baseUrl.indexOf("/"));
		if(baseUrl.endsWith("/"))
			baseUrl = baseUrl.substring(0,baseUrl.length()-1);
		return baseUrl;
	}
	
	public static String getGlobalPhone(String countryCallingCode, String localPhone) {
		String globalPhone = "+" + countryCallingCode.replaceAll(" ", "");
		int i = 0;
		while(i < localPhone.length() && localPhone.charAt(i) == '0')
			i++;
		if(i < localPhone.length())
			globalPhone += localPhone.substring(i);
		return globalPhone;
	}
	
	public static String encodeBase64(String str) {
		if(str == null)
			return null;
		return Base64Utils.encodeToString(str.getBytes());
	}

	public static String decodeBase64(String str) {
		if(str == null)
			return null;
		return new String(Base64Utils.decodeFromString(str));
	}
}
