package com.vip.qa.autov.core.parser;

import java.util.List;

import com.vip.qa.autov.core.json.JsonMapper;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * freemarker自定义函数，用于对json串转义
 * 
 * @author alex.ma
 *
 */
@Deprecated
public class Json2StringTemplateMethodModel implements TemplateMethodModelEx {

	public static final String METHOD_NAME = "json2String";

	private static Json2StringTemplateMethodModel instance;

	private Json2StringTemplateMethodModel() {
	}

	public static Json2StringTemplateMethodModel getInstance() {
		if (instance == null) {
			instance = new Json2StringTemplateMethodModel();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments.get(0) == null) {
			return null;
		}
		return JsonMapper.toJson(arguments.get(0).toString());
	}

}
