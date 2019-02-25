package com.vip.qa.autov.core.utils;

import java.net.URLDecoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class MapUtils {

	public static Map<String, String> makeTreeMap(Map<String, String> map, final boolean asc) {
		if (map == null) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return asc ? o1.compareTo(o2) : o2.compareTo(o1);
			}

		});
		sortMap.putAll(map);
		return sortMap;
	}

	public static Map<String, String> urlParamToMap(String urlParam) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isNotBlank(urlParam)) {
			String[] pairs = urlParam.split("&");
			for (String pair : pairs) {
				if (!pair.contains("=")) {
					continue;
				}
				String[] kv = pair.split("=", 2);
				try {
					map.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
				} catch (Exception e) {
				}
			}
		}

		return map;
	}
}
