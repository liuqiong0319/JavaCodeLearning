package com.vip.qa.autov.core.context;

import java.lang.reflect.Method;

public class TestMethodHolder {

	private static ThreadLocal<Method> methodLocal = new ThreadLocal<Method>();

	public static Method get() {
		return methodLocal.get();
	}

	public static void set(Method method) {
		methodLocal.set(method);
	}
}
