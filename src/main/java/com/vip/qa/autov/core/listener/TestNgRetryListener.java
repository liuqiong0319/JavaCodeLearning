package com.vip.qa.autov.core.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer2;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

public class TestNgRetryListener implements IAnnotationTransformer2 {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();

		if (retry == null) {
			annotation.setRetryAnalyzer(DefaultRetryAnalyzer.class);
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor,
			Method testMethod) {

	}

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO 自动生成的方法存根

	}
}
