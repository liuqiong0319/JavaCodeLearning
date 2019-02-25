package com.vip.qa.autov.core.repository;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.Assert;

public abstract class BasicRepoScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean,
		ApplicationContextAware, BeanNameAware {

	protected String basePackage;

	protected String beanName;

	protected ApplicationContext applicationContext;

	protected Map<String, String> requesterPackageMap;

	protected Map<Class<?>, String> requesterClassMap;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.basePackage, "Property 'basePackage' is required");
	}

	/*
	 * BeanDefinitionRegistries are called early in application startup, before
	 * BeanFactoryPostProcessors. This means that PropertyResourceConfigurers
	 * will not have been loaded and any property substitution of this class'
	 * properties will fail. To avoid this, find any PropertyResourceConfigurers
	 * defined in the context and run them on this class' bean definition. Then
	 * update the values.
	 */
	protected void processPropertyPlaceHolders() {
		Map<String, PropertyResourceConfigurer> prcs = applicationContext
				.getBeansOfType(PropertyResourceConfigurer.class);

		if (!prcs.isEmpty() && applicationContext instanceof GenericApplicationContext) {
			BeanDefinition mapperScannerBean = ((GenericApplicationContext) applicationContext).getBeanFactory()
					.getBeanDefinition(beanName);

			// PropertyResourceConfigurer does not expose any methods to
			// explicitly perform
			// property placeholder substitution. Instead, create a BeanFactory
			// that just
			// contains this mapper scanner and post process the factory.
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			factory.registerBeanDefinition(beanName, mapperScannerBean);

			for (PropertyResourceConfigurer prc : prcs.values()) {
				prc.postProcessBeanFactory(factory);
			}

			PropertyValues values = mapperScannerBean.getPropertyValues();

			String updatedVaue = updatePropertyValue("basePackage", values);
			if (updatedVaue != null) {
				this.basePackage = updatedVaue;
			}
		}
	}

	private String updatePropertyValue(String propertyName, PropertyValues values) {
		PropertyValue property = values.getPropertyValue(propertyName);

		if (property == null) {
			return null;
		}

		Object value = property.getValue();

		if (value == null) {
			return null;
		} else if (value instanceof String) {
			return value.toString();
		} else if (value instanceof TypedStringValue) {
			return ((TypedStringValue) value).getValue();
		} else {
			return null;
		}
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public Map<String, String> getRequesterPackageMap() {
		return requesterPackageMap;
	}

	/**
	 * 指定扫描requesterPackageMap参数的包名，并找出最匹配的Requester的bean
	 * 
	 * @param requesterPackageMap
	 *            key为包名（含子包）,value为requester 的bean name
	 * @return
	 */
	public void setRequesterPackageMap(Map<String, String> requesterPackageMap) {
		this.requesterPackageMap = requesterPackageMap;
	}

	public Map<Class<?>, String> getRequesterClassMap() {
		return requesterClassMap;
	}

	/**
	 * 指定某个类名使用某个指定的requester bean
	 * 
	 * @param requesterClassMap
	 *            key为repo接口类名,value为requester 的bean name
	 * @return
	 */
	public void setRequesterClassMap(Map<Class<?>, String> requesterClassMap) {
		this.requesterClassMap = requesterClassMap;
	}

}
