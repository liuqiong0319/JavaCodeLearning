package com.vip.qa.autov.core.api.repository;

import org.springframework.beans.factory.FactoryBean;

/**
 * Api Repository类的代理实现的factory bean
 * 
 * @author alex.ma
 *
 */
public class ApiRepoFactoryBean<T> implements FactoryBean<T> {

	private Class<T> repoInterface;

	public ApiRepoFactoryBean() {
	}

	public ApiRepoFactoryBean(Class<T> repoInterface) {
		this.repoInterface = repoInterface;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		return (T) ApiRepoRegistry.getInstance().getRepo(repoInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return this.repoInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<T> getRepoInterface() {
		return repoInterface;
	}

	public void setRepoInterface(Class<T> repoInterface) {
		this.repoInterface = repoInterface;
	}

}
