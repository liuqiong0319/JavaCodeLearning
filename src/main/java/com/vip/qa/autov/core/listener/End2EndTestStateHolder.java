package com.vip.qa.autov.core.listener;

/**
 * 主要是跑e2e的时候某个用例挂了不要继续往下跑，End2EndMethodInvokeListener会每次开始执行时设置这里的状态
 * 
 * @author alex.ma
 *
 */
public class End2EndTestStateHolder {

	private static ThreadLocal<Boolean> testStatusLocal = new ThreadLocal<>();

	public static void setTestStarted() {
		testStatusLocal.set(true);
	}

	public static boolean isFirstTest() {
		return testStatusLocal.get() == null;
	}

	public static void setTestEnd() {
		testStatusLocal.remove();
	}
}
