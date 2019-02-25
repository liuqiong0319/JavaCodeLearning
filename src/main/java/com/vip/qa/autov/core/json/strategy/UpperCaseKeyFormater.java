package com.vip.qa.autov.core.json.strategy;

public class UpperCaseKeyFormater implements JsonKeyFormater {

	@Override
	public String format(String key) {
		return key.toUpperCase();
	}

}
