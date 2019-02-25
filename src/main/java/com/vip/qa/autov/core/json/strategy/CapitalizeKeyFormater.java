package com.vip.qa.autov.core.json.strategy;

import org.apache.commons.lang3.StringUtils;

public class CapitalizeKeyFormater implements JsonKeyFormater {

	@Override
	public String format(String key) {
		return StringUtils.capitalize(key);
	}

}
