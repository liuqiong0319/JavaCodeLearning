package com.vip.qa.autov.core.api.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 维持ApiRequester实现类和ApiProxyHandler实例的对应关系
 * 
 * @author alex.ma
 *
 */
public class ApiProxyHandlerHolder {

	private static ApiProxyHandlerHolder instance = new ApiProxyHandlerHolder();

	private Map<Class<?>, ApiProxyHandler<?>> classHandlerMap = new HashMap<Class<?>, ApiProxyHandler<?>>();

	private ApiProxyHandlerHolder() {
	}

	public static ApiProxyHandlerHolder getInstance() {
		return instance;
	}

	public ApiProxyHandler<?> getHandler(Class<?> requesterClass) {
		return classHandlerMap.get(requesterClass);
	}

	public void addHandler(Class<?> requesterClass, ApiProxyHandler<?> handler) {
		classHandlerMap.put(requesterClass, handler);
	}
}
