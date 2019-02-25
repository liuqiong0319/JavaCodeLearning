package com.vip.qa.autov.core.json.strategy;

import java.math.BigDecimal;

public class UseScientificNotationValueFormater implements JsonValueFormater {

	@Override
	public Object format(String key, Object value) {
		if (value instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) value;
			return bigValue.doubleValue();
		}
		return value;
	}
}
