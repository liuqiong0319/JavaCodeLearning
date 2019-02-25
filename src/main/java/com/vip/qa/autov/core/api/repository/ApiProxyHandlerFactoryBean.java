package com.vip.qa.autov.core.api.repository;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ApiProxyHandler的factory bean, 动态注入ApiProxyHandler
 * 
 * @author alex.ma
 *
 * @param <R>
 */
public class ApiProxyHandlerFactoryBean<R> implements FactoryBean<ApiProxyHandler<R>>, InitializingBean {

	private Class<ApiProxyHandler<R>> handlerClass;

	private ApiProxyHandler<R> instance;

	public ApiProxyHandlerFactoryBean() {
	}

	public ApiProxyHandlerFactoryBean(Class<ApiProxyHandler<R>> handlerClass) {
		this.handlerClass = handlerClass;
	}

	@Override
	public ApiProxyHandler<R> getObject() throws Exception {
		return instance;
	}

	@Override
	public Class<?> getObjectType() {
		return handlerClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<ApiProxyHandler<R>> getHandlerClass() {
		return handlerClass;
	}

	public void setHandlerClass(Class<ApiProxyHandler<R>> handlerClass) {
		this.handlerClass = handlerClass;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		instance = handlerClass.newInstance();
		// 添加ApiProxyHandler实例到缓存
		Type requesterType = ((ParameterizedType) handlerClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
		ApiProxyHandlerHolder.getInstance().addHandler((Class<?>) requesterType, instance);
	}
}
