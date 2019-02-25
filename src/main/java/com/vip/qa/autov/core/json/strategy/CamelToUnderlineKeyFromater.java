package com.vip.qa.autov.core.json.strategy;

public class CamelToUnderlineKeyFromater implements JsonKeyFormater {

	@Override
	public String format(String key) {
		return camelToUnderline(key);
	}

	private static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append("_");
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
