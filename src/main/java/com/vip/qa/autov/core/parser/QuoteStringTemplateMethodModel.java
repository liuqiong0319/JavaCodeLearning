package com.vip.qa.autov.core.parser;

import java.util.List;
import java.util.Map;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * freemarker自定义函数，用于对字符串加上双引号
 * 
 * @author alex.ma
 *
 */
public class QuoteStringTemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "quoteStr";

	private Map<String, String> params;

	public QuoteStringTemplateMethodModel(Map<String, String> params) {
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

		String result = arg.toString();

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
