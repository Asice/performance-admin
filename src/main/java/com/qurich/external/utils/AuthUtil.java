package com.qurich.external.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;


public class AuthUtil {

	
	/**
	 * 普通字符加密
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str).toLowerCase();
	}
	
	public static void setCookie(String key, String value, int expireTime, int remember,HttpServletResponse response)
	{
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		if(remember == 1)
			cookie.setMaxAge(expireTime);
		response.addCookie(cookie);
	}
	public static String getCookie(HttpServletRequest request, HttpServletResponse response,
			String name) {
		String value = null;
		try {
			if (name != null) {
				Cookie cookies[] = request.getCookies();
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if (name.equals(cookie.getName())) {
							value = cookie.getValue();
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	public static String formatDateToString(Date date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	public static String formatDateToString(Date date, String format)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	public static Date formatStringToDate(String date) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(date);
	}
	public static Date formatStringToDate(String date, String format) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}
	public static String formatLongToDateString(long dateLong, String format)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date= new Date(dateLong);
		return dateFormat.format(date);
	}
	public static Date getDate(Date date) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(formatDateToString(date));
	}
	public static Map<String, String> getMap(String name, String value)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("value", value);
		return map;
	}
	public static String doubleTrans(double d){
		if(Math.round(d)-d==0){
			return String.valueOf((long)d);
		}
		return String.valueOf(d);
	}
	public static String longToIP(long longIp){
		StringBuffer sb = new StringBuffer("");
		//直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		//将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		//将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		//将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}
	public static String getRand() {
		double rand = Math.random() * 9000 + 1000;
		return String.valueOf((int) Math.floor(rand));
	}
	public static String getToken() {
		int rand = (int) Math.floor(Math.random() * 9000) + 1000;
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString().replace("-", "");
		return String.valueOf(rand + s);
	}
	
}
