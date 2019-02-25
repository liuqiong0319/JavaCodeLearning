package com.vip.qa.autov.core.context;

public class LogAppendixHolder {

	private static ThreadLocal<String> idxLocal = new ThreadLocal<String>();

	public static String get() {
		return idxLocal.get();
	}

	public static void set(String idx) {
		idxLocal.set(idx);
	}
}
