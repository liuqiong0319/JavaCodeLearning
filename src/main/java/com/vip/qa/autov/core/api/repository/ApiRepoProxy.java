package com.vip.qa.autov.core.api.repository;

import com.vip.qa.autov.core.api.ApiRequester;
import com.vip.qa.autov.core.json.JsonKeyFormatStrategy;
import com.vip.qa.autov.core.json.JsonValueFormatStrategy;
import com.vip.qa.autov.core.json.strategy.JsonKeyFormater;
import com.vip.qa.autov.core.json.strategy.JsonValueFormater;
import com.vip.qa.autov.core.response.AbstractResponser;
import com.vip.qa.autov.core.response.JsonResultHandler;
import com.vip.qa.autov.core.response.Responser;
import com.vip.qa.autov.core.utils.SpringContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过动态代理执行ApiRepository接口，接口真实调用是在ApiProxyHandler里
 * 
 * @author alex.ma
 *
 */
public class ApiRepoProxy implements InvocationHandler {

	private String requesterBeanName;

	public ApiRepoProxy(String requesterBeanName) {
		this.requesterBeanName = requesterBeanName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (!Responser.class.isAssignableFrom(method.getReturnType())) {
			return method.invoke(proxy, args);
		}
		ApiRequester<?> requester = SpringContextHolder.getBean(requesterBeanName);

		// ApiProxyHander通过ApiProxyHnderFactoryBean动态生成实例，并且通过requester类型绑定到不同的ApiProxyHander类上
		ApiProxyHandlerHolder holder = ApiProxyHandlerHolder.getInstance();
		ApiProxyHandler<ApiRequester<?>> handler = (ApiProxyHandler<ApiRequester<?>>) holder.getHandler(requester
				.getClass());

		Object responser = handler.invokeApi(requester, proxy, method, args);
		return handleResponser(responser, method);
	}

	/**
	 * 格式化json结果的key和value
	 * 
	 * @param responser
	 * @param method
	 * @return
	 */
	private static Object handleResponser(Object responser, Method method) {
		if (responser instanceof AbstractResponser && method.isAnnotationPresent(ApiSetting.class)) {
			ApiSetting apiSetting = method.getAnnotation(ApiSetting.class);

			AbstractResponser<?> apiResponser = (AbstractResponser<?>) responser;
			JsonResultHandler<?> jsonResultHandler = apiResponser.jsonResultHandler();
			JsonKeyFormater keyFormater = getJsonKeyFormater(apiSetting);
			jsonResultHandler.formatKey(keyFormater);

			List<JsonValueFormater> valueFormaters = getJsonValueFormaters(apiSetting);
			for (JsonValueFormater valueFormater : valueFormaters) {
				jsonResultHandler.formatValue(valueFormater);
			}
			return apiResponser;
		}
		return responser;
	}

	/**
	 * 获取JsonKeyFormater，keyFormater和keyFormatStrategy只能取一个，keyFormater优先级高于keyFormatStrategy
	 * 
	 * @param apiSetting
	 * @return
	 */
	private static JsonKeyFormater getJsonKeyFormater(ApiSetting apiSetting) {
		JsonKeyFormatStrategy keyFormatStrategy = apiSetting.keyFormatStrategy();
		Class<?> keyFormaterClass = apiSetting.keyFormater();
		try {
			Object keyFormaterObj = keyFormaterClass.newInstance();
			if (keyFormaterObj != null && JsonKeyFormater.class.isInstance(keyFormaterObj)) {
				return (JsonKeyFormater) keyFormaterObj;
			} else {
				if (keyFormatStrategy != null) {
					return keyFormatStrategy.asFormater();
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取JsonValueFormater，结果为valueFormatStrategy和valueFormater的并集，先执行valueFormatStrategy，后执行valueFormater
	 * 
	 * @param apiSetting
	 * @return
	 */
	private static List<JsonValueFormater> getJsonValueFormaters(ApiSetting apiSetting) {
		List<JsonValueFormater> formaters = new ArrayList<JsonValueFormater>();
		JsonValueFormatStrategy[] formatStrategies = apiSetting.valueFormatStrategy();
		for (JsonValueFormatStrategy formatStrategy : formatStrategies) {
			formaters.add(formatStrategy.asFormater());
		}
		for (Class<?> clz : apiSetting.valueFormater()) {
			try {
				Object obj = clz.newInstance();
				if (obj != null && JsonValueFormater.class.isInstance(obj)) {
					formaters.add((JsonValueFormater) obj);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return formaters;
	}

}
