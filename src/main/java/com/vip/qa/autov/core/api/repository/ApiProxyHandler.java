package com.vip.qa.autov.core.api.repository;

import java.lang.reflect.Method;

/**
 * 用于ApiRepoProxy里面封装api真正调用逻辑
 * 
 * @author alex.ma
 *
 * @param <R>
 */
public interface ApiProxyHandler<R> {

	public Object invokeApi(R requester, Object proxy, Method method, Object[] args);

}
