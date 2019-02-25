package com.vip.qa.autov.core.extractor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import net.minidev.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.TypeReference;
import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.utils.JsonPathUtils;

public class JsonPathExtractor extends AbstractExtractor {

	private static Logger logger = LoggerFactory.getLogger(JsonPathExtractor.class);

	protected String input;

	protected JsonPathExtractor() {
	}

	public JsonPathExtractor(String input) {
		this.input = input;
	}

	/**
	 * Extract a value from xml input by jsonpath
	 * 
	 * @param jPath
	 * @return extracted value
	 */
	@Override
	public String extract(String jPath) {

		if (StringUtils.isBlank(jPath)) {
			return input;
		}
		String extracted = JsonPathUtils.read(input, jPath);
		logger.debug("Extracted value is: " + extracted);

		return extracted;
	}

	/**
	 * 把json转换为Map类型的java对象
	 * 
	 * @param jPaths
	 * @return
	 */
	public LinkedHashMap<String, Object> extractMap(String... jPaths) {
		String json = extract(jPaths);
		return JsonMapper.fromJson(json, new TypeReference<LinkedHashMap<String, Object>>() {
		}.getType());
	}

	/**
	 * 把json转换为Map类型的java对象
	 * 
	 * @param jPaths
	 * @return
	 */
	public List<Object> extractList(String... jPaths) {
		String json = extract(jPaths);
		return JsonMapper.fromJson(json, new TypeReference<List<Object>>() {
		}.getType());
	}

	/**
	 * 把json转换为Map类型的java对象
	 * 
	 * @param jPaths
	 * @return
	 */
	public LinkedHashMap<String, String> extractMap1(String... jPaths) {
		String json = extract(jPaths);
		return JsonMapper.fromJson(json, new TypeReference<LinkedHashMap<String, String>>() {
		}.getType());
	}

	/**
	 * 把json转换为List类型的java对象
	 * 
	 * @param jPaths
	 * @return
	 */
	public List<String> extractList1(String... jPaths) {
		String json = extract(jPaths);
		return JsonMapper.fromJson(json, new TypeReference<List<String>>() {
		}.getType());
	}

	/**
	 * 通过多个jsonpath一层层的提取，下一个jsonpath从上一个jsonpath提取的结果中提取
	 * 
	 * @param jPaths
	 * @return extracted value
	 */
	public String extract(String... jPaths) {
		String result = input;
		for (String jPath : jPaths) {
			JsonPathExtractor extractor = new JsonPathExtractor(result);
			result = extractor.extract(jPath);
			if (result == null) {
				return null;
			}
		}
		return result;
	}

	public double extractDouble(String... jPaths) {
		String value = extract(jPaths);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	public int extractInt(String... jPaths) {
		String value = extract(jPaths);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	public boolean extractBoolean(String... jPaths) {
		String value = extract(jPaths);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	public float extractFloat(String... jPaths) {
		String value = extract(jPaths);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Float.valueOf(value);
	}

	public long extractLong(String... jPaths) {
		String value = extract(jPaths);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Long.valueOf(value);
	}

	/**
	 * get the count of json object nodes of json array by jsonpath
	 * 
	 * @param jPath
	 * @return element count
	 */
	@Override
	public int getCount(String jPath) {

		int count = 0;

		if (StringUtils.isBlank(input)) {
			return count;
		}

		String json = JsonPathUtils.read(input, jPath);

		if (json.startsWith("[") && json.endsWith("]")) {
			List<?> list = JsonMapper.fromJson(json, List.class);
			for (Object o : list) {
				if (o != null) {
					count++;
				}
			}
		} else {
			Map<?, ?> map = JsonMapper.fromJson(json, Map.class);
			for (Entry<?, ?> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					count++;
				}
			}
		}
		logger.debug("Actual Count is " + count);
		return count;
	}

	/**
	 * Get current json array size
	 * 
	 * @return
	 */
	public int getCount() {
		return getCount(null);
	}

	/**
	 * Extract elements by jsonpath
	 * 
	 * @param jPath
	 * @return elements
	 */
	@Override
	public List<String> extractSome(String jPath) {
		List<String> matchFound = new ArrayList<>();

		try {
			JSONArray jsonArray = JsonPathUtils.readArray(input, jPath);
			int length = jsonArray.size();
			for (int i = 0; i < length; i++) {
				Object obj = jsonArray.get(i);
				matchFound.add(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Extracted values are: " + matchFound.toString());
		return matchFound;
	}

	public static void main(String[] args) {
		System.out.println(JsonMapper.fromJson("oePHAjlb1OYVkgg7NPKbjqzADJyY", Object.class));
	}
}
