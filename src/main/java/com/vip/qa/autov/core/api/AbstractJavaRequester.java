package com.vip.qa.autov.core.api;

import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.parser.ApiTemplateParser;
import com.vip.qa.autov.core.response.Responser;
import com.vip.qa.autov.core.utils.Json2JavaParamConverter;
import com.vip.qa.autov.core.utils.JsonUtils;
import com.vip.qa.autov.core.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * osp和thrift协议Requester的基类
 * 
 * @author alex.ma
 *
 * @param <T>
 */
public abstract class AbstractJavaRequester<T extends Responser<?>> implements ApiRequester<T> {

	protected static Logger logger = LoggerFactory.getLogger(AbstractJavaRequester.class);

	protected Class<?> serviceClass;

	protected Object clientObj;

	protected AbstractJavaRequester() {
	}

	public AbstractJavaRequester(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
		this.clientObj = getTargetInstance(serviceClass);
	}

	public AbstractJavaRequester(String service) {
		try {
			this.serviceClass = Class.forName(service);
			this.clientObj = getTargetInstance(serviceClass);
		} catch (ClassNotFoundException e) {
			logger.error("Service class {} not found ,failed to intitialize", service);
			e.printStackTrace();
		}
	}

	/**
	 * 通过模板调用接口
	 * 
	 * @param method
	 * @param templateFileSuffix
	 *            模板文件后缀（下划线后部分）
	 */
	@Override
	public T call(String method, String templateFileSuffix) {
		return call(method, new HashMap<String, String>(), templateFileSuffix);
	}

	@Override
	public T call(String method) {
		return call(method, new HashMap<String, String>(), null);
	}

	/**
	 * 通过java对象参数调接口（无需模板）
	 * 
	 * @param method
	 * @param requestObjects
	 * @return
	 */
	protected abstract T callDirectly(String method, Object... requestObjects);

	/**
	 * Invoke rpc method by json string which will be converted to java object
	 * 
	 * @param method
	 *            Method to invode
	 * @param jsonParams
	 *            json object which will be converted to java object and passed
	 *            to the invoking method
	 * @return java object
	 * @throws Throwable
	 */
	protected Object callWithParams(Class<?> serviceClass, String method, StringBuilder requestSb, String... jsonParams)
			throws Exception {
		Validate.notNull(serviceClass, "serivce must be specified!");
		try {
			Method m = ReflectionUtils.getMethodByName(clientObj.getClass(), method);
			if (m == null) {
				throw new Exception("Method name " + method + " does not exists in service class");
			}
			logger.info("[INVOKE JAVA] - [SERVICE]: {} - [METHOD]: {}", serviceClass.getName(), method);

			List<Object> params = null;
			if (jsonParams != null && jsonParams.length > 0) {
				params = Json2JavaParamConverter.convertParams(m, jsonParams);
				String requestStr = JsonMapper.toJsonPretty(jsonParams);
				if (requestSb != null) {
					requestSb.append(requestStr);
				}
				logger.info("[PARAMS]: " + requestStr);
			}
			setTimeout();
			Object result = ReflectionUtils.invokeMethod(clientObj, method, m.getParameterTypes(),
					params == null ? new Object[] {} : params.toArray());
			return result;
		} catch (Exception e) {
			e = convertFromInvocationException(e);
			throw e;
		}
	}

	/**
	 * Invoke rpc method by java object params
	 * 
	 * @param method
	 *            Method to invode
	 * @param params
	 *            objects which will be converted to java object and passed
	 *            to the invoking method
	 * @return java object
	 * @throws Throwable
	 */
	protected Object callWithObject(Class<?> serviceClass, String method, StringBuilder requestSb, Object... params)
			throws Exception {
		Validate.notNull(serviceClass, "serivce must be specified!");
		try {
			Method m = ReflectionUtils.getMethodByName(clientObj.getClass(), method);
			if (m == null) {
				throw new Exception("Method name " + method + " does not exists in service class");
			}
			logger.info("[INVOKE JAVA] - [SERVICE]: {} - [METHOD]: {}", serviceClass.getName(), method);

			params = params == null ? new Object[] {} : params;
			if (params.length > 0) {
				String allParamJson = JsonUtils.toPrettyJson(params);
				params = JsonUtils.convertParams(m, allParamJson).toArray();
				if (requestSb != null) {
					requestSb.append(allParamJson);
				}
				logger.info("[PARAMS]: " + allParamJson);
			}
			setTimeout();
			Object result = ReflectionUtils.invokeMethod(clientObj, method, m.getParameterTypes(),
					params);
			return result;
		} catch (Exception e) {
			e = convertFromInvocationException(e);
			throw e;
		}
	}

	/**
	 * Invoke rpc method by freemarker template, template file format is
	 * ${methodName}_${templateFileSuffix}
	 * 
	 * @param jsonData json which will be converted to java object and passed
	 *            to the invoking method
	 * @param templateParams
	 *            params to pass to freemark template, when param name is
	 *            Param_X(X is the param index), the whole json param will be
	 *            replaced
	 * @param requestSb to log the request
	 * @throws Exception
	 */
	protected Object callWithJson(Class<?> serviceClass, String jsonData, String method, Map<String, ?> templateParams,
			StringBuilder requestSb) throws Exception {
		try {
			Object clientObj = getTargetInstance(serviceClass);
			Object[] params = null;
			Method m = ReflectionUtils.getMethodByName(clientObj.getClass(), method);
			Validate.notNull(m, "Method {} not found in class {}", method, serviceClass.getName());
			logger.info("[INVOKE JAVA] - [SERVICE]: {} - [METHOD]: {}", serviceClass.getName(), method);
			if (StringUtils.isNotBlank(jsonData)) {
				List<Object> paramList = Json2JavaParamConverter
						.convertParamsFromWholeJson(m, jsonData, templateParams);
				String requestStr = JsonMapper.toJsonPretty(paramList);
				if (requestSb != null) {
					requestSb.append(requestStr);
				}
				logger.info("[PARAMS]: " + requestStr);
				if (paramList != null) {
					params = paramList.toArray();
				}
			}
			setTimeout();
			Object result = m.invoke(clientObj, params);
			return result;
		} catch (Exception e) {
			e = convertFromInvocationException(e);
			throw e;
		}
	}

	private Exception convertFromInvocationException(Exception e) {
		if (e instanceof InvocationTargetException) {
			Throwable throwable = ((InvocationTargetException) e).getTargetException();
			if (throwable instanceof Exception) {
				e = (Exception) throwable;
			}
		}
		return e;
	}

	/**
	 * 通过模板解析
	 * 
	 * @param method
	 * @param templateParams
	 * @param suffix
	 *            模板文件下划线的后缀，例如xxx_1.0.0, 则suffix是1.0.0
	 * @return
	 */
	protected String parseJson(String method, Map<String, ?> templateParams, String suffix) {
		ApiTemplateParser parser = new ApiTemplateParser(serviceClass.getName(), method, suffix);
		return parser.parse(templateParams);
	}

	protected abstract Object getTargetInstance(Class<?> serviceClass);

	/**
	 * call api using json data directly
	 * 
	 * @param method
	 * @param jsonParams
	 * @return
	 */
	public abstract T callDirectlyByJson(String method, String... jsonParams);

	protected void setTimeout(){
	}

}
