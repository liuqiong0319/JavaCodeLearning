package com.vip.qa.autov.core.assertion;

import com.vip.qa.autov.core.extractor.JsonPathExtractor;
import com.vip.qa.autov.core.jsoncompare.ExtendedJSONCompare;
import com.vip.qa.autov.core.jsoncompare.ValueToStringJsonTokener;
import com.vip.qa.autov.core.jsoncompare.filter.ValueCompareFilter;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.JsonPathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JsonPathAsserter extends AbstractPathAsserter<JsonPathAsserter> {

	private static Logger logger = LoggerFactory.getLogger(JsonPathAsserter.class);

	/**
	 * Json字段值是否使用原始类型比较
	 */
	private boolean useOriginalType = false;

	public JsonPathAsserter(String actual) {
		super(actual, JsonPathAsserter.class);
		this.extractor = new JsonPathExtractor(actual);
	}

	/**
	 * 使用原始类型比较，则同时比较json字段的类型和值是否一致，否则只比较字段值
	 * 
//	 * @param useOriginalType
	 * @return
	 */
	public JsonPathAsserter useOriginalType() {
		this.useOriginalType = true;
		return this;
	}

	/**
	 * assert value extracted by path contains to expected value
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertContains(String[] paths, String expected) {
		Assert.assertTrue(((JsonPathExtractor) extractor).extract(paths).contains(expected));
		return this;
	}

	/**
	 * assert value extracted by path NOT contains to expected value
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertNotContains(String[] paths, String expected) {
		Assert.assertFalse(((JsonPathExtractor) extractor).extract(paths).contains(expected));
		return this;
	}

	/**
	 * assert value extracted by path matches regex
	 * 
	 * @param paths
//	 * @param expected
	 */
	public JsonPathAsserter assertRegexMatch(String[] paths, String regex) {
		String extracted = ((JsonPathExtractor) extractor).extract(paths);
		Assert.assertTrue(Pattern.matches(regex, extracted));
		return this;
	}

	/**
	 * assert value extracted by path does not match regex
	 * 
	 * @param paths
//	 * @param expected
	 */
	public JsonPathAsserter assertRegexNotMatch(String[] paths, String regex) {
		String extracted = ((JsonPathExtractor) extractor).extract(paths);
		Assert.assertFalse(Pattern.matches(regex, extracted));
		return this;
	}

	/**
	 * assert value extracted by path equals to expected object to string
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, Object expected) {
		Assert.assertEquals(((JsonPathExtractor) extractor).extract(paths), expected.toString());
		return this;
	}

	/**
	 * assert value extracted by path NOT equals to expected value
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, Object expected) {
		Assert.assertNotEquals(((JsonPathExtractor) extractor).extract(paths), expected);
		return this;
	}

	/**
	 * assert value extracted by path NOT equals to expected value
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, String expected) {
		Assert.assertNotEquals(((JsonPathExtractor) extractor).extract(paths), expected);
		return this;
	}

	/**
	 * assert value extracted by path equals to expected value ignore case
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertEqualsIgnoreCase(String[] paths, String expected) {
		Assert.assertEquals(((JsonPathExtractor) extractor).extract(paths).toLowerCase(), expected.toLowerCase());
		return this;
	}

	/**
	 * assert value extracted by path NOT equals to expected value ignore case
	 * 
	 * @param paths
	 * @param expected
	 */
	public JsonPathAsserter assertNotEqualsIgnoreCase(String[] paths, String expected) {
		Assert.assertNotEquals(((JsonPathExtractor) extractor).extract(paths).toLowerCase(), expected.toLowerCase());
		return this;
	}

	/**
	 * assert value extracted by path is blank
	 * 
	 * @param paths
//	 * @param expected
	 */
	public JsonPathAsserter assertBlank(String[] paths) {
		Assert.assertTrue(StringUtils.isBlank(((JsonPathExtractor) extractor).extract(paths)));
		return this;
	}

	/**
	 * assert value extracted by path is not blank
	 * 
	 * @param paths
//	 * @param expected
	 */
	public JsonPathAsserter assertNotBlank(String[] paths) {
		Assert.assertFalse(StringUtils.isBlank(((JsonPathExtractor) extractor).extract(paths)));
		return this;
	}

	/**
	 * 断言整形结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, int expected) {
		int actualValue = 0;
		try {
			actualValue = Integer.parseInt(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言整形结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, int expected) {
		int actualValue = 0;
		try {
			actualValue = Integer.parseInt(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言double类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, double expected) {
		double actualValue = 0D;
		try {
			actualValue = Double.parseDouble(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言double类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, double expected) {
		double actualValue = 0D;
		try {
			actualValue = Double.parseDouble(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言float类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, float expected) {
		float actualValue = 0F;
		try {
			actualValue = Float.parseFloat(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言float类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, float expected) {
		float actualValue = 0F;
		try {
			actualValue = Float.parseFloat(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言short类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, short expected) {
		short actualValue = 0;
		try {
			actualValue = Short.parseShort(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言short类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, short expected) {
		short actualValue = 0;
		try {
			actualValue = Short.parseShort(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, long expected) {
		long actualValue = 0L;
		try {
			actualValue = Long.parseLong(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, long expected) {
		long actualValue = 0L;
		try {
			actualValue = Long.parseLong(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言byte类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, byte expected) {
		byte actualValue = 0;
		try {
			actualValue = Byte.parseByte(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, byte expected) {
		byte actualValue = 0;
		try {
			actualValue = Byte.parseByte(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言boolean类型结果相等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertEquals(String[] paths, boolean expected) {
		boolean actualValue = false;
		try {
			actualValue = Boolean.parseBoolean(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言boolean类型结果不等
	 * 
	 * @param expected
	 */
	public JsonPathAsserter assertNotEquals(String[] paths, boolean expected) {
		boolean actualValue = false;
		try {
			actualValue = Boolean.parseBoolean(((JsonPathExtractor) extractor).extract(paths));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to boolean");
			return this;
		}
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * assert value extracted by path condition result is true
	 * 
	 * @param paths
	 * @param condition
	 */
	public JsonPathAsserter assertTrue(String[] paths, ExtractCondition condition) {
		Assert.assertTrue(condition.eval(((JsonPathExtractor) extractor).extract(paths)));
		return this;
	}

	/**
	 * assert value extracted by path condition result is false
	 * 
	 * @param paths
	 * @param condition
	 */
	public JsonPathAsserter assertFalse(String[] paths, ExtractCondition condition) {
		Assert.assertFalse(condition.eval(((JsonPathExtractor) extractor).extract(paths)));
		return this;
	}

	/**
	 * assert element count of the current json
	 * 
//	 * @param matcher
	 * @param expectedCount
	 */
	public JsonPathAsserter assertCount(int expectedCount) {
		Assert.assertEquals(((JsonPathExtractor) extractor).getCount(), expectedCount);
		return this;
	}

	/**
	 * 断言两个两个json结构一致，actual可存在比expected更多的json字段，字段顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchLenient(String expected) {
		assertMatch(expected, actual, JSONCompareMode.LENIENT, null);
		return this;
	}

	/**
	 * 断言两个两个json结构一致，actual可存在比expected更多的json字段，字段顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchLenient(String expected, ValueCompareFilter valueCompareFilter) {
		assertMatch(expected, actual, JSONCompareMode.LENIENT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言两个两个json结构一致，actual和expected的字段一样但是顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensible(String expected) {
		assertMatch(expected, actual, JSONCompareMode.NON_EXTENSIBLE, null);
		return this;
	}

	/**
	 * 断言两个两个json结构一致，actual和expected的字段一样但是顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensible(String expected, ValueCompareFilter valueCompareFilter) {
		assertMatch(expected, actual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter);
		return this;
	}

	/**
	 * 断言两个两个json顺序及结构完全一致
	 * 
	 */
	public JsonPathAsserter assertMatchStrict(String expected) {
		assertMatch(expected, actual, JSONCompareMode.STRICT, null);
		return this;
	}

	/**
	 * 断言两个两个json顺序及结构完全一致
	 * 
	 */
	public JsonPathAsserter assertMatchStrict(String expected, ValueCompareFilter valueCompareFilter) {
		assertMatch(expected, actual, JSONCompareMode.STRICT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言两个两个json顺序及结构一致，actual可存在比expected更多的json字段
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrder(String expected) {
		assertMatch(expected, actual, JSONCompareMode.STRICT_ORDER, null);
		return this;
	}

	/**
	 * 断言两个两个json顺序及结构一致，actual可存在比expected更多的json字段
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrder(String expected, ValueCompareFilter valueCompareFilter) {
		assertMatch(expected, actual, JSONCompareMode.STRICT_ORDER, valueCompareFilter);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json结构一致，
	 * actual可存在比expected更多的json字段， 字段顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchLenient(String expected, String... jsonPaths) {
		assertMatchLenient(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json结构一致，
	 * actual可存在比expected更多的json字段， 字段顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchLenient(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json结构一致，
	 * actual和expected的字段一样但是顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensible(String expected, String... jsonPaths) {
		assertMatchNonExtensible(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json结构一致，
	 * actual和expected的字段一样但是顺序可以不一致
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensible(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, extractedActual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json顺序及结构完全一致
	 * 
	 */
	public JsonPathAsserter assertMatchStrict(String expected, String... jsonPaths) {
		assertMatchStrict(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json顺序及结构完全一致
	 * 
	 */
	public JsonPathAsserter assertMatchStrict(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json顺序及结构一致，
	 * actual可存在比expected更多的json字段
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrder(String expected, String... jsonPaths) {
		assertMatchStrictOrder(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言jsonpath(多个则连续提取)对预期及返回结构提取json后，两个两个json顺序及结构一致，
	 * actual可存在比expected更多的json字段
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrder(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, extractedActual, JSONCompareMode.STRICT_ORDER, valueCompareFilter);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致( actual可存在比expected更多的json字段，
	 * 字段顺序可以不一致)
	 *
	 * 
	 */
	public JsonPathAsserter assertMatchLenientExtractActual(String expected, String... jsonPaths) {
		assertMatchLenientExtractActual(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致( actual可存在比expected更多的json字段，
	 * 字段顺序可以不一致)
	 *
	 * 
	 */
	public JsonPathAsserter assertMatchLenientExtractActual(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertMatch(expected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致( actual可存在比expected更多的json字段，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrderExtractActual(String expected, String... jsonPaths) {
		assertMatchStrictOrderExtractActual(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致( actual可存在比expected更多的json字段，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrderExtractActual(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertMatch(expected, extractedActual, JSONCompareMode.STRICT_ORDER, valueCompareFilter);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致(actual和expected字段一样，顺序可不一致)
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensibleExtractActual(String expected, String... jsonPaths) {
		assertMatchNonExtensibleExtractActual(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致(actual和expected字段一样，顺序可不一致)
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensibleExtractActual(String expected,
			ValueCompareFilter valueCompareFilter, String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertMatch(expected, extractedActual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致(actual和expected字段一样，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictExtractActual(String expected, String... jsonPaths) {
		assertMatchStrictExtractActual(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言实际结果通过一个或多个jsonpath提取后，和预期json结构一致(actual和expected字段一样，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictExtractActual(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertMatch(expected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致( actual可存在比expected更多的json字段，
	 * 字段顺序可以不一致)
	 *
	 * 
	 */
	public JsonPathAsserter assertMatchLenientExtractExpected(String expected, String... jsonPaths) {
		assertMatchLenientExtractExpected(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致( actual可存在比expected更多的json字段，
	 * 字段顺序可以不一致)
	 *
	 * 
	 */
	public JsonPathAsserter assertMatchLenientExtractExpected(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, actual, JSONCompareMode.LENIENT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致( actual可存在比expected更多的json字段，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrderExtractExpected(String expected, String... jsonPaths) {
		assertMatchStrictOrderExtractExpected(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致( actual可存在比expected更多的json字段，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictOrderExtractExpected(String expected,
			ValueCompareFilter valueCompareFilter, String... jsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, actual, JSONCompareMode.STRICT_ORDER, valueCompareFilter);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致(actual和expected字段一样，顺序可不一致)
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensibleExtractExpected(String expected, String... jsonPaths) {
		assertMatchNonExtensibleExtractExpected(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致(actual和expected字段一样，顺序可不一致)
	 * 
	 */
	public JsonPathAsserter assertMatchNonExtensibleExtractExpected(String expected,
			ValueCompareFilter valueCompareFilter, String... jsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, actual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致(actual和expected字段一样，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictExtractExpected(String expected, String... jsonPaths) {
		assertMatchStrictExtractExpected(expected, null, jsonPaths);
		return this;
	}

	/**
	 * 断言预期结果通过一个或多个jsonpath提取后，和实际json结构一致(actual和expected字段一样，顺序一致)
	 * 
	 */
	public JsonPathAsserter assertMatchStrictExtractExpected(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertMatch(extractedExpected, actual, JSONCompareMode.STRICT, valueCompareFilter);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，actual可存在比expected更多的json字段)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrder(String expected, ValueCompareFilter valueCompareFilter,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsEquals(expected, actual, JSONCompareMode.STRICT_ORDER, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，actual可存在比expected更多的json字段)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrder(String expected, String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrder(expected, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensible(String expected, ValueCompareFilter valueCompareFilter,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensible(String expected, String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensible(expected, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrict(String expected, String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrict(expected, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrict(String expected, ValueCompareFilter valueCompareFilter,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsEquals(expected, actual, JSONCompareMode.STRICT, valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenient(String expected, String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenient(expected, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenient(String expected, ValueCompareFilter valueCompareFilter,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsEquals(expected, actual, JSONCompareMode.LENIENT, valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderPartial(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderPartial(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderPartial(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.STRICT_ORDER,
				valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderPartial(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderPartial(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderPartial(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.STRICT_ORDER,
				valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensiblePartial(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensiblePartial(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensiblePartial(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.NON_EXTENSIBLE,
				valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected的字段一样)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensiblePartial(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensiblePartial(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensiblePartial(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.NON_EXTENSIBLE,
				valueCompareFilter, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictPartial(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictPartial(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictPartial(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictPartial(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictPartial(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected的字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictPartial(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientPartial(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientPartial(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientPartial(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientPartial(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientPartial(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言两个json经过jsonpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected多字段一样)
	 * 
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientPartial(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractActual(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderExtractActual(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractActual(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.STRICT_ORDER, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractActual(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderExtractActual(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractActual(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.STRICT_ORDER, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractActual(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictExtractActual(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractActual(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractActual(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictExtractActual(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractActual(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractActual(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensibleExtractActual(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractActual(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractActual(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensibleExtractActual(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractActual(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractActual(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientExtractActual(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractActual(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPath);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractActual(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientExtractActual(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractActual(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
		assertNotMatchFieldsEquals(expected, extractedActual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractExpected(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderExtractExpected(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractExpected(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.STRICT_ORDER, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractExpected(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictOrderExtractExpected(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual可存在比expected更多的json字段)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictOrderExtractExpected(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.STRICT_ORDER, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractExpected(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictExtractExpected(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractExpected(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractExpected(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsStrictExtractExpected(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言实际结果经jsonpath提取后和预期json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public JsonPathAsserter assertNotMatchFieldsStrictExtractExpected(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.STRICT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractExpected(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensibleExtractExpected(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractExpected(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractExpected(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.NON_EXTENSIBLE, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsNonExtensibleExtractExpected(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsNonExtensibleExtractExpected(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractExpected(String expected, String jsonPath,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientExtractExpected(expected, jsonPath, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractExpected(String expected, String jsonPath,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractExpected(String expected, String[] jsonPaths,
			String... notMatchedJsonPaths) {
		assertNotMatchFieldsLenientExtractExpected(expected, jsonPaths, null, notMatchedJsonPaths);
		return this;
	}

	/**
	 * 断言预期结果经jsonpath提取后和实际json对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致，
	 * 预期不匹配字段可以比实际多)
	 */
	public JsonPathAsserter assertNotMatchFieldsLenientExtractExpected(String expected, String[] jsonPaths,
			ValueCompareFilter valueCompareFilter, String... notMatchedJsonPaths) {
		String extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		assertNotMatchFieldsEquals(extractedExpected, actual, JSONCompareMode.LENIENT, valueCompareFilter,
				notMatchedJsonPaths);
		return this;
	}

	/**
	 * assert json array match ignoring order, note: this method compares each
	 * element of the expected and actual json array by counnting every element
	 * in the json array having a match in the expected array
	 * 
	 * @param jsonPaths
	 *            do not specify if no need to extract
	 * @param expected
	 */
	public JsonPathAsserter assertArrayMatchIgnoreOrder(String expected, String... jsonPaths) {
		assertArrayMatchIgnoreOrder(expected, null, jsonPaths);
		return this;
	}

	/**
	 * assert json array match ignoring order, note: this method compares each
	 * element of the expected and actual json array by counnting every element
	 * in the json array having a match in the expected array
	 * 
	 * @param jsonPaths
	 *            do not specify if no need to extract
	 * @param expected
	 */
	public JsonPathAsserter assertArrayMatchIgnoreOrder(String expected, ValueCompareFilter valueCompareFilter,
			String... jsonPaths) {
		String extractedActual = actual;
		String extractedExpected = expected;
		if (jsonPaths != null) {
			extractedActual = ((JsonPathExtractor) extractor).extract(jsonPaths);
			extractedExpected = new JsonPathExtractor(expected).extract(jsonPaths);
		}

		if (extractedActual == null && extractedExpected == null) {
			return this;
		}

		List<String> actualList = new JsonPathExtractor(extractedActual).extractList1("$");
		List<String> expectedList = new JsonPathExtractor(extractedExpected).extractList1("$");
		int matchCount = 0;
		for (String expectedJson : expectedList) {
			for (String actualJson : actualList) {
				JSONCompareResult jsonCompareResult;
				try {
					jsonCompareResult = ExtendedJSONCompare.compareJSON(expectedJson, actualJson,
							JSONCompareMode.LENIENT, useOriginalType, valueCompareFilter);
					boolean passed = jsonCompareResult.passed();
					if (passed) {
						matchCount++;
					}
					if (matchCount >= expectedList.size()) {
						break;
					}

				} catch (JSONException e) {
					Exceptions.checked(e);
				}
			}
		}

		Assert.assertTrue(matchCount == expectedList.size(), "Array expected match count is " + expectedList.size()
				+ ", but actual is " + matchCount);

		return this;
	}

	/**
	 * 断言json匹配（去掉不无需匹配的field）
	 * 
	 * @param expected
	 * @param actual
	 * @param jsonCompareMode
	 * @param notMatchFields
	 *            无需做匹配的field的jsonpath
	 */
	private void assertNotMatchFieldsEquals(String expected, String actual, JSONCompareMode jsonCompareMode,
			ValueCompareFilter valueCompareFilter, String... notMatchFields) {
		Validate.notEmpty(notMatchFields);
		// expected = formatJson(expected);
		// actual = formatJson(actual);
		for (String unmatched : notMatchFields) {
			try {
				if (expected != null) {
					expected = JsonPathUtils.delete(expected, unmatched);
				}
			} catch (Exception e) {
			}
			try {
				if (actual != null) {
					actual = JsonPathUtils.delete(actual, unmatched);
				}
			} catch (Exception e) {
			}
		}
		assertMatch(expected, actual, jsonCompareMode, valueCompareFilter);
	}

	/**
	 * 断言匹配
	 * 
	 * @param expected
	 * @param actual
	 * @param jsonCompareMode
	 */
	private void assertMatch(String expected, String actual, JSONCompareMode jsonCompareMode,
			ValueCompareFilter valueCompareFilter) {
		if ((expected == null && actual != null) || (expected != null && actual == null)) {
			throw new RuntimeException("[Expected]: " + expected + ", but [Actual]: " + actual);
		}
		// expected = formatJson(expected);
		// actual = formatJson(actual);
		JSONCompareResult jsonCompareResult;
		try {
			jsonCompareResult = ExtendedJSONCompare.compareJSON(expected, actual, jsonCompareMode, useOriginalType,
					valueCompareFilter);
			boolean passed = jsonCompareResult.passed();
			if (!passed) {
				logger.error("[Expected]: " + expected + ", but [Actual]: " + actual);
				throw new RuntimeException(jsonCompareResult.getMessage());
			}

		} catch (JSONException e) {
			Assert.fail(e.getMessage());
		}
	}

	// /**
	// * 去掉json模板里面的注释，先个反序列化格式化掉然后再序列化还原
	// *
	// * @param json
	// * @return
	// */
	// private static String formatJson(String json) {
	//
	// Object obj = JsonMapper.fromJson(json, Object.class);
	// return JsonMapper.toJson(obj);
	// }

	public static void main(String[] args) throws JSONException {
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		// System.out.println(JSON.toJSONString(map,
		// SerializerFeature.WriteNonStringValueAsString));
		System.out.println(new JSONObject(new ValueToStringJsonTokener("{\"a\":1231231.33000}", false)));
	}

}
