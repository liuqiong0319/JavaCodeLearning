package com.vip.qa.autov.core.jsoncompare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class ValueToStringJsonParser {

	// regular expression to match a number in JSON format. see
	// http://www.json.org/fatfree.html.
	// "A number can be represented as integer, real, or floating point. JSON
	// does not support octal or hex
	// ... [or] NaN or Infinity".
	private static final String NUMBER_REGEX = "-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?";

	private ValueToStringJsonParser() {
	}

	/**
	 * Takes a JSON string and returns either a {@link org.json.JSONObject} or
	 * {@link org.json.JSONArray}, depending on whether the string represents an
	 * object or an array.
	 *
	 * @param s
	 *            Raw JSON string to be parsed
	 * @return JSONObject or JSONArray
	 * @throws JSONException
	 *             JSON parsing error
	 */
	public static Object parseJSON(final String s, boolean ignoreDoubleType) throws JSONException {
		if (s.trim().startsWith("{")) {
			return new JSONObject(new ValueToStringJsonTokener(s, ignoreDoubleType));
		} else if (s.trim().startsWith("[")) {
			return new JSONArray(new ValueToStringJsonTokener(s, ignoreDoubleType));
		} else if (s.trim().startsWith("\"") || s.trim().matches(NUMBER_REGEX)) {
			return new JSONString() {
				@Override
				public String toJSONString() {
					return s;
				}
			};
		}
		throw new JSONException("Unparsable JSON string: " + s);
	}
}
