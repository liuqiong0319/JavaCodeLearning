package com.vip.qa.autov.core.json;

import com.vip.qa.autov.core.json.strategy.*;

import java.util.concurrent.ConcurrentHashMap;

public class JsonFormaterFactory {

	private static final ConcurrentHashMap<JsonKeyFormatStrategy, JsonKeyFormater> keyFormaterMap = new ConcurrentHashMap<JsonKeyFormatStrategy, JsonKeyFormater>(
			JsonKeyFormatStrategy.values().length);

	private static final ConcurrentHashMap<JsonValueFormatStrategy, JsonValueFormater> valueFormaterMap = new ConcurrentHashMap<JsonValueFormatStrategy, JsonValueFormater>(
			JsonValueFormatStrategy.values().length);

	public static JsonKeyFormater getKeyFormater(JsonKeyFormatStrategy strategy) {
		JsonKeyFormater formater = keyFormaterMap.get(strategy);
		if (formater != null) {
			return formater;
		}
		switch (strategy) {
		case UPPPER_CASE:
			formater = new UpperCaseKeyFormater();
			break;
		case LOWER_CASE:
			formater = new LowerCaseKeyFormater();
			break;
		case CAPITALIZE:
			formater = new CapitalizeKeyFormater();
			break;
		case UNCAPITALIZE:
			formater = new UncapitalizeKeyFormater();
			break;
		case UNDERLINE_TO_CAMEL:
			formater = new UnderlineToCamelKeyFormater();
			break;
		case CAMEL_TO_UNDERLINE:
			formater = new CamelToUnderlineKeyFromater();
			break;
		case REMOVE_UNDERLINE:
			formater = new RemoveUnderlineKeyFormater();
			break;
		default:
			formater = new NoneKeyFormater();
			break;
		}
		keyFormaterMap.put(strategy, formater);
		return formater;
	}

	public static JsonValueFormater getValueFormater(JsonValueFormatStrategy strategy) {
		JsonValueFormater formater = valueFormaterMap.get(strategy);
		if (formater != null) {
			return formater;
		}
		switch (strategy) {
		case REMOVE_DECIMAL_ZEROS:
			formater = new RemoveDecimalZerosValueFormater();
			break;
		case USE_SCIENTIFIC_NOTATION:
			formater = new UseScientificNotationValueFormater();
			break;
		default:
			formater = new NoneValueFormater();
			break;
		}
		valueFormaterMap.put(strategy, formater);
		return formater;
	}
}
