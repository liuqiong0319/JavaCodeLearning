package com.vip.qa.autov.core.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.internal.JsonContext;
import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.json.JsonSmartJsonProvider;
import net.minidev.json.JSONArray;

import java.util.Map;
import java.util.WeakHashMap;

public class JsonPathUtils {

	private static Configuration defaultConfig = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL)
			.jsonProvider(new JsonSmartJsonProvider()).build();

	private static Map<String, JsonPath> cache = new WeakHashMap<String, JsonPath>();

	/**
	 * add or update node to json by json path
	 * 
	 * @param json
	 * @param jsonPath
	 * @param key
	 * @param value
	 * @return
	 */
	public static String put(String json, String jsonPath, String key, String value) {
		JsonPath jPath = compileJsonPath(jsonPath);
		Object o = jPath.read(json, defaultConfig);
		Object oValue = JsonPath.parse(value, defaultConfig).read("$");
		jPath.put(o, key, oValue, defaultConfig);
		return objectToString(o);
	}

	/**
	 * delete node by jsonpath
	 * 
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static String delete(String json, String jsonPath) {
		JsonContext o = (JsonContext) JsonPath.parse(json, defaultConfig).delete(jsonPath);
		return objectToString(o.json());
	}

	/**
	 * add value to json array
	 * 
	 * @param json
	 * @param jsonPath
	 * @param value
	 * @return
	 */
	public static String add(String json, String jsonPath, String value) {
		Object oValue = JsonPath.parse(value, defaultConfig).read("$");
		JsonContext o = (JsonContext) JsonPath.parse(json, defaultConfig).add(jsonPath, oValue);
		return objectToString(o.json());
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
	public static String renameKey(String json, String jsonPath, String oldKeyName, String newKeyName) {
		JsonPath jPath = compileJsonPath(jsonPath);
		Object o = jPath.read(json, defaultConfig);
		o = jPath.renameKey(o, oldKeyName, newKeyName, defaultConfig);
		return objectToString(o);
	}

	/**
	 * read json
	 * 
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static String read(String json, String jsonPath) {
		JsonPath jPath = compileJsonPath(jsonPath);
		Object o = jPath.read(json, defaultConfig);
		return objectToString(o);
	}

	/**
	 * read json
	 * 
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static JSONArray readArray(String json, String jsonPath) {
		JsonPath jPath = compileJsonPath(jsonPath);
		return (JSONArray) jPath.read(json, defaultConfig);
	}

	public static JsonPath compileJsonPath(String jsonPath) {
		JsonPath jPath = cache.get(jsonPath);
		if (jsonPath == null) {
			jsonPath = "$";
		}
		try {
			if (jPath == null) {
				jPath = JsonPath.compile(jsonPath);
				cache.put(jsonPath, jPath);
			}
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		return jPath;
	}

	private static String objectToString(Object o) {
		String extracted = null;
		if (o == null) {
			return null;
		}
		if (o instanceof String || o instanceof Integer || o instanceof Long || o instanceof Double
				|| o instanceof Short || o instanceof Float || o instanceof Byte) {
			extracted = o.toString();
		} else {
			extracted = JsonMapper.toJsonSerializeNulls(o);
		}
		return extracted;
	}

}
