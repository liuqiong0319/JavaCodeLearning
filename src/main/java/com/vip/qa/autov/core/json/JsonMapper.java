package com.vip.qa.autov.core.json;

import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * fastjson工具类
 * 
 * @author alex.ma
 *
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static MultiValueMapFilter multiValueMapFilter = new MultiValueMapFilter();

	public static String toJson(Object obj, SerializerFeature... features) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter }, features);
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter },
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteBigDecimalAsPlain,
				SerializerFeature.SkipTransientField);
	}

	public static String toJsonSerializeNulls(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter },
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteBigDecimalAsPlain,
				SerializerFeature.WriteMapNullValue, SerializerFeature.SkipTransientField);
	}

	public static String toJsonPrettySerializeNulls(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter },
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteBigDecimalAsPlain,
				SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat,
				SerializerFeature.SkipTransientField);
	}

	public static String toJsonPretty(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter }, SerializerFeature.PrettyFormat,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteBigDecimalAsPlain,
				SerializerFeature.SkipTransientField);
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		return fromJson(jsonString, clazz, Feature.AllowComment, Feature.AllowSingleQuotes,
				Feature.AllowUnQuotedFieldNames, Feature.AllowArbitraryCommas);
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
				Feature.AllowUnQuotedFieldNames, Feature.AllowArbitraryCommas);
	}

	public static class MultiValueMapFilter implements ValueFilter {

		@SuppressWarnings("rawtypes")
		@Override
		public Object process(Object object, String name, Object value) {
			if (object != null && value != null && object instanceof MultiValueMap) {
				if (value instanceof Collection) {
					Collection valueList = (Collection) value;
					if (valueList.isEmpty()) {
						value = null;
					} else if (valueList.size() == 1) {
						value = valueList.iterator().next();
					}
				}
			}
			return value;
		}
	}

}
