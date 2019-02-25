package com.vip.qa.autov.core.api;

import java.util.Map;

import com.vip.qa.autov.core.request.Requester;
import com.vip.qa.autov.core.response.Responser;

public interface ApiRequester<T extends Responser<?>> extends Requester {

	/**
	 * call api using template
	 * 
	 * @param name
	 *            api name
	 * @param templateParams
	 *            params passed to template
	 * @return
	 */
	T call(String name, Map<String, ?> templateParams);

	/**
	 * call api using template
	 * 
	 * @param name
	 *            api name
	 * @param templateParams
	 *            params passed to template
	 * @param templateFileSuffix
	 *            template suffix with _suffix, e.g, getVendorProduct_1.0.0,
	 *            often used for version control of templates
	 * @return
	 */
	T call(String name, Map<String, ?> templateParams, String templateFileSuffix);

	/**
	 * call api using template without passing param
	 * 
	 * @param name
	 * @return
	 */
	T call(String name);

	/**
	 * call api using template and suffix with passing params
	 * 
	 * @param name
	 *            api name
	 * @param templateFileSuffix
	 *            template suffix with _suffix, e.g, getVendorProduct_1.0.0,
	 *            often used for version control of templates
	 * @return
	 */
	T call(String name, String templateFileSuffix);

	/**
	 * 获取当前request的json串（通过requester实例或者api repository接口发请求才能获取）
	 * 
	 * @return
	 */
	String getRequestString();

}
