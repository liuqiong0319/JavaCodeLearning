package com.vip.qa.autov.core.json;

import com.vip.qa.autov.core.json.strategy.JsonValueFormater;

/**
 * 对Json的所有value进行转换的策略
 * 
 * @author alex.ma
 *
 */
public enum JsonValueFormatStrategy {

	/**
	 * 小数最后的0去掉
	 */
	REMOVE_DECIMAL_ZEROS,

	/**
	 * 用科学计数法格式化
	 */
	USE_SCIENTIFIC_NOTATION,

	/**
	 * 不做处理
	 */
	NONE;

	public JsonValueFormater asFormater() {
		return JsonFormaterFactory.getValueFormater(this);
	}

}
