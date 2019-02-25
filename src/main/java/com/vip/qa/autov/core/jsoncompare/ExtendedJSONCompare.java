package com.vip.qa.autov.core.jsoncompare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import com.vip.qa.autov.core.jsoncompare.filter.ValueCompareFilter;

/**
 * Provides API to compare two JSON entities. This is the backend to
 * {@link JSONAssert}, but it can be programmed against directly to access the
 * functionality. (eg, to make something that works with a non-JUnit test
 * framework)
 */
public final class ExtendedJSONCompare {
	private ExtendedJSONCompare() {
	}

	private static JSONComparator getComparatorForMode(JSONCompareMode mode, ValueCompareFilter filter) {
		return new ExtendedComparator(mode, filter);
	}

	/**
	 * Compares JSON string provided to the expected JSON string using provided
	 * comparator, and returns the results of the comparison.
	 * 
	 * @param expectedStr
	 *            Expected JSON string
	 * @param actualStr
	 *            JSON string to compare
	 * @param comparator
	 *            Comparator to use
	 * @param useOriginalType
	 * @return result of the comparison
	 * @throws JSONException
	 * @throws IllegalArgumentException
	 *             when type of expectedStr doesn't match the type of actualStr
	 */
	public static JSONCompareResult compareJSON(String expectedStr, String actualStr, JSONComparator comparator,
			JSONCompareMode mode, boolean useOriginalType) throws JSONException {
		Object expected = null;
		Object actual = null;
		if (JSONCompareMode.LENIENT == mode) {
			expected = ValueToStringJsonParser.parseJSON(expectedStr, useOriginalType);
			actual = ValueToStringJsonParser.parseJSON(actualStr, useOriginalType);
		} else {
			expected = ValueToStringJsonParser.parseJSON(expectedStr, true);
			actual = ValueToStringJsonParser.parseJSON(actualStr, true);
		}
		if ((expected instanceof JSONObject) && (actual instanceof JSONObject)) {
			return compareJSON((JSONObject) expected, (JSONObject) actual, comparator);
		} else if ((expected instanceof JSONArray) && (actual instanceof JSONArray)) {
			return compareJSON((JSONArray) expected, (JSONArray) actual, comparator);
		} else if (expected instanceof JSONString && actual instanceof JSONString) {
			return compareJson((JSONString) expected, (JSONString) actual);
		} else if (expected instanceof JSONObject) {
			return new JSONCompareResult().fail("", expected, actual);
		} else {
			return new JSONCompareResult().fail("", expected, actual);
		}
	}

	/**
	 * Compares JSON object provided to the expected JSON object using provided
	 * comparator, and returns the results of the comparison.
	 * 
	 * @param expected
	 *            expected json object
	 * @param actual
	 *            actual json object
	 * @param comparator
	 *            comparator to use
	 * @return result of the comparison
	 * @throws JSONException
	 */
	public static JSONCompareResult compareJSON(JSONObject expected, JSONObject actual, JSONComparator comparator)
			throws JSONException {
		return comparator.compareJSON(expected, actual);
	}

	/**
	 * Compares JSON object provided to the expected JSON object using provided
	 * comparator, and returns the results of the comparison.
	 * 
	 * @param expected
	 *            expected json array
	 * @param actual
	 *            actual json array
	 * @param comparator
	 *            comparator to use
	 * @return result of the comparison
	 * @throws JSONException
	 */
	public static JSONCompareResult compareJSON(JSONArray expected, JSONArray actual, JSONComparator comparator)
			throws JSONException {
		return comparator.compareJSON(expected, actual);
	}

	/**
	 * Compares {@link JSONString} provided to the expected {@code JSONString},
	 * checking that the {@link org.json.JSONString#toJSONString()} are equal.
	 *
	 * @param expected
	 *            Expected {@code JSONstring}
	 * @param actual
	 *            {@code JSONstring} to compare
	 */
	public static JSONCompareResult compareJson(final JSONString expected, final JSONString actual) {
		return JSONCompare.compareJson(expected, actual);
	}

	/**
	 * Compares JSON string provided to the expected JSON string, and returns
	 * the results of the comparison.
	 *
	 * @param expectedStr
	 *            Expected JSON string
	 * @param actualStr
	 *            JSON string to compare
	 * @param mode
	 *            Defines comparison behavior
	 * @param useOriginalType
	 * @throws JSONException
	 */
	public static JSONCompareResult compareJSON(String expectedStr, String actualStr, JSONCompareMode mode,
			boolean useOriginalType, ValueCompareFilter filter) throws JSONException {
		return compareJSON(expectedStr, actualStr, getComparatorForMode(mode, filter), mode, useOriginalType);
	}

	/**
	 * Compares JSONObject provided to the expected JSONObject, and returns the
	 * results of the comparison.
	 *
	 * @param expected
	 *            Expected JSONObject
	 * @param actual
	 *            JSONObject to compare
	 * @param mode
	 *            Defines comparison behavior
	 * @throws JSONException
	 */
	public static JSONCompareResult compareJSON(JSONObject expected, JSONObject actual, JSONCompareMode mode,
			ValueCompareFilter filter) throws JSONException {
		return compareJSON(expected, actual, getComparatorForMode(mode, filter));
	}

	/**
	 * Compares JSONArray provided to the expected JSONArray, and returns the
	 * results of the comparison.
	 *
	 * @param expected
	 *            Expected JSONArray
	 * @param actual
	 *            JSONArray to compare
	 * @param mode
	 *            Defines comparison behavior
	 * @throws JSONException
	 */
	public static JSONCompareResult compareJSON(JSONArray expected, JSONArray actual, JSONCompareMode mode,
			ValueCompareFilter filter) throws JSONException {
		return compareJSON(expected, actual, getComparatorForMode(mode, filter));
	}

}
