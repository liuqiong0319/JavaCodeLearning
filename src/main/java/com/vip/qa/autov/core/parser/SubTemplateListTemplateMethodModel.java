package com.vip.qa.autov.core.parser;

import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.utils.FreeMarkerUtils;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 调子模板，map参数传{tempateName}#{key}格式可替换子模板里面key值
 * 
 * @author alex.ma
 *
 */
public class SubTemplateListTemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "callTemplateList";

	@SuppressWarnings("rawtypes")
	private Map paramMap;

	private File parentTemplateFile;

	@SuppressWarnings("rawtypes")
	public SubTemplateListTemplateMethodModel(File parentTemplateFile, Map paramMap) {
		this.paramMap = paramMap;
		this.parentTemplateFile = parentTemplateFile;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		int argSize = arguments.size();
		if (argSize != 2) {
			throw new TemplateModelException(
					"Wrong number of arguments, please use callTemplateList(templateName,paramCount)");
		}
		Object arg = arguments.get(0);
		Object arg1 = arguments.get(1);

		if (arg == null || arg.getClass().getSimpleName().equals("EmptyStringAndSequence")
				|| arg.toString().equals("null")) {
			return null;
		}

		if (arg1 == null || arg1.getClass().getSimpleName().equals("EmptyStringAndSequence")) {
			return null;
		}

		int count = ((SimpleNumber) arg1).getAsNumber().intValue();
		if (count == 0) {
			return "[]";
		}

		String templateName = arg.toString();
		Validate.notBlank(templateName, "模板名称必传!");
		if (templateName.contains("/")) {
			templateName = StringUtils.substringAfterLast(templateName, "/");
		}
		templateName = StringUtils.substringBeforeLast(templateName, ".");

		Map<Integer, Map<String, Object>> listParams = new ConcurrentHashMap<>();

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
						Map<String, Object> listParam = listParams.get(parseListParam.index);
						if (listParam == null) {
							listParam = new HashMap<>();
							listParams.put(parseListParam.index, listParam);
						}
						listParam.put(parseListParam.name, entry.getValue());
					}
				}
			}
		}

		List<Object> params = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Map<String, Object> listParam = listParams.get(i);
			Object listObj = FreeMarkerUtils.parseFileToObject(
					new File(parentTemplateFile.getParent(), arg.toString()).getPath(), listParam);
			params.add(listObj);
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
		String idx = str1.substring(1, str1.lastIndexOf("]"));
		int index = Integer.parseInt(idx);
		String name = pair[1];
		ListParam listParam = new ListParam();
		listParam.index = index;
		listParam.name = name;
		return listParam;
	}

	static class ListParam {
		int index;
		String name;
	}
}
