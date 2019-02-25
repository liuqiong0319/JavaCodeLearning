package com.vip.qa.autov.core.api.repository;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 维护ApiRepository接口类和动态代理实现类的对应关系
 * 
 * @author alex.ma
 *
 */

public class ApiRepoRegistry {

	private final Map<Class<?>, Object> knownRepos = new HashMap<Class<?>, Object>();

	private static ApiRepoRegistry instance = new ApiRepoRegistry();

	private ApiRepoRegistry() {
	}

	public static ApiRepoRegistry getInstance() {
		return instance;
	}

	Object getRepo(Class<?> interfaceClass) {
		Object repoInstance = knownRepos.get(interfaceClass);
		return repoInstance;
	}

	void addRepo(Class<?> interfaceClass, String requesterBeanName) {
		Object repoInstance = getApiRepoInstance(interfaceClass, requesterBeanName);
		knownRepos.put(interfaceClass, repoInstance);
	}

	private Object getApiRepoInstance(Class<?> interfaceClass, String requesterBeanName) {
		return Proxy.newProxyInstance(ApiRepoProxy.class.getClassLoader(), new Class[] { interfaceClass },
				new ApiRepoProxy(requesterBeanName));
	}
}
