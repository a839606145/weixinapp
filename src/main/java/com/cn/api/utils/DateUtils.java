package com.cn.api.utils;

import java.util.Date;

public class DateUtils {

	public static Date formateDateByString(String s){
		try{
			long time=Long.parseLong(s);
			return new Date(time);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
}
