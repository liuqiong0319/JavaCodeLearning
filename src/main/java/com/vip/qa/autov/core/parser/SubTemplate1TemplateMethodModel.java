package com.vip.qa.autov.core.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;

import com.vip.qa.autov.core.utils.FreeMarkerUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 调子模板，子模板参数全部替换
 * 
 * @author alex.ma
 *
 */
public class SubTemplate1TemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "callTemplate1";

	@SuppressWarnings("rawtypes")
	private Map paramMap;

	private File parentTemplateFile;

	@SuppressWarnings("rawtypes")
	public SubTemplate1TemplateMethodModel(File parentTemplateFile, Map paramMap) {
		this.paramMap = paramMap;
		this.parentTemplateFile = parentTemplateFile;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		int argSize = arguments.size();
		if (argSize != 1) {
			throw new TemplateModelException("Wrong number of arguments, please use callTemplate1(templateName)");
		}
		Object arg = arguments.get(0);

		if (arg == null || arg.getClass().getSimpleName().equals("EmptyStringAndSequence")
				|| arg.toString().equals("null")) {
			return null;
		}
		Map templateParams = paramMap;
		String templateName = arg.toString();
		Validate.notBlank(templateName, "模板名称必传!");
		if (templateName.contains("/")) {
			templateName = StringUtils.substringAfterLast(templateName, "/");
		}
		templateName = StringUtils.substringBeforeLast(templateName, ".");

		return FreeMarkerUtils.parseFile(new File(parentTemplateFile.getParent(), arg.toString()).getPath(),
				templateParams);
	}
}
