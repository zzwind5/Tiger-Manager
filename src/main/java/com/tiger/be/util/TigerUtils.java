package com.tiger.be.util;

public class TigerUtils {

	public static <T> T createInstance(Class<T> clazz) {
		T instance = null;
		try {
			instance = clazz.newInstance();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance;
	}
	
	public static void recordSysLog(String message) {
		
	}
}
