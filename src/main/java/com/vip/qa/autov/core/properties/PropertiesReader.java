package com.vip.qa.autov.core.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import com.vip.qa.autov.core.utils.Exceptions;

/**
 * 读取sql, ui等制定文件夹下的配置文件
 * 
 * @author alex.ma
 *
 */
public class PropertiesReader {

	protected Properties properties = new Properties();

	public void initProperties(File propertyFile) {
		Validate.isTrue(propertyFile.exists(), "Properties file " + propertyFile + " not exists");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propertyFile);
			properties.load(new InputStreamReader(fis, "UTF-8"));
		} catch (IOException e) {
			Exceptions.checked(e);
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}

	public Properties getAll() {
		return properties;
	}

	public String get(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new NoSuchElementException("No element found by key:" + key);
		}
		return value.trim();
	}

}
