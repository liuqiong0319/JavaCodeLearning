package com.vip.qa.autov.core.json;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.vip.qa.autov.core.json.JsonMapper.MultiValueMapFilter;
import com.vip.qa.autov.core.json.strategy.JsonKeyFormater;
import com.vip.qa.autov.core.json.strategy.JsonValueFormater;
import com.vip.qa.autov.core.json.strategy.NoneKeyFormater;
import com.vip.qa.autov.core.json.strategy.NoneValueFormater;

public class JsonFormatter {

	private static DecimalFormat decimalFormat = new DecimalFormat("#.#");

	private static MultiValueMapFilter multiValueMapFilter = new MultiValueMapFilter();

	public static String formatKey(final String json, final JsonKeyFormater formater, SerializerFeature... features) {
		if (json == null) {
			return null;
		}
		final Object obj = JsonMapper.fromJson(json, Object.class);
		NameFilter filter = new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				if (formater == null) {
					return name;
				}
				return formater.format(name);
			}

		};
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter, filter }, features);
	}

	/**
	 * 格式化json的key，例如大小写转换等
	 * 
	 * @param json
	 * @param strategy
	 * @param features
	 * @return
	 */
	@Deprecated
	public static String formatKey(final String json, final JsonKeyFormatStrategy strategy,
			SerializerFeature... features) {
		JsonKeyFormater formater = strategy == null ? new NoneKeyFormater() : strategy.asFormater();
		return formatKey(json, formater, features);
	}

	public static String formatValue(final String json, final JsonValueFormater formater, SerializerFeature... features) {
		if (json == null) {
			return null;
		}

		ValueFilter filter = new ValueFilter() {

			@Override
			public Object process(Object object, String name, Object value) {
				if (formater == null) {
					return value;
				}
				return formater.format(name, value);
			}
		};
		final Object obj = JsonMapper.fromJson(json, Object.class);
		return JSON.toJSONString(obj, new SerializeFilter[] { multiValueMapFilter, filter }, features);
	}

	/**
	 * 格式化json的value，Deprecated,使用formatValue(final String json, final JsonValueFormatStrategyImpl strategy,
	 * SerializerFeature... features)
	 * 
	 * @param json
	 * @param strategy
	 * @param features
	 * @return
	 */
	@Deprecated
	public static String formatValue(final String json, final JsonValueFormatStrategy strategy,
			SerializerFeature... features) {
		JsonValueFormater formater = strategy == null ? new NoneValueFormater() : strategy.asFormater();
		return formatValue(json, formater, features);
	}

	// private static String convertKey(String key, JsonKeyFormatStrategy strategy) {
	// if (strategy != null) {
	// switch (strategy) {
	// case UPPPER_CASE:
	// key = key.toUpperCase();
	// break;
	// case LOWER_CASE:
	// key = key.toLowerCase();
	// break;
	// case CAPITALIZE:
	// key = StringUtils.capitalize(key);
	// break;
	// case UNCAPITALIZE:
	// key = StringUtils.uncapitalize(key);
	// break;
	// case UNDERLINE_TO_CAMEL:
	// key = underlineToCamel(key);
	// break;
	// case CAMEL_TO_UNDERLINE:
	// key = camelToUnderline(key);
	// break;
	// case REMOVE_UNDERLINE:
	// key = key.replace("_", "");
	// break;
	// }
	// }
	// return key;
	// }

	// private static Object convertValue(Object value, JsonValueFormatStrategy strategy) {
	// if (strategy != null) {
	// switch (strategy) {
	// case REMOVE_DECIMAL_ZEROS:
	// try {
	// if (value instanceof Number) {
	//
	// value = new BigDecimal(decimalFormat.parse(value.toString()).toString());
	// } else if (value instanceof String && NumberUtils.isCreatable(value.toString())) {
	// String strValue = value.toString();
	// if (strValue.contains(".")) {
	// value = decimalFormat.parse(strValue).toString();
	// }
	//
	// }
	// } catch (ParseException e) {
	// Exceptions.checked(e);
	// }
	// break;
	// case USE_SCIENTIFIC_NOTATION:
	// if (value instanceof BigDecimal) {
	// BigDecimal bigValue = (BigDecimal) value;
	// value = bigValue.doubleValue();
	// }
	// break;
	// default:
	// break;
	// }
	// }
	// return value;
	// }

	public static void main(String[] args) throws ParseException {
		System.out.println(formatValue("{\"prebilledTaxAmount\":5.11000333333331}",
				JsonValueFormatStrategy.REMOVE_DECIMAL_ZEROS));
	}

}
