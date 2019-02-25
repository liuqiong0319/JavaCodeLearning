package com.vip.qa.autov.core.response;

public abstract class AbstractJavaResponser extends AbstractApiTemplateResponser<Object> {

	protected Class<?> serviceClass;

	protected String suffix;
	protected String method;

	public AbstractJavaResponser(Object object, Class<?> serviceClass) {
		super(object);
		this.serviceClass = serviceClass;
	}

	public AbstractJavaResponser(Object object, Class<?> serviceClass, String method, String suffix) {
		this(object, serviceClass);
		this.method = method;
		this.suffix = suffix;
	}

	@Override
	protected String getName() {
		return method;
	}

	@Override
	protected String getDir() {
		return this.serviceClass.getName();
	}

	@Override
	protected String getSuffix() {
		return suffix;
	}

}
