package com.vip.qa.autov.core.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.alibaba.fastjson.TypeReference;
import com.vip.qa.autov.core.json.JsonMapper;

public class Json2JavaParamConverter {

	/**
	 * Convert json param to java param instance of the invoking method
	 * 
	 * @param method
	 *            Method to invode
	 * @param jsonParams
	 *            json object which will be converted to java object
	 * @return
	 */
	public static List<Object> convertParams(Method method, String... jsonParams) {
		Type[] paramTypes = method.getGenericParameterTypes();
		Validate.isTrue(jsonParams.length == paramTypes.length, "param size not matched, expected size is "
				+ paramTypes.length + "!");
		List<Object> params = new ArrayList<>();
		int index = 0;
		Object param = null;
		for (String jsonParam : jsonParams) {
			if (jsonParam == null || jsonParam.equals("null")) {
				param = null;
			} else {
				Type type = paramTypes[index];
				if (type == String.class) {
					param = jsonParam;
				} else {
					param = JsonMapper.fromJson(jsonParam, type);
				}
				params.add(param);
			}
			index++;
		}

		return params;
	}

	/**
	 * Convert json param to java param instance of the invoking method
	 * 
	 * @param method
	 *            Method to invode
	 * @param jsonParams
	 *            json object which will be converted to java object
	 * @return
	 */
	public static List<Object> convertParamsFromWholeJson(Method method, String json, Map<String, ?> templateParams) {
		Type[] paramTypes = method.getGenericParameterTypes();
		int index = 0;
		List<Object> paramList = new ArrayList<>();
		Map<String, Object> jsonObj = JsonMapper.fromJson(json, new TypeReference<Map<String, Object>>() {
		}.getType());
		for (Type paramType : paramTypes) {
			String key = String.valueOf(index);
			Object obj = null;
			Object param = null;
			if (templateParams != null) {
				param = templateParams.get(key);
			}

			if (param == null) {
				param = jsonObj.get(key);
			}
			// 直接通过key为0，1，2.。。的参数替换掉哼歌参数，如果参数值是非字符串则自动先序列化为字符串再反序列化
			if (param != null) {
				if (paramType == String.class) {
					obj = param.toString();
				}else if(paramType == Date.class) {
					//直接转Date异常，包装为List<Date>再转
					List<Date> ds = JsonMapper.fromJson("['" + param + "']", new TypeReference<List<Date>>(){}.getType());
					obj = ds.get(0);
				}else {
					String jsonParam = null;
					if (param instanceof String) {
						jsonParam = param.toString();
					} else {
						jsonParam = JsonMapper.toJson(param);
					}
					obj = JsonMapper.fromJson(jsonParam, paramType);
				}
			}

			paramList.add(obj);
			index++;
		}

		return paramList;
	}
}
