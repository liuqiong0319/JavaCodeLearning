package com.vip.qa.autov.core.response;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.api.ApiRequester;
import com.vip.qa.autov.core.parser.Parser;
import com.vip.qa.autov.core.parser.RespTemplateParser;

/**
 * Api的响应模板解析类
 * 
 * @author alex.ma
 *
 * @param <T>
 */
public abstract class AbstractApiTemplateResponser<T> extends AbstractRespTemplateResponser<T> {

	private static final String TEMPLATE_RESP_DIR = AutoVConsts.TEMPLATE_RESP_DIR;

	public static final String TEMPLATE_RESP_API_DIR = "api";

	protected ApiRequester<?> currentRequester;

	public AbstractApiTemplateResponser() {
		super();
	}

	public AbstractApiTemplateResponser(T response) {
		super(response);
	}

	/**
	 * 从template_resp文件夹获取并解析响应模板
	 * 
	 * @param templateParams
	 * @return
	 */
	public String getTemplateResponse(Map<String, ?> templateParams) {

		File dir = new File(TEMPLATE_RESP_DIR + File.separator + TEMPLATE_RESP_API_DIR);
		Validate.isTrue(dir.exists());
		File templateDir = new File(dir, getDir());
		Validate.isTrue(templateDir.exists(), "template dir does not exist!");
		String name = getName() + "_" + getSuffix();
		File file = new File(templateDir, name);
		if (!file.exists()) {
			file = new File(templateDir, getName());
		}
		Parser parser = new RespTemplateParser(file);
		String expectResp = parser.parse(templateParams);
		return expectResp;
	}

	@Override
	protected String getTemplateRespBaseDir() {
		return TEMPLATE_RESP_API_DIR;
	}

	/**
	 * 获取api requester实例（通过 api requester或者api respository接口发起请求才能获取）
	 * 
	 * @return
	 */
	public ApiRequester<?> getCurrentRequester() {
		return currentRequester;
	}

	public void setCurrentRequester(ApiRequester<?> currentRequester) {
		this.currentRequester = currentRequester;
	}

	protected abstract String getName();

	protected abstract String getDir();

	protected abstract String getSuffix();

}
