package com.vip.qa.autov.core.json;

import com.vip.qa.autov.core.json.strategy.JsonKeyFormater;

/**
 * 对Json的key进行转换的策略
 * 
 * @author alex.ma
 *
 */
public enum JsonKeyFormatStrategy {

	/**
	 * 转大写
	 */
	UPPPER_CASE,
	/**
	 * 转小写
	 */
	LOWER_CASE,
	/**
	 * 首字母大写
	 */
	CAPITALIZE,
	/**
	 * 首字母小写
	 */
	UNCAPITALIZE,
	/**
	 * 下划线转驼峰，如product_id，转productId
	 */
	UNDERLINE_TO_CAMEL,
	/**
	 * 驼峰转下划线，如productId转product_id
	 */
	CAMEL_TO_UNDERLINE,

	/**
	 * 去掉下划线，如product_id转productid
	 */
	REMOVE_UNDERLINE,

	/**
	 * 不做转换
	 */
	NONE;

	public JsonKeyFormater asFormater() {
		return JsonFormaterFactory.getKeyFormater(this);
	}
}
