package com.vip.qa.autov.core.jsoncompare;

import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.allJSONObjects;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.allSimpleValues;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.arrayOfJsonObjectToMap;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.findUniqueKey;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.formatUniqueKey;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.isUsableAsUniqueKey;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

import com.vip.qa.autov.core.jsoncompare.filter.ValueCompareFilter;
import com.vip.qa.autov.core.jsoncompare.filter.ValueCompareFilter.ValueCompareResult;

public class ExtendedComparator extends DefaultComparator {

	JSONCompareMode mode;

	private ValueCompareFilter valueCompareFilter;

	public ExtendedComparator(JSONCompareMode mode, ValueCompareFilter valueCompareFilter) {
		super(mode);
		this.mode = mode;
		this.valueCompareFilter = valueCompareFilter;
	}

	@Override
	public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result)
			throws JSONException {
		ValueCompareResult compareResult = getCompareStatus(prefix, expectedValue, actualValue);
		if (compareResult != null) {
			if (ValueCompareResult.PASSED == compareResult) {
				return;
			} else if (ValueCompareResult.FAILED == compareResult) {
				result.fail(prefix, expectedValue, actualValue);
				return;
			}
		}
		super.compareValues(prefix, expectedValue, actualValue, result);
	}

	@Override
	public void compareJSONArray(String prefix, JSONArray expected, JSONArray actual, JSONCompareResult result)
			throws JSONException {
		if (expected.length() != actual.length()) {
			if (mode != JSONCompareMode.LENIENT) {
				result.fail(prefix + "[]: Expected " + expected.length() + " values but got " + actual.length());
				return;
			}
		} else if (expected.length() == 0) {
			return; // Nothing to compare
		}

		if (expected.length() == 0 && mode == JSONCompareMode.LENIENT) {
			return;
		} else if (mode.hasStrictOrder()) {
			compareJSONArrayWithStrictOrder(prefix, expected, actual, result);
		} else if (allSimpleValues(expected)) {
			compareJSONArrayOfSimpleValues(prefix, expected, actual, result);
		} else if (allJSONObjects(expected)) {
			compareJSONArrayOfJsonObjects(prefix, expected, actual, result);
		} else {
			// An expensive last resort
			recursivelyCompareJSONArray(prefix, expected, actual, result);
		}
	}

	@Override
	protected void compareJSONArrayOfJsonObjects(String key, JSONArray expected, JSONArray actual,
			JSONCompareResult result) throws JSONException {
		String uniqueKey = findUniqueKey(expected);
		if (uniqueKey == null || !isUsableAsUniqueKey(uniqueKey, actual)) {
			// An expensive last resort
			recursivelyCompareJSONArray(key, expected, actual, result);
			return;
		}

		Map<Object, JSONObject> expectedValueMap = arrayOfJsonObjectToMap(expected, uniqueKey);
		Map<Object, JSONObject> actualValueMap = arrayOfJsonObjectToMap(actual, uniqueKey);

		// compareValues(formatUniqueKey(key, uniqueKey, "*"), expected, actual,
		// result);

		for (Entry<Object, JSONObject> entry : expectedValueMap.entrySet()) {

			Object id = entry.getKey();
			if (!actualValueMap.containsKey(id)) {
				result.missing(formatUniqueKey(key, uniqueKey, id), expectedValueMap.get(id));
				continue;
			}
			JSONObject expectedValue = expectedValueMap.get(id);
			JSONObject actualValue = actualValueMap.get(id);
			compareValues(formatUniqueKey(key, uniqueKey, id), expectedValue, actualValue, result);
		}
		int count = 0;
		for (Object id : actualValueMap.keySet()) {
			if (count > expected.length()) {
				if (mode == JSONCompareMode.LENIENT) {
					break;
				}
			}
			if (!expectedValueMap.containsKey(id)) {
				result.unexpected(formatUniqueKey(key, uniqueKey, id), actualValueMap.get(id));
			}
			count++;
		}
	}

	@Override
	protected void recursivelyCompareJSONArray(String key, JSONArray expected, JSONArray actual,
			JSONCompareResult result) throws JSONException {
		Set<Integer> matched = new HashSet<Integer>();
		for (int i = 0; i < expected.length(); ++i) {
			Object expectedElement = expected.get(i);
			boolean matchFound = false;
			JSONCompareResult objResult = null;
			for (int j = 0; j < actual.length(); ++j) {
				Object actualElement = actual.get(j);
				if (matched.contains(j) || !actualElement.getClass().equals(expectedElement.getClass())) {
					continue;
				}
				if (expectedElement instanceof JSONObject) {
					JSONCompareResult thisResult = compareJSON((JSONObject) expectedElement, (JSONObject) actualElement);
					if (thisResult.passed()) {
						matched.add(j);
						matchFound = true;
						break;
					} else {
						if (objResult == null) {
							objResult = thisResult;
						}
					}
				} else if (expectedElement instanceof JSONArray) {
					if (compareJSON((JSONArray) expectedElement, (JSONArray) actualElement).passed()) {
						matched.add(j);
						matchFound = true;
						break;
					}
				} else if (expectedElement.equals(actualElement)) {
					matched.add(j);
					matchFound = true;
					break;
				}
			}
			if (!matchFound) {
				String msg = key + "[" + i + "] Could not find match for element " + expectedElement;
				if (objResult != null) {
					msg += "。 Unmatched object result is " + objResult.getMessage();
				}
				result.fail(msg);
				return;
			}
		}
	}

	@Override
	public boolean areNotSameDoubles(Object expectedValue, Object actualValue) {
		return !expectedValue.toString().equals(actualValue.toString());
	}

	private ValueCompareResult getCompareStatus(String prefix, Object expected, Object actual) {
		if (valueCompareFilter != null) {
			ValueCompareResult result = valueCompareFilter.process(prefix, expected, actual, mode);
			return result;

		}
		return null;
	}
}
