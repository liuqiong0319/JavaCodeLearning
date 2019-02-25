package com.vip.qa.autov.core.api.caching;

import com.vip.qa.autov.core.AutoVConsts;

public class ApiCacheModel {

	public static final String KEY_SEPERATOR = "@";

	String key;

	String response;

	String request;

	String name;

	String suffix;

	String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getApiKey() {
		return AutoVConsts.API_CACHE_KEY_PREFIX + key;
	}

	@SuppressWarnings({"unchecked","deprecation"})
	//  注解@SuppressWarnings用来压制程序中出来的警告，比如在没有用泛型或是方法已经过时的时候.
	public String getTsCacheKey() {
		String joinedKey = AutoVConsts.API_CACHE_TS_KEY_PREFIX + name + KEY_SEPERATOR + suffix;
		return joinedKey;
	}

	public String getRespCacheKey() {
		String joinedKey = AutoVConsts.API_CACHE_RESP_KEY_PREFIX + name + KEY_SEPERATOR + suffix;
		return joinedKey;
	}

	public String getReqCacheKey() {
		String joinedKey = AutoVConsts.API_CACHE_REQ_KEY_PREFIX + name + KEY_SEPERATOR + suffix;
		return joinedKey;
	}

}
