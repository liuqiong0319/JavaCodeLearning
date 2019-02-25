package com.vip.qa.autov.core.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

public class FilePathUtils {

	// private static PathMatchingResourcePatternResolver resolver = new
	// PathMatchingResourcePatternResolver();

	public static String getRelativePath(String path) throws Exception {
		String urlPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + ".").getPath();
		urlPath = URLDecoder.decode(urlPath, "UTF-8");
		File currentFile = new File(urlPath).getParentFile().getParentFile();
		currentFile = new File(currentFile, path);

		return currentFile.getPath();
	}

	/**
	 * 解决idea和eclipse相对路径不一致的问题
	 * 
	 * @param prop
	 * @param defaultValue
	 * @return
	 * @throws IOException
	 */
	public static String getRelativePathFromProps(String prop, String defaultValue) {
		String path = ClassPathPropertiesUtils.getProperty(prop, defaultValue);
		try {
			return getRelativePath(path);
		}
		catch (FileNotFoundException e) {
			//Ignore
		}
		catch (Exception e) {
			Exceptions.checked(e);
		}
		return null;
	}

}
