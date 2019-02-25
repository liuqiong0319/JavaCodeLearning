package com.vip.qa.autov.core.extractor;

import java.util.List;

public interface Extractor {

	/**
	 * 通过表达式从字符串提取
	 * 
	 * @param expression
	 * @return
	 */
	String extract(String expression);

	/**
	 * 通过表达式获取匹配个数
	 * 
	 * @param expression
	 * @return
	 */
	int getCount(String expression);

	/**
	 * 通过表达式获取所有匹配的列表
	 * 
	 * @param expression
	 * @return
	 */
	List<String> extractSome(String expression);

	/**
	 * 通过判断表达式判断节点是否存在
	 * 
	 * @param expression
	 * @return
	 */
	boolean isExists(String expression);

	/**
	 * 通过表达式随机提取节点数据
	 * 
	 * @param expression
	 * @return
	 */
	String extractRandom(String expression);

}
