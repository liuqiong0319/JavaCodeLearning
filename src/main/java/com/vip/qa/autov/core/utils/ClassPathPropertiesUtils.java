package com.vip.qa.autov.core.utils;

import com.vip.qa.autov.core.common.AutoVConstants;
import com.vip.qa.autov.core.common.AutoVProperties;
import com.vip.qa.autov.core.params.GlobalParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class ClassPathPropertiesUtils {

	private static Properties properties;

	public static final String[] PROP_FILES = { "application.properties", "application.test.properties",
			"application.local.properties" };

	private static Logger logger = LoggerFactory.getLogger(ClassPathPropertiesUtils.class);

	static {
		initProperties(PROP_FILES);
		String profile = AutoVProperties.getActiveProfile();
		if (StringUtils.isBlank(profile)) {
			profile = ClassPathPropertiesUtils.getValue(AutoVConstants.KEY_VTP_ENVIRONMENT);
		}
		if (StringUtils.isBlank(profile)) {
			profile = ClassPathPropertiesUtils.getValue(AutoVConstants.SPRING_PROFILES_ACTIVE);
		}
		if (StringUtils.isNotBlank(profile)) {
			AutoVProperties.setActiveProfile(profile);
			logger.info("Active Profile is {}", profile);
		}
		initPropertiesWithProfile();
		GlobalParams.getAll();
	}

	private static void initPropertiesWithProfile() {
		Properties profileProps = ClassPathPropertiesUtils
				.getPropertiesFromProfile(ClassPathPropertiesUtils.PROP_FILES);
		ClassPathPropertiesUtils.mergeProperties(profileProps);
	}

	public static void initProperties(String... propertiesFiles) {
		if (properties == null) {
			properties = getPropertiesByFileName(propertiesFiles);
		}
	}

	private static void mergeProperties(Properties props) {
		if (properties != null) {
			properties.putAll(props);
		}
	}

	/**
	 * 取出Property。
	 */
	public static String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		String s = properties.getProperty(key);
		return s;
	}

	/**
	 * 取出String类型的Property,如果都為Null则抛出异常.
	 */
	public static String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value.trim();
	}

	public static boolean hasProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			return false;
		}
		return true;
	}

	/**
	 * 取出String类型的Property.如果都為Null則返回Default值.
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value.trim() : defaultValue;
	}

	/**
	 * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static int getInteger(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public static int getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Long类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static Long getLong(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Long.valueOf(value);
	}

	/**
	 * 取出Long类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public static Long getLong(String key, Long defaultValue) {
		String value = getValue(key);
		return value != null ? Long.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Double类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static double getDouble(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public static double getDouble(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property.如果都為Null抛出异常,如果内容不是true/false则返回false.
	 */
	public static boolean getBoolean(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 取出Boolean类型的Propert.如果都為Null則返回Default值,如果内容不为true/false则返回false.
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	public static Properties getPropertiesByFileName(String... fileNames) {
		Validate.notEmpty(fileNames, "files names must be specified!");
		Properties props = new Properties();
		for (String fileName : fileNames) {
			loadProperties(props, "classpath*:/" + fileName);
		}
		return props;
	}

	public static Properties getProfilePropertiesByFileName(String... fileNames) {
		Validate.notEmpty(fileNames, "files names must be specified!");
		Properties props = getPropertiesByFileName(fileNames);

		Properties profileProps = getPropertiesFromProfile(fileNames);
		props.putAll(profileProps);
		return props;
	}

	public static Properties getPropertiesFromProfile(String... fileNames) {
		Properties props = new Properties();
		String activeProfile = AutoVProperties.getActiveProfile();
		if (StringUtils.isNotBlank(activeProfile)) {
			for (String fileName : fileNames) {
				String profileFile = "classpath*:/profiles/" + activeProfile + "/" + fileName;
				loadProperties(props, profileFile);
			}
		}
		return props;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static Properties loadProperties(Properties props, String... resourcesPaths) {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		for (String location : resourcesPaths) {
			try {
				Resource[] resources = resolver.getResources(location);
				for (Resource resource : resources) {
					PropertiesLoaderUtils.fillProperties(props, resource);
				}
			} catch (IOException e) {
				logger.warn(e.getMessage());
			}

		}
		return props;
	}

}
