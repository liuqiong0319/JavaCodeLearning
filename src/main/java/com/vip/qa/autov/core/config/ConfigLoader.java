package com.vip.qa.autov.core.config;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.Exceptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	private EnvConfig remoteConfig;

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private static ConfigLoader instance = new ConfigLoader();

	private ConfigLoader() {

	}

	public static ConfigLoader getInstance() {
		return instance;
	}

	/**
	 * 获取VTP和本地配置合并后的配置
	 * 
	 * @param propertiesType
	 * @return
	 */
	public Properties getConfig(ConfigTypeEnum propertiesType) {
		String value = propertiesType.getValue();
		// 先读本地默认配置
		Properties config = ClassPathPropertiesUtils.getPropertiesByFileName(propertiesType.getValue() + ".properties");

		// vtp配置覆盖本地默认配置
		if (remoteConfig != null) {
			Properties vtpProp = remoteConfig.getConfigs().get(propertiesType.value);
			if (vtpProp != null) {
				config.putAll(vtpProp);
			}
		}

		// profile配置覆盖vtp配置
		Properties profileConfig = getProfileConfigFromFile(value);
		if (profileConfig != null) {
			config.putAll(profileConfig);
		}

		return config;
	}

	public String getHosts() {
		String activeProfile = AutoVConsts.getActiveProfile();
		String hostFileName = AutoVConsts.HOSTS_FILE;

		String hosts = null;
		Resource resource = null;

		try {
			// 先读本地Profile的hosts
			resource = resourceLoader.getResource("classpath:/profiles/" + activeProfile + "/" + hostFileName);
			if (resource.exists()) {
				hosts = IOUtils.toString(resource.getInputStream(), "UTF-8");
			}

			// 本地没有profile文件夹没有hosts配置则读vtp的hosts配置
//			if (remoteConfig != null && hosts == null) {
//				String vtpHosts = remoteConfig.getHosts();
//				if (vtpHosts != null) {
//					hosts = vtpHosts;
//				}
//			}

			// 如果VTP和profile都没有hosts文件，则读取默认根目录配置
			if (hosts == null) {
				resource = resourceLoader.getResource("classpath:/" + hostFileName);
				if (resource.exists()) {
					hosts = FileUtils.readFileToString(resource.getFile(), "UTF-8");
				}
			}
		} catch (IOException e) {
			Exceptions.checked(e);
		}

		return hosts;
	}

	private static Properties getProfileConfigFromFile(String configType) {
		Properties properties = ClassPathPropertiesUtils.getPropertiesFromProfile(configType + ".properties",
				configType + ".local.properties");
		return properties;
	}

	public EnvConfig getRemoteConfig() {
		return remoteConfig;
	}

	public void setRemoteConfig(EnvConfig remoteConfig) {
		this.remoteConfig = remoteConfig;
	}

}
