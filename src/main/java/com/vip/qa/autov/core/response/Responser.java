package com.vip.qa.autov.core.response;

import com.vip.qa.autov.core.assertion.AsserterBuilder;
import com.vip.qa.autov.core.extractor.ExtractorBuilder;

public interface Responser<T> {

	/**
	 * 获取响应json序列化后的字符串
	 * 
	 * @return
	 */
	String getResult();

	/**
	 * 获取真实响应
	 * 
	 * @return
	 */
	T getResponse();

	/**
	 * 断言器
	 * 
	 * @return
	 */
	AsserterBuilder asserter();

	/**
	 * 提取器
	 * 
	 * @return
	 */
	ExtractorBuilder extractor();

}
