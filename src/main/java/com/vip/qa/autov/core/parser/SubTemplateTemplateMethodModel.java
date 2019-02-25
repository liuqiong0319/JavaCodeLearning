package com.vip.qa.autov.core.parser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;

import com.vip.qa.autov.core.utils.FreeMarkerUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 调子模板，map参数传{tempateName}#{key}格式可替换子模板里面key值
 * 
 * @author alex.ma
 *
 */
public class SubTemplateTemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "callTemplate";

	@SuppressWarnings("rawtypes")
	private Map paramMap;

	private File parentTemplateFile;

	@SuppressWarnings("rawtypes")
	public SubTemplateTemplateMethodModel(File parentTemplateFile, Map paramMap) {
		this.paramMap = paramMap;
		this.parentTemplateFile = parentTemplateFile;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		int argSize = arguments.size();
		if (argSize != 1 && argSize != 2) {
			throw new TemplateModelException(
					"Wrong number of arguments, please use callTemplate(templateName, templateKey) or callTemplate(templateName)");
		}
		Object arg = arguments.get(0);
		Object arg1 = null;
		if (arguments.size() > 1) {
			arg1 = arguments.get(1);
		}

		if (arg == null || arg.getClass().getSimpleName().equals("EmptyStringAndSequence")
				|| arg.toString().equals("null")) {
			return null;
		}
		Map templateParams = new HashMap();
		String templateName = arg.toString();
		Validate.notBlank(templateName, "模板名称必传!");
		if (templateName.contains("/")) {
			templateName = StringUtils.substringAfterLast(templateName, "/");
		}
		templateName = StringUtils.substringBeforeLast(templateName, ".");

		if (paramMap != null && StringUtils.isNotBlank(templateName)) {
			Set<Entry> entries = paramMap.entrySet();
			for (Entry entry : entries) {
				String key = entry.getKey().toString();
				// key格式为{模板名}/{key}并且模板名称和子模板名称一致，则做替换
				String[] pair = key.split("#", argSize + 1);
				if (pair.length > 1) {
					String tName = pair[0];
					boolean shouldAdd = tName.equals(templateName);
					if (!shouldAdd) {
						continue;
					}
					String tKey = null;
					String paramName = null;
					if (argSize == 1 || arg1 == null) {
						paramName = pair[1];
					} else if (pair.length == 3) {
						paramName = pair[2];
						tKey = pair[1];
						shouldAdd = shouldAdd && tKey.equals(arg1.toString());
					}
					if (shouldAdd) {
						templateParams.put(paramName, entry.getValue());
					}

				}
			}
		}
		return FreeMarkerUtils.parseFile(new File(parentTemplateFile.getParent(), arg.toString()).getPath(),
				templateParams);
	}
}
