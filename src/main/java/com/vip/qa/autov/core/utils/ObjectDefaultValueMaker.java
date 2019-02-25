package com.vip.qa.autov.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.lang.reflect.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用于生成默认数据，通过反射等方式生成一个含有默认数据的对象
 * 
 * @author alex.ma
 *
 */
public class ObjectDefaultValueMaker {

	private Set<String> typeSet = new HashSet<>();

	private Map<Class<?>, Object[]> enumTypes = Maps.newHashMap();

	public <T> T getDefaultValue(Class<T> paramClass, Type t) {
		return getDefaultValue(paramClass, t, null);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T getDefaultValue(Class<T> paramClass, Type t, String fieldName) {
		Object dest = null;
		try {
			dest = getSampleObj(paramClass, t, fieldName);
			if (dest == null && paramClass != void.class) {
				dest = paramClass.newInstance();
				Field[] fields = paramClass.getDeclaredFields();
				for (Field f : fields) {
					if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
						continue;
					}

					f.setAccessible(true);
					if (Collection.class.isAssignableFrom(f.getType())) {
						String key = f.getName() + "@" + f.getGenericType().toString();
						if (typeSet.contains(key)) {
							continue;
						}
						typeSet.add(key);
					}
					if (f.getGenericType() == t && Serializable.class.isAssignableFrom(f.getType())) {
						continue;
					}
					Object value = null;
					value = getDefaultValue(f.getType(), f.getGenericType(), f.getName());

					f.set(dest, value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T) dest;
	}

	public void reset() {
		typeSet.clear();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T getSampleObj(Class<T> paramClass, Type t, String fieldName) throws Exception {
		if (Collection.class.isAssignableFrom(paramClass)) {
			return (T) initCollection((Class<Collection>) paramClass, t);
		} else if (paramClass.isArray()) {
			return (T) initArray(paramClass, t);
		} else if (Map.class.isAssignableFrom(paramClass)) {
			return (T) initMap((Class<Map>) paramClass, t);
		} else if (paramClass == Date.class) {
			return (T) new Date();
		} else if (paramClass == Timestamp.class) {
			return (T) new Timestamp(System.currentTimeMillis());
		} else if (paramClass == String.class || paramClass == StringBuilder.class) {
			if (fieldName == null) {
				return (T) "a";
			}
			return (T) (fieldName + "_123");
		} else if (paramClass == int.class || paramClass == Integer.class) {
			return (T) new Integer(0);
		} else if (paramClass == long.class || paramClass == Long.class) {
			return (T) new Long(0);
		} else if (paramClass == short.class || paramClass == Short.class) {
			return (T) new Short((short) 0);
		} else if (paramClass == byte.class || paramClass == Byte.class) {
			return (T) new Byte((byte) 0);
		} else if (paramClass == double.class || paramClass == Double.class) {
			return (T) new Double(0.0d);
		} else if (paramClass == float.class || paramClass == Float.class) {
			return (T) new Float(0.0f);
		} else if (paramClass == Boolean.class || paramClass == boolean.class) {
			return (T) new Boolean(true);
		} else if (paramClass.isEnum() || paramClass == Enum.class) {
			T[] ts = paramClass.getEnumConstants();
			if (ts.length > 0) {
				enumTypes.put(paramClass, ts);
				return ts[0];
			}
		}
		return null;
	}

	public Map<Class<?>, Object[]> getEnumTypes() {
		return enumTypes;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection initCollection(Class<Collection> source, Type t) throws Exception {
		Collection dest = null;
		if (t instanceof ParameterizedType) // 【3】如果是泛型参数的类型
		{
			ParameterizedType pt = (ParameterizedType) t;

			Type[] types = pt.getActualTypeArguments();
			Type type0 = types[0];
			Class class0 = null;
			if (types[0] instanceof ParameterizedType) {
				class0 = (Class) ((ParameterizedType) type0).getRawType();
			} else {
				class0 = (Class) type0;
			}
			if (Set.class.isAssignableFrom(source)) {
				dest = Sets.newHashSet();
			} else {
				dest = Lists.newArrayList();
			}
			dest.add(getDefaultValue(class0, type0));
		}

		return dest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object initArray(Class source, Type t) throws Exception {
		Object dest = Array.newInstance(source.getComponentType(), 1);
		Array.set(dest, 0, getDefaultValue(source.getComponentType(), t));
		return dest;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map initMap(Class<Map> source, Type t) throws Exception {
		Map dest = Maps.newHashMap();
		if (t instanceof ParameterizedType) // 【3】如果是泛型参数的类型
		{
			ParameterizedType pt = (ParameterizedType) t;

			Type[] types = pt.getActualTypeArguments();
			Type type0 = types[0];
			Type type1 = types[1];
			Class class0 = (Class) types[0];
			Class class1 = null;
			if (types[1] instanceof ParameterizedType) {
				class1 = (Class) ((ParameterizedType) type1).getRawType();
			} else {
				class1 = (Class) type1;
			}
			dest.put(getDefaultValue(class0, type0), getDefaultValue(class1, type1));
		}

		return dest;
	}

}
