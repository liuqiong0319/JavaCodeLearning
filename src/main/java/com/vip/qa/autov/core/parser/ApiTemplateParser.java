package com.vip.qa.autov.core.parser;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.utils.FreeMarkerUtils;
import com.vip.qa.autov.core.utils.GroovyEvalUtils;

/**
 * 通过freemarker解析api入参模板
 * 
 * @author alex.ma
 *
 */
public class ApiTemplateParser implements Parser {

	private String dir;

	private String file;

	private String suffix;

	public ApiTemplateParser(String dir, String file) {
		this(dir, file, null);
	}

	public ApiTemplateParser(String dir, String file, String suffix) {
		this.dir = dir;
		this.file = file;
		this.suffix = suffix;
	}

	public static File getTemplateFileWithSuffix(String dir, String file, String suffix) {
		String fileName = file;
		File templateDir = new File(AutoVConsts.TEMPLATE_API_DIR + File.separator + dir);
		if (StringUtils.isNotBlank(suffix)) {
			fileName += "_" + suffix;
			File templateFile = new File(templateDir, fileName);
			if (!templateFile.exists()) {
				fileName = file;
			}
		}
		return new File(templateDir, fileName);
	}

	/**
	 * Parse json template file to json String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String parse(Map params) {
		File templateFile = getTemplateFileWithSuffix(dir, file, suffix);

		String result = FreeMarkerUtils.parseFile(templateFile, params);
		result = GroovyEvalUtils.eval(result, params);
		// 模板如果有注释需要通过序列化反序列化去掉
		Object obj = JsonMapper.fromJson(result, Object.class);
		return JsonMapper.toJson(obj);
	}
}
