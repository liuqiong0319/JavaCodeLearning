package com.vip.qa.autov.core.json.strategy;

public class NoneValueFormater implements JsonValueFormater {

	@Override
	public Object format(String key, Object value) {
		return value;
	}

}
