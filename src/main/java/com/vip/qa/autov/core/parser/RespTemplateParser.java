package com.vip.qa.autov.core.parser;

import java.io.File;
import java.util.Map;

import com.vip.qa.autov.core.json.JsonMapper;
import com.vip.qa.autov.core.utils.FreeMarkerUtils;
import com.vip.qa.autov.core.utils.GroovyEvalUtils;

/**
 * 解析想用模板
 * 
 * @author alex.ma
 *
 */
public class RespTemplateParser implements Parser {

	private File file;

	public RespTemplateParser(File file) {
		this.file = file;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String parse(Map params) {
		if (!file.isFile()) {
			throw new IllegalArgumentException("File provided must be a file!");
		}
		String result = FreeMarkerUtils.parseFile(file, params);
		result = GroovyEvalUtils.eval(result, params);
		// 模板如果有注释需要通过序列化反序列化去掉
		Object obj = JsonMapper.fromJson(result, Object.class);
		return JsonMapper.toJson(obj);
	}
}
