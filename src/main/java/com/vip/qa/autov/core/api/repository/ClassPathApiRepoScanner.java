package com.vip.qa.autov.core.api.repository;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import com.vip.qa.autov.core.repository.BasicClassPathRepoScanner;

public class ClassPathApiRepoScanner extends BasicClassPathRepoScanner {

	public ClassPathApiRepoScanner(BeanDefinitionRegistry registry) {
		super(registry);
		annotationClass = ApiRepository.class;
		repoFactoryBean = new ApiRepoFactoryBean<Object>();
	}

	@Override
	protected void registerRepoProxy(Class<?> interfaceClass, String requesterBeanName) {
		ApiRepoRegistry.getInstance().addRepo(interfaceClass, requesterBeanName);
	}

}
