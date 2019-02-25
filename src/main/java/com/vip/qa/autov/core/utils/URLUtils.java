package com.vip.qa.autov.core.utils;

import com.vip.qa.autov.core.AutoVConsts;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class URLUtils {

	public static String appendParamsToUrl(String url, MultiValueMap params, String encoding) {
		String appendix = paramsToUrl(params, encoding);
		if (StringUtils.isNotBlank(appendix)) {
			if (url.contains("?")) {
				url += "&" + appendix;
			} else {
				url += "?" + appendix;
			}
		}
		return url;
	}

	public static String appendParamsToUrl2(String url, Map<String, Object> params, String encoding) {
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			for (String key : params.keySet()) {
				try {
					sb.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), encoding)).append
							("&");
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		if (StringUtils.isNotBlank(sb)) {
			if (url.contains("?")) {
				return url + "&" + sb.toString();
			} else {
				return url + "?" + sb.toString();
			}
		}
		return url;
	}

	public static Map<String, String> getUrlParams(String str, String encoding) throws UnsupportedEncodingException {
		String[] params = str.split("&");
		Map<String, String> map = new HashMap<>();
		for (String param : params) {
			if (StringUtils.isNotBlank(param)) {
				String[] pair = param.split("=", 2);
				map.put(pair[0], pair.length == 2 ? URLDecoder.decode(pair[1], encoding) : null);
			}
		}
		return map;
	}

	public static String paramsToUrl(MultiValueMap params, String encoding) {
		if (params.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object entryObj : params.entrySet()) {
			Entry entry = (Entry) entryObj;

			String key = entry.getKey().toString();
			if (key.equals(AutoVConsts.HTTP_POST_DATA_PARAM_NAME)) {
				continue;
			}

			List values = (List) entry.getValue();

			for (Object value : values) {
				try {
					sb.append(entry.getKey() + "=" + URLEncoder.encode(value.toString(), encoding)).append("&");
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		int lastIdx = sb.lastIndexOf("&");
		if (lastIdx > 0) {
			sb.deleteCharAt(lastIdx);
		}

		return sb.toString();
	}

	public static String completeUrl(String url) {
		url = url.trim();
		if (url.indexOf("://") < 0) {
			return "http://" + url;
		}
		return url;
	}

	public static String parseHostFromUrl(String url) {
		String host = url;
		if (host.indexOf("://") > 0) {
			host = StringUtils.substringAfter(host, "://");
		}
		if (host.indexOf(":") > 0) {
			return StringUtils.substringBefore(host, ":");
		}
		if (host.indexOf("/") > 0) {
			return StringUtils.substringBefore(host, "/");
		}
		return host;
	}

}
