package com.vip.qa.autov.core.params;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试用例及表格参数化变量的map，自动替换掉${xxx}参数格式
 * 
 * @author alex.ma
 *
 * @param <String>
 * @param <T>
 */
@SuppressWarnings({ "serial", "hiding" })
public class ParamMap<String, T> extends HashMap<String, T> {

	private transient Map<String, T> tempMap = new HashMap<>();

	private static Pattern scriptBlockPattern = Pattern.compile("\\$\\{([\\s\\S]+?)}");

	private Stack<Object> stack = new Stack<>();

	public ParamMap() {
		super();
	}

	public ParamMap(Map<String, T> m) {
		super(m);
	}

	public static <T> ParamMap<java.lang.String, T> create() {
		return new ParamMap<>();
	}

	public static <T> ParamMap<java.lang.String, T> create(Map<java.lang.String, T> m) {
		return new ParamMap<>(m);
	}

	/**
	 * 复制新的ParamMap对象和数据
	 * 
	 * @return
	 */
	public ParamMap<String, T> copy() {
		return new ParamMap<String, T>(this);
	}

	/**
	 * 存入临时变量，调用clearTemp时会把临时变量清掉，临时变量是paramMap内部的一个map，可以在不影响当前paramMap变量值的情况下
	 * ，在get的时候合并变量值（优先使用temp map的值）
	 * 
	 * @param key
	 * @param value
	 */
	public void putTemp(String key, T value) {
		tempMap.put(key, value);
	}

	/**
	 * 存入临时变量，调用clearTemp时会把临时变量清掉，临时变量是paramMap内部的一个map，可以在不影响当前paramMap变量值的情况下
	 * ，在get的时候合并变量值（优先使用temp map的值）
	 * 
	 * @param map
	 */
	public void putAllTemp(Map<String, T> map) {
		tempMap.putAll(map);
	}

	/**
	 * 清除所有临时变量
	 */
	public void clearTemp() {
		tempMap.clear();
	}

	/**
	 * 删除key值对应的临时变量
	 */
	public void removeTemp(String key) {
		tempMap.remove(key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T get(Object key) {
		try {
			int index = stack.indexOf(key);
			stack.push(key);
			if (index >= 0) {
				java.lang.String loop = StringUtils.join(stack.subList(index, stack.size()), "->");
				throw new RuntimeException("Loop reference: " + loop);
			}

			T preValue = tempMap.get(key);
			if (preValue == null) {
				preValue = super.get(key);
			}
			if (preValue instanceof java.lang.String) {
				Object postValue = preValue;
				Matcher matcher = scriptBlockPattern.matcher((CharSequence) preValue);
				StringBuffer sb = new StringBuffer();
				while (matcher.find()) {
					java.lang.String toEval = matcher.group(1);
					Object result = get(toEval); //递归get
					if (result != null) {
						matcher.appendReplacement(sb, Matcher.quoteReplacement(result.toString()));
					}
				}
				matcher.appendTail(sb);
				postValue = sb.toString();
				return (T) postValue;
			} else {
				return preValue;
			}
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			} else {
				throw new RuntimeException(e);
			}
		} finally {
			stack.pop();
		}
	}

	 public T getOrDefault(Object key, T defaultValue) {
		 T value = this.get(key);
		 if (null == value) {
		 	return defaultValue;
		 }
		 return value;
	 }

	/**
	 * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public int getInteger(Object key) {
		T value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value.toString());
	}

	/**
	 * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public int getInteger(Object key, int defaultValue) {
		T value = get(key);
		return value != null ? Integer.valueOf(value.toString()) : defaultValue;
	}

	/**
	 * 取出Double类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public double getDouble(Object key) {
		T value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value.toString());
	}

	/**
	 * 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public double getDouble(Object key, Integer defaultValue) {
		T value = get(key);
		return value != null ? Double.valueOf(value.toString()) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property.如果都為Null抛出异常,如果内容不是true/false则返回false.
	 */
	public boolean getBoolean(Object key) {
		T value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value.toString());
	}

	/**
	 * 取出Boolean类型的Propert.如果都為Null則返回Default值,如果内容不为true/false则返回false.
	 */
	public boolean getBoolean(Object key, boolean defaultValue) {
		Object value = get(key);
		return value != null ? Boolean.valueOf(value.toString()) : defaultValue;
	}

	@Override
	public Collection<T> values() {
		Collection<T> values = new LinkedList<>();
		for (Object key : this.keySet()) {
			T value = this.get(key);
			values.add(value);
		}
		return values;
	}

	/**
	 * merge all elements to current map, ignoring existing elements
	 * 
	 * @param toMerge
	 */
	public void putAllNonExisting(Map<String, T> toMerge) {
		for (Entry<String, T> entry : toMerge.entrySet()) {
			String key = entry.getKey();
			if (!this.containsKey(key)) {
				this.put(key, entry.getValue());
			}
		}
	}
}
