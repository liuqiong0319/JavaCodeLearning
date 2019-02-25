package com.vip.qa.autov.core.params;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * 把global.properties文件内容读取出来并加载到系统环境变量,测试启动前初始化
 * 
 * @author alex.ma
 *
 */
@SuppressWarnings("unchecked")
public class GlobalParams {

	private static Properties properties;

	private static Map<String, String> envProps;

	private static Logger logger = LoggerFactory.getLogger(GlobalParams.class);

	static {
		properties = ClassPathPropertiesUtils.getProfilePropertiesByFileName(AutoVConsts.GLOBAL_PARAM_FILE
				+ ".properties", AutoVConsts.GLOBAL_PARAM_FILE + ".local.properties");

		// initialize system propeties and env properties from global.properties
		// automatically
		initSysEnv();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.setProperty(key, value);
		}
	}

	public static void put(String key, String value) {
		if (envProps != null) {
			envProps.put(key, value);
		}
	}

	public static Properties getAll() {
		return properties;
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static int getInteger(String key) {
		String value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public static int getInteger(String key, Integer defaultValue) {
		String value = get(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Double类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static double getDouble(String key) {
		String value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public static double getDouble(String key, Integer defaultValue) {
		String value = get(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property.如果都為Null抛出异常,如果内容不是true/false则返回false.
	 */
	public static boolean getBoolean(String key) {
		String value = get(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 取出Boolean类型的Propert.如果都為Null則返回Default值,如果内容不为true/false则返回false.
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = get(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	private static void initSysEnv() {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("java.lang.ProcessEnvironment");
		} catch (Exception e) {
			logger.error("Fail to find java.lang.ProcessEnvironment");
		}
		if (clazz != null) {
			try {
				envProps = (Map<String, String>) ReflectionUtils.getStaticFieldValue(clazz,
						"theCaseInsensitiveEnvironment");
			} catch (Exception e) {
				Map<String, String> theUnmodifiableEnvironment;
				try {
					theUnmodifiableEnvironment = (Map<String, String>) ReflectionUtils.getStaticFieldValue(clazz,
							"theUnmodifiableEnvironment");
					envProps = (Map<String, String>) ReflectionUtils.getFieldValue(theUnmodifiableEnvironment, "m");
				} catch (Exception e1) {
					logger.error("Fail to hack System.getenv!");
				}

			}
			if (envProps != null) {
				for (Entry<Object, Object> entry : properties.entrySet()) {
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
					envProps.put(key, value);
				}
			}
		}
	}
}
