package com.vip.qa.autov.core.json.strategy;

public class UnderlineToCamelKeyFormater implements JsonKeyFormater {

	@Override
	public String format(String key) {
		return underlineToCamel(key);
	}

	private static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
