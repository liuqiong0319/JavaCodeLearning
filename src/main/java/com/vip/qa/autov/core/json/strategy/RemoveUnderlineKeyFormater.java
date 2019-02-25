package com.vip.qa.autov.core.json.strategy;

public class RemoveUnderlineKeyFormater implements JsonKeyFormater {

	@Override
	public String format(String key) {
		return key.replace("_", "");
	}

}
