package com.vip.qa.autov.core.response;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.parser.Parser;
import com.vip.qa.autov.core.parser.RespTemplateParser;

public abstract class AbstractRespTemplateResponser<T> extends AbstractResponser<T> {

	private static final String TEMPLATE_RESP_DIR = AutoVConsts.TEMPLATE_RESP_DIR;

	public AbstractRespTemplateResponser() {
		super();
	}

	public AbstractRespTemplateResponser(T response) {
		super(response);
	}

	/**
	 * 从template_resp文件夹获取并解析响应模板
	 * 
	 * @param path
	 *            file path under base dir
	 * @param templateParams
	 * @return
	 */
	public String getTemplateResponse(String path, Map<String, ?> templateParams) {
		String dirPath = getTemplateRespBaseDir();
		File dir = new File(TEMPLATE_RESP_DIR + File.separator + dirPath);
		Validate.isTrue(dir.exists(), "template dir: " + dirPath + " does not exists");
		File file = new File(dir, path);
		Parser parser = new RespTemplateParser(file);
		String expectResp = parser.parse(templateParams);
		return expectResp;
	}

	protected abstract String getTemplateRespBaseDir();
}
