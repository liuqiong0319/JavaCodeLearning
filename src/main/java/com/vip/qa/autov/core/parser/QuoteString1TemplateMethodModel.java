package com.vip.qa.autov.core.parser;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * freemarker自定义函数，用于对json串转义然后加上双引号
 * 
 * @author alex.ma
 *
 */
public class QuoteString1TemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "quoteStr1";

	private Map<String, String> params;

	public QuoteString1TemplateMethodModel(Map<String, String> params) {
		this.params = params;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments.size() == 0) {
			throw new TemplateModelException("Wrong number of arguments");
		}
		Object arg = arguments.get(0);
		if (arg == null || arg.getClass().getSimpleName().equals("EmptyStringAndSequence")) {
			return "null";
		}

		String result = StringEscapeUtils.escapeJson(arg.toString());
		if (arguments.size() > 1) {
			Object arg1 = arguments.get(1);
			if (arg1 instanceof TemplateBooleanModel) {
				if (((TemplateBooleanModel) arg1).getAsBoolean()) {
					result = params.get(result);
				}
			}
		}
		if (result == null) {
			return "null";
		}

		return "\"" + result + "\"";
	}
}
