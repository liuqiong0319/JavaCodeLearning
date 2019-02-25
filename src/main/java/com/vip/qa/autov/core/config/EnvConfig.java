package com.vip.qa.autov.core.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvConfig {

	private String hosts;

	private Map<String, Properties> configs = new HashMap<>();

	public String getHosts() {
		return hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public Map<String, Properties> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, Properties> configs) {
		this.configs = configs;
	}

}
