package com.vip.qa.autov.core.testcase;

import com.vip.qa.autov.core.params.ParamMap;

public class ParamHolder {

	private static ThreadLocal<ParamMap<String, ?>[]> paramsCache = new ThreadLocal<ParamMap<String, ?>[]>();

	/**
	 * get current ParamMap list
	 * 
	 * @return
	 */
	public static ParamMap<String, ?>[] getParams() {
		return paramsCache.get();
	}

	/**
	 * get first param map
	 * 
	 * @return
	 */
	public static ParamMap<String, ?> getParam() {
		ParamMap<String, ?>[] params = paramsCache.get();
		if (params != null && params.length > 0) {
			return params[0];
		}
		return null;
	}

	@SafeVarargs
	public static void setParams(ParamMap<String, ?>... paramsList) {
		if (paramsList != null) {
			paramsCache.set(paramsList);
			for (ParamMap<String, ?> param : paramsList) {
				param.clearTemp();
			}
		}
	}

	public static void main(String[] args) {
	}

}
