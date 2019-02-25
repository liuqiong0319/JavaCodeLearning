package com.vip.qa.autov.core.repository;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.ReflectionUtils;

public abstract class BasicClassPathRepoScanner extends ClassPathBeanDefinitionScanner {

	protected Class<? extends Annotation> annotationClass;

	protected FactoryBean<?> repoFactoryBean;

	protected Map<String, String> requesterPackageMap;

	protected Map<Class<?>, String> requesterClassMap;

	public BasicClassPathRepoScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

		if (beanDefinitions.isEmpty()) {
			logger.warn("No repo was found in '" + Arrays.toString(basePackages)
					+ "' package. Please check your configuration.");
		} else {
			try {
				processBeanDefinitions(beanDefinitions);
			} catch (Exception e) {
				Exceptions.checked(e);
			}
		}

		return beanDefinitions;
	}

	public void registerFilters() {

		// if specified, use the given annotation and / or marker interface
		addIncludeFilter(new AnnotationTypeFilter(annotationClass));

		// exclude package-info.java
		addExcludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});
	}

	protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) throws Exception {
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();

			if (logger.isDebugEnabled()) {
				logger.debug("Creating Bean with name '" + holder.getBeanName() + "' and '"
						+ definition.getBeanClassName() + "' interface");
			}
			String interfaceName = definition.getBeanClassName();
			definition.getConstructorArgumentValues().addGenericArgumentValue(interfaceName);

			Class<?> interfaceClass = getInterfaceClass(interfaceName);

			String requesterBeanName = getRequesterBeanName(interfaceClass);
			registerRepoProxy(interfaceClass, requesterBeanName);
			definition.setBeanClass(repoFactoryBean.getClass());

			String beanName = getBeanName(interfaceClass);
			if (StringUtils.isNoneBlank(beanName)) {
				AutowireCandidateQualifier qualifier = new AutowireCandidateQualifier(beanName);
				definition.addQualifier(qualifier);
			}
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}
	}

	protected abstract void registerRepoProxy(Class<?> interfaceClass, String requesterBeanName);

	private Class<?> getInterfaceClass(String interfaceName) {
		Class<?> interfaceClass = null;
		try {
			interfaceClass = Class.forName(interfaceName);
		} catch (ClassNotFoundException e) {
			Exceptions.checked(e);
		}
		return interfaceClass;
	}

	/**
	 * 扫描requesterPackageMap参数的包名，并找出最匹配的Requester的bean
	 * 
	 * @param interfaceClass
	 * @return
	 * @throws Exception
	 */
	protected String getRequesterBeanName(Class<?> interfaceClass) throws Exception {

		Annotation anno = interfaceClass.getAnnotation(annotationClass);
		String requesterName = (String) ReflectionUtils.invokeMethod(anno, "requesterName", new Class<?>[] {},
				new Object[] {});
		if (StringUtils.isNotBlank(requesterName)) {
			return requesterName;
		}
		if (requesterClassMap != null) {
			requesterName = requesterClassMap.get(interfaceClass);
			if (StringUtils.isNotBlank(requesterName)) {
				return requesterName;
			}
		}
		if (requesterPackageMap != null) {
			String packageName = interfaceClass.getPackage().getName();
			List<String> keys = new ArrayList<>(requesterPackageMap.keySet());
			Collections.sort(keys, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1);
				}

			});
			for (String key : keys) {
				if (packageName.startsWith(key)) {
					return requesterPackageMap.get(key);
				}
			}
		}
		throw new IllegalArgumentException("Requester bean with name " + requesterName + "["
				+ interfaceClass.getSimpleName() + "] does not exist");
	}

	private String getBeanName(Class<?> interfaceClass) throws Exception {
		Annotation anno = interfaceClass.getAnnotation(annotationClass);
		String beanName = (String) ReflectionUtils.invokeMethod(anno, "value", new Class<?>[] {}, new Object[] {});
		return beanName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
		if (super.checkCandidate(beanName, beanDefinition)) {
			return true;
		} else {
			logger.warn("Skipping Bean with name '" + beanName + "' and '" + beanDefinition.getBeanClassName()
					+ "' interface" + ". Bean already defined with the same name!");
			return false;
		}
	}

	public Map<String, String> getRequesterPackageMap() {
		return requesterPackageMap;
	}

	public void setRequesterPackageMap(Map<String, String> requesterPackageMap) {
		this.requesterPackageMap = requesterPackageMap;
	}

	public Map<Class<?>, String> getRequesterClassMap() {
		return requesterClassMap;
	}

	public void setRequesterClassMap(Map<Class<?>, String> requesterClassMap) {
		this.requesterClassMap = requesterClassMap;
	}
}
