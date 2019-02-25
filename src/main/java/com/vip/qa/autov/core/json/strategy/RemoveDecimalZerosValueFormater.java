package com.vip.qa.autov.core.json.strategy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.math.NumberUtils;

import com.vip.qa.autov.core.utils.Exceptions;

public class RemoveDecimalZerosValueFormater implements JsonValueFormater {

	private static DecimalFormat decimalFormat = new DecimalFormat("#.#");

	@Override
	public Object format(String key, Object value) {
		try {
			if (value instanceof Number) {
				return new BigDecimal(decimalFormat.parse(value.toString()).toString());
			} else if (value instanceof String && NumberUtils.isCreatable(value.toString())) {
				String strValue = value.toString();
				if (strValue.contains(".")) {
					return decimalFormat.parse(strValue).toString();
				}
			}
		} catch (ParseException e) {
			Exceptions.checked(e);
		}
		return value;
	}

}
