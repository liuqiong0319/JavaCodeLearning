package com.vip.qa.autov.core.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated please use JsonMapper instead
 * @see com.vip.qa.autov.core.json.JsonMapper
 * @author alex.ma
 *
 */
@Deprecated
public class GsonMapper {

	private static Logger logger = LoggerFactory.getLogger(GsonMapper.class);

	public static String toJson(Object obj, SerializerFeature... features) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, features);
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
	}

	public static String toJsonSerializeNulls(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
	}

	public static String toJsonPretty(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		return fromJson(jsonString, clazz, Feature.AllowComment, Feature.AllowSingleQuotes,
				Feature.AllowUnQuotedFieldNames, Feature.AllowISO8601DateFormat, Feature.AllowArbitraryCommas);
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz, Feature... features) {
		if (jsonString == null) {
			return null;
		}
		try {
			return JSON.parseObject(jsonString, clazz, features);
		} catch (Exception e) {
			logger.error("String before deserilization: " + jsonString);
			throw e;
		}
	}

	public static <T> T fromJson(String jsonString, Type type, Feature... features) {
		if (jsonString == null) {
			return null;
		}
		try {
			return JSON.parseObject(jsonString, type, features);
		} catch (Exception e) {
			logger.error("String before deserilization: " + jsonString);
			throw e;
		}
	}

	public static <T> T fromJson(String jsonString, Type type) {
		return fromJson(jsonString, type, Feature.AllowComment, Feature.AllowSingleQuotes,
				Feature.AllowUnQuotedFieldNames, Feature.AllowISO8601DateFormat, Feature.AllowArbitraryCommas);
	}

	public static void main(String[] args) {
		Map<String, Object> obj = new HashMap<>();
		obj.put("a", 232323423424234L);
		obj.put("b", 232323423424234.333);
		obj.put("c", null);
		obj.put("d", new Date());
		System.out.println(toJson(obj));
	}
}
