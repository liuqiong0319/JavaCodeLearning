package com.vip.qa.autov.core.config;

public enum ConfigTypeEnum {

	HOSTS("hosts"), MEMCACHED("memcached"), DB("db"), REDIS("redis"), SERVER("server"), ZK("zk"), OSP("osp");

	String value;

	ConfigTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
