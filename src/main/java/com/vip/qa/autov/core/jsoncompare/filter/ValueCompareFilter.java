package com.vip.qa.autov.core.jsoncompare.filter;

import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * 用于指定自定义value对比逻辑
 * 
 * @author alex.ma
 *
 */
public interface ValueCompareFilter {

	/**
	 * 
	 * @param key
	 *            json的key
	 * @param expectedValue
	 * @param actualValue
	 * @param mode
	 * @return 返回passed表示人工设置为验证通过，返回failed表示为人工设置为验证失败，设置为compare表示使用默认方式对比
	 */
	ValueCompareResult process(String key, Object expectedValue, Object actualValue, JSONCompareMode mode);

	public static enum ValueCompareResult {

		/**
		 * 设为比较通过
		 */
		PASSED,

		/**
		 * 设置为比较失败
		 */
		FAILED,

		/**
		 * 使用默认比较方式
		 */
		COMPARE
	}
}
