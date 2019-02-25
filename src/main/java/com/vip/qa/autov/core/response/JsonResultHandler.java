package com.vip.qa.autov.core.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.json.JsonFormatter;
import com.vip.qa.autov.core.json.JsonKeyFormatStrategy;
import com.vip.qa.autov.core.json.JsonValueFormatStrategy;
import com.vip.qa.autov.core.json.strategy.JsonKeyFormater;
import com.vip.qa.autov.core.json.strategy.JsonValueFormater;
import com.vip.qa.autov.core.json.strategy.NoneKeyFormater;
import com.vip.qa.autov.core.json.strategy.NoneValueFormater;
import com.vip.qa.autov.core.utils.JsonPathUtils;

/**
 * 用于对响应Json进行修改
 * 
 * @author alex.ma
 *
 * @param <T>
 */
public class JsonResultHandler<T> {

	private AbstractResponser<T> responser;

	private static Logger logger = LoggerFactory.getLogger(JsonResultHandler.class);

	public JsonResultHandler(AbstractResponser<T> responser) {
		this.responser = responser;
	}

	/**
	 * add value to json array
	 * 
	 * @param json
	 * @param jsonPath
	 * @param value
	 * @return
	 */
	public JsonResultHandler<T> add(String jsonPath, String value) {
		String result = responser.getResult();
		if (result != null) {
			result = JsonPathUtils.add(result, jsonPath, value);
			responser.setResult(result);
			logger.info("Json Add Result: " + result);
		}
		return this;
	}

	/**
	 * delete node by jsonpath
	 * 
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public JsonResultHandler<T> delete(String jsonPath) {
		String result = responser.getResult();
		if (result != null) {
			result = JsonPathUtils.delete(result, jsonPath);
			responser.setResult(result);
			logger.info("Json Delete Result: " + result);
		}
		return this;
	}

	/**
	 * add or update node to json by json path
	 * 
	 * @param json
	 * @param jsonPath
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonResultHandler<T> put(String jsonPath, String key, String value) {
		String result = responser.getResult();
		if (result != null) {
			result = JsonPathUtils.put(result, jsonPath, key, value);
			responser.setResult(result);
			logger.info("Json Put Result: " + result);
		}
		return this;
	}

	/**
	 * rename json key
	 * 
	 * @param json
	 * @param jsonPath
	 * @param oldKeyName
	 * @param newKeyName
	 * @return
	 */
	public JsonResultHandler<T> renameKey(String jsonPath, String oldKeyName, String newKeyName) {
		String result = responser.getResult();
		if (result != null) {
			result = JsonPathUtils.renameKey(result, jsonPath, oldKeyName, newKeyName);
			responser.setResult(result);
			logger.info("Json ReanemKey Result: " + result);
		}
		return this;
	}

	public JsonResultHandler<T> formatKey(JsonKeyFormater formater) {
		if (formater == null || formater instanceof NoneKeyFormater) {
			return this;
		}
		String result = responser.getResult();
		List<SerializerFeature> features = new ArrayList<SerializerFeature>();
		features.add(SerializerFeature.WriteDateUseDateFormat);
		features.add(SerializerFeature.SkipTransientField);
		if (responser.jsonSerializeConfig.isSerializeNulls()) {
			features.add(SerializerFeature.WriteMapNullValue);
		}
		if (AutoVConsts.IS_JSON_PRETTY) {
			features.add(SerializerFeature.PrettyFormat);
		}
		features.add(SerializerFeature.WriteBigDecimalAsPlain);
		if (result != null) {
			result = JsonFormatter.formatKey(result, formater, features.toArray(new SerializerFeature[] {}));
			responser.setResult(result);
			logger.info("Json Key Fortmat Result: " + result);
		}
		return this;
	}

	/**
	 * 按照strategy参数格式化所有的key
	 * 
	 * @param strategy
	 */
	@Deprecated
	public JsonResultHandler<T> formatKey(JsonKeyFormatStrategy strategy) {
		JsonKeyFormater formater = strategy == null ? new NoneKeyFormater() : strategy.asFormater();
		formatKey(formater);
		return this;
	}

	/**
	 * 按照strategy参数格式化所有的value
	 * 
	 * @param formater
	 * @return
	 */
	public JsonResultHandler<T> formatValue(JsonValueFormater formater) {
		if (formater == null || formater instanceof NoneValueFormater) {
			return this;
		}
		String result = responser.getResult();
		List<SerializerFeature> features = new ArrayList<SerializerFeature>();
		features.add(SerializerFeature.WriteDateUseDateFormat);
		features.add(SerializerFeature.SkipTransientField);
		if (responser.jsonSerializeConfig.isSerializeNulls()) {
			features.add(SerializerFeature.WriteMapNullValue);
		}
		if (AutoVConsts.IS_JSON_PRETTY) {
			features.add(SerializerFeature.PrettyFormat);
		}
		features.add(SerializerFeature.WriteBigDecimalAsPlain);
		if (result != null) {
			result = JsonFormatter.formatValue(result, formater, features.toArray(new SerializerFeature[] {}));
			responser.setResult(result);
			logger.info("Json Value Fortmat Result: " + result);
		}
		return this;
	}

	/**
	 * 按照strategy参数格式化所有的value
	 * 
	 * @param strategy
	 */
	@Deprecated
	public JsonResultHandler<T> formatValue(JsonValueFormatStrategy strategy) {
		JsonValueFormater formater = strategy == null ? new NoneValueFormater() : strategy.asFormater();
		formatValue(formater);
		return this;
	}

}
