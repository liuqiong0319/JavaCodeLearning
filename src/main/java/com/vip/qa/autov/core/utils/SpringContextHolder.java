/**
 * Copyright (c) 2005-2010 springside.org.cn Licensed under the Apache License,
 * Version 2.0 (the "License"); $Id: SpringContextHolder.java 1211 2010-09-10
 * 16:20:45Z calvinxiu $
 */
package com.vip.qa.autov.core.utils;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * implements ApplicationContextAware interface, injection Context in static
	 * variable.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("injection ApplicationContext in SpringContextHolder:" + applicationContext);

		if (SpringContextHolder.applicationContext != null) {
			logger.debug("SpringContextHolder ApplicationContext has been replace,inhere ApplicationContext:"
					+ SpringContextHolder.applicationContext);
		}

		SpringContextHolder.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * implements DisposableBean interface, close Context and clean static
	 * variable.
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clear();
	}

	/**
	 * get static variable from ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static boolean hasBean(String name) {
		try {
			applicationContext.getBean(name);
		} catch (BeansException e) {
			return false;
		}
		return true;
	}

	public static <T> boolean hasBean(Class<T> requiredType) {
		try {
			if (applicationContext == null) {
				return false;
			}
			return applicationContext.getBeansOfType(requiredType).size() > 0;
		} catch (BeansException e) {
			return false;
		}
	}

	/**
	 * get bean from applicationContext.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * get bean from applicationContext.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * get bean from applicationContext.
	 */
	public static <T> Collection<T> getBeans(Class<T> requiredType) {
		assertContextInjected();
		Map<String, T> beans = applicationContext.getBeansOfType(requiredType);
		return beans.values();
	}

	/**
	 * clean ApplicationContext to null at SpringContextHolder.
	 */
	public static void clear() {
		logger.debug("clean ApplicationContext to null at SpringContextHolder:" + applicationContext);
		applicationContext = null;
	}

	/**
	 * check not null ApplicationContext.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext has not injection,please defined SpringContextHolder in applicationContext.xml");
		}
	}

	public static void reload() {
		((AbstractRefreshableApplicationContext) applicationContext).refresh();
	}
}
