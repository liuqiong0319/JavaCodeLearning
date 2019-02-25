package com.vip.qa.autov.core.parser;

import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.utils.FreeMarkerUtils;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 调子模板，map参数传{tempateName}#{key}格式可替换子模板里面key值
 * 
 * @author alex.ma
 *
 */
public class SubTemplateMapTemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "callTemplateMap";

	@SuppressWarnings("rawtypes")
	private Map paramMap;

	private File parentTemplateFile;

	@SuppressWarnings("rawtypes")
	public SubTemplateMapTemplateMethodModel(File parentTemplateFile, Map paramMap) {
		this.paramMap = paramMap;
		this.parentTemplateFile = parentTemplateFile;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		int argSize = arguments.size();
		if (argSize != 1) {
			throw new TemplateModelException("Wrong number of arguments, please use callTemplateMap(templateName)");
		}
		Object arg = arguments.get(0);

		if (arg == null || arg.getClass().getSimpleName().equals("EmptyStringAndSequence")
				|| arg.toString().equals("null")) {
			return null;
		}

		String templateName = arg.toString();
		Validate.notBlank(templateName, "模板名称必传!");
		if (templateName.contains("/")) {
			templateName = StringUtils.substringAfterLast(templateName, "/");
		}
		templateName = StringUtils.substringBeforeLast(templateName, ".");

		Map<Object, Map<String, Object>> mapParams = new ConcurrentHashMap<>();

		if (paramMap != null && StringUtils.isNotBlank(templateName)) {

			Set<Entry> entries = paramMap.entrySet();
			for (Entry entry : entries) {
				String key = entry.getKey().toString();
				// key格式为{模板名}/{key}并且模板名称和子模板名称一致，则做替换
				String[] pair = key.split("#", 2);
				if (pair.length > 1) {
					String tName = pair[0];
					boolean shouldAdd = tName.equals(templateName);
					if (!shouldAdd) {
						continue;
					}
					String paramName = pair[1];
					if (StringUtils.isNotBlank(paramName)) {
						ListParam parseListParam = parseListParam(paramName);
						Map<String, Object> listParam = mapParams.get(parseListParam.key);
						if (listParam == null) {
							listParam = new HashMap<>();
							mapParams.put(parseListParam.key, listParam);
						}
						listParam.put(parseListParam.name, entry.getValue());
					}
				}
			}
		}

		Map<Object, Object> params = new HashMap<>();
		for (Entry<Object, Map<String, Object>> entry : mapParams.entrySet()) {
			Object obj = FreeMarkerUtils.parseFileToObject(
					new File(parentTemplateFile.getParent(), arg.toString()).getPath(), mapParams);
			params.put(entry.getKey(), obj);
		}

		return JsonMapper.toJsonPretty(params);
	}

	private ListParam parseListParam(String paramName) {
		String[] pair = paramName.split("\\.", 2);
		if (pair.length == 0) {
			throw new InvalidParameterException("Paramater name format must be [n].paramName");
		}
		String str1 = pair[0];
		if (!str1.startsWith("[") && !str1.endsWith("]")) {
			throw new InvalidParameterException("Paramater index of list must be be in [n] format");
		}
		String keyStr = str1.substring(1, str1.lastIndexOf("]"));
		Object key = keyStr;
		if (keyStr.startsWith("'") && keyStr.endsWith("'")) {
			key = keyStr.substring(1, keyStr.length() - 1);
		} else {
			if (keyStr.equals("true") || keyStr.equals("false")) {
				key = Boolean.valueOf(keyStr);
			} else if (NumberUtils.isNumber(keyStr)) {
				key = new BigDecimal(keyStr);
			}
		}

		ListParam listParam = new ListParam();
		listParam.key = key;
		if (pair.length > 1) {
			String name = pair[1];
			listParam.name = name;
		}
		return listParam;
	}

	static class ListParam {
		Object key;
		String name;
	}
}
