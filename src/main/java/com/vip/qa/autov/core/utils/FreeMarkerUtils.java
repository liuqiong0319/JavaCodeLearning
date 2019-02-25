package com.vip.qa.autov.core.utils;

import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.parser.Json2StringTemplateMethodModel;
import com.vip.qa.autov.core.parser.QuoteString1TemplateMethodModel;
import com.vip.qa.autov.core.parser.QuoteStringTemplateMethodModel;
import com.vip.qa.autov.core.parser.SubTemplate1TemplateMethodModel;
import com.vip.qa.autov.core.parser.SubTemplateListTemplateMethodModel;
import com.vip.qa.autov.core.parser.SubTemplateMapTemplateMethodModel;
import com.vip.qa.autov.core.parser.SubTemplateTemplateMethodModel;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerUtils {

	private static final String STRING_TEMPLATE_NAME = "string_template";

	/**
	 * 通过freemarker解析字符串
	 * 
	 * @param value
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String parse(String value, Map params, File templateFile) {
		if (value == null) {
			return null;
		}
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		StringWriter writer = new StringWriter();

		stringLoader.putTemplate(STRING_TEMPLATE_NAME, value);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setNumberFormat("#.########");
		cfg.setClassicCompatible(true);
		cfg.setTemplateLoader(stringLoader);
		params.put(Json2StringTemplateMethodModel.METHOD_NAME, Json2StringTemplateMethodModel.getInstance());
		params.put(QuoteStringTemplateMethodModel.METHOD_NAME, new QuoteStringTemplateMethodModel(params));
		params.put(QuoteString1TemplateMethodModel.METHOD_NAME, new QuoteString1TemplateMethodModel(params));
		params.put(SubTemplateTemplateMethodModel.METHOD_NAME, new SubTemplateTemplateMethodModel(templateFile, params));
		params.put(SubTemplate1TemplateMethodModel.METHOD_NAME, new SubTemplate1TemplateMethodModel(templateFile,
				params));
		params.put(SubTemplateListTemplateMethodModel.METHOD_NAME, new SubTemplateListTemplateMethodModel(templateFile,
				params));
		params.put(SubTemplateMapTemplateMethodModel.METHOD_NAME, new SubTemplateMapTemplateMethodModel(templateFile,
				params));
		try {
			Template template = cfg.getTemplate(STRING_TEMPLATE_NAME);
			template.process(params, writer);
		} catch (Exception e) {
			Exceptions.checked(e);
		} finally {
			params.remove(Json2StringTemplateMethodModel.METHOD_NAME);
			params.remove(QuoteStringTemplateMethodModel.METHOD_NAME);
			params.remove(QuoteString1TemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateTemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplate1TemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateListTemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateMapTemplateMethodModel.METHOD_NAME);
		}
		return writer.toString();
	}

	/**
	 * 通过freemarker解析字符串
	 * 
	 * @param value
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String parse(String value, Map params) {
		if (value == null) {
			return null;
		}
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		StringWriter writer = new StringWriter();

		stringLoader.putTemplate(STRING_TEMPLATE_NAME, value);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setNumberFormat("#.########");
		cfg.setClassicCompatible(true);
		cfg.setTemplateLoader(stringLoader);

		try {
			Template template = cfg.getTemplate(STRING_TEMPLATE_NAME);
			template.process(params, writer);
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		return writer.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String parseFile(File templateFile, Map params) {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setNumberFormat("#.########");
			cfg.setClassicCompatible(true);
			cfg.setDirectoryForTemplateLoading(templateFile.getParentFile());
			if (params == null) {
				params = new HashMap();
			}
			params.put(Json2StringTemplateMethodModel.METHOD_NAME, Json2StringTemplateMethodModel.getInstance());
			params.put(QuoteStringTemplateMethodModel.METHOD_NAME, new QuoteStringTemplateMethodModel(params));
			params.put(QuoteString1TemplateMethodModel.METHOD_NAME, new QuoteString1TemplateMethodModel(params));
			params.put(SubTemplateTemplateMethodModel.METHOD_NAME, new SubTemplateTemplateMethodModel(templateFile,
					params));
			params.put(SubTemplate1TemplateMethodModel.METHOD_NAME, new SubTemplate1TemplateMethodModel(templateFile,
					params));
			params.put(SubTemplateListTemplateMethodModel.METHOD_NAME, new SubTemplateListTemplateMethodModel(
					templateFile, params));
			params.put(SubTemplateMapTemplateMethodModel.METHOD_NAME, new SubTemplateMapTemplateMethodModel(
					templateFile, params));

			Template template = cfg.getTemplate(templateFile.getName());
			StringWriter writer = new StringWriter();
			template.process(params, writer);
			String result = writer.toString();
			return result;
		} catch (Exception e) {
			Exceptions.checked(e);
		} finally {
			params.remove(Json2StringTemplateMethodModel.METHOD_NAME);
			params.remove(QuoteStringTemplateMethodModel.METHOD_NAME);
			params.remove(QuoteString1TemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateTemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplate1TemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateListTemplateMethodModel.METHOD_NAME);
			params.remove(SubTemplateMapTemplateMethodModel.METHOD_NAME);
		}
		return null;
	}

	/**
	 * 通过freemarker解析模板文件
	 * 
	 * @param templateFile
	 *            根目录下模板相对路径
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String parseFile(String path, Map params) {
		File templateFile = new File(path);
		return parseFile(templateFile, params);
	}

	/**
	 * 通过freemarker对解析模板文件后的json字符串反序列化为对象
	 * 
	 * @param path
	 *            根目录下模板相对路径
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object parseFileToObject(String path, Map params) {
		String json = parseFile(path, params);
		return JsonMapper.fromJson(json, Object.class);
	}

	/**
	 * 通过freemarker对解析模板文件后的json字符串反序列化为对象
	 * 
	 * @param templateFile
	 *            根目录下模板相对路径
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object parseFileToObject(File templateFile, Map params) {
		String json = parseFile(templateFile, params);
		return JsonMapper.fromJson(json, Object.class);
	}

	public static void main(String[] args) {
		Map params = new HashMap();
		params.put("param", "param1");
		params.put("param1AA", null);
		System.out.println(FreeMarkerUtils.parseFile(new File("aaa").getAbsolutePath(), params));
	}
}
