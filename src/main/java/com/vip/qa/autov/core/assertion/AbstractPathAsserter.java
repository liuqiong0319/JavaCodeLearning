package com.vip.qa.autov.core.assertion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.vip.qa.autov.core.extractor.AbstractExtractor;
import com.vip.qa.autov.core.utils.Exceptions;

public abstract class AbstractPathAsserter<T extends AbstractPathAsserter<T>> {

	protected String actual;
	protected AbstractExtractor extractor;
	protected final T myself;

	private static Logger logger = LoggerFactory.getLogger(AbstractPathAsserter.class);

	public AbstractPathAsserter(String actual, Class<T> selfType) {
		this.actual = actual;
		this.myself = (selfType.cast(this));
	}

	/**
	 * assert value extracted by path contains to expected value
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertContains(String path, String expected) {
		Assert.assertTrue(extractor.extract(path).contains(expected));
		return myself;
	}

	/**
	 * assert value extracted by path NOT contains to expected value
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertNotContains(String path, String expected) {
		Assert.assertFalse(extractor.extract(path).contains(expected));
		return myself;
	}

	/**
	 * assert value extracted by path matches regex
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertRegexMatch(String path, String regex) {
		String extracted = extractor.extract(path);
		Assert.assertTrue(Pattern.matches(regex, extracted));
		return myself;
	}

	/**
	 * assert value extracted by path does not match regex
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertRegexNotMatch(String path, String regex) {
		String extracted = extractor.extract(path);
		Assert.assertFalse(Pattern.matches(regex, extracted));
		return myself;
	}

	/**
	 * assert value extracted by path equals to expected object to string
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertEquals(String path, Object expected) {
		Assert.assertEquals(extractor.extract(path), expected.toString());
		return myself;
	}

	/**
	 * assert value extracted by path NOT equals to expected value
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertNotEquals(String path, Object expected) {
		Assert.assertNotEquals(extractor.extract(path), expected);
		return myself;
	}

	/**
	 * assert value extracted by path NOT equals to expected value
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertNotEquals(String path, String expected) {
		Assert.assertNotEquals(extractor.extract(path), expected);
		return myself;
	}

	/**
	 * assert value extracted by path equals to expected value ignore case
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertEqualsIgnoreCase(String path, String expected) {
		Assert.assertEquals(extractor.extract(path).toLowerCase(), expected.toLowerCase());
		return myself;
	}

	/**
	 * assert value extracted by path NOT equals to expected value ignore case
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertNotEqualsIgnoreCase(String path, String expected) {
		Assert.assertNotEquals(extractor.extract(path).toLowerCase(), expected.toLowerCase());
		return myself;
	}

	/**
	 * assert value extracted by path is blank
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertBlank(String path) {
		if (actual != null) {
			Assert.assertTrue(StringUtils.isBlank(extractor.extract(path)));
		}
		return myself;
	}

	/**
	 * assert value extracted by path is not blank
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertNotBlank(String path) {
		if (actual == null) {
			Assert.fail("Actual is null");
		}
		Assert.assertFalse(StringUtils.isBlank(extractor.extract(path)));
		return myself;
	}

	/**
	 * assert value extracted by path is null
	 * 
	 * @param path
	 */
	public T assertNull(String path) {
		if (actual != null) {
			Assert.assertNull(extractor.extract(path));
		}

		return myself;
	}

	/**
	 * assert value extracted by path is not null
	 * 
	 * @param path
	 */
	public T assertNotNull(String path) {
		if (actual == null) {
			Assert.fail("Actual is null");
		}
		Assert.assertNotNull(extractor.extract(path));
		return myself;
	}

	/**
	 * 断言整形结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, int expected) {
		int actualValue = 0;
		try {
			actualValue = Integer.parseInt(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言整形结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, int expected) {
		int actualValue = 0;
		try {
			actualValue = Integer.parseInt(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言double类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, double expected) {
		double actualValue = 0D;
		try {
			actualValue = Double.parseDouble(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言double类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, double expected) {
		double actualValue = 0D;
		try {
			actualValue = Double.parseDouble(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言float类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, float expected) {
		float actualValue = 0F;
		try {
			actualValue = Float.parseFloat(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言float类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, float expected) {
		float actualValue = 0F;
		try {
			actualValue = Float.parseFloat(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言short类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, short expected) {
		short actualValue = 0;
		try {
			actualValue = Short.parseShort(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言short类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, short expected) {
		short actualValue = 0;
		try {
			actualValue = Short.parseShort(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言long类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, long expected) {
		long actualValue = 0L;
		try {
			actualValue = Long.parseLong(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, long expected) {
		long actualValue = 0L;
		try {
			actualValue = Long.parseLong(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言byte类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, byte expected) {
		byte actualValue = 0;
		try {
			actualValue = Byte.parseByte(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, byte expected) {
		byte actualValue = 0;
		try {
			actualValue = Byte.parseByte(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to number");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言boolean类型结果相等
	 * 
	 * @param expected
	 */
	public T assertEquals(String path, boolean expected) {
		boolean actualValue = false;
		try {
			actualValue = Boolean.parseBoolean(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.error("Failed to parse " + actual + " to number");
			Exceptions.checked(e);
		}
		Assert.assertEquals(actualValue, expected);
		return myself;
	}

	/**
	 * 断言boolean类型结果不等
	 * 
	 * @param expected
	 */
	public T assertNotEquals(String path, boolean expected) {
		boolean actualValue = false;
		try {
			actualValue = Boolean.parseBoolean(extractor.extract(path));
		} catch (NumberFormatException e) {
			logger.warn("Failed to parse " + actual + " to boolean");
			return myself;
		}
		Assert.assertNotEquals(actualValue, expected);
		return myself;
	}

	/**
	 * assert element count extracted by path equals to expected count
	 * 
	 * @param path
	 * @param expectedCount
	 */
	public T assertCount(String path, int expectedCount) {
		Assert.assertEquals(extractor.getCount(path), expectedCount);
		return myself;
	}

	/**
	 * assert element extracted by path exists
	 * 
	 * @param path
	 */
	public T assertExists(String path) {
		Assert.assertTrue(extractor.isExists(path));
		return myself;
	}

	/**
	 * assert element extracted by path NOT exists
	 * 
	 * @param path
	 */
	public T assertNotExists(String path) {
		Assert.assertFalse(extractor.isExists(path));
		return myself;
	}

	/**
	 * assert elements extracted by path contains expected elements
	 * 
	 * @param path
	 * @param expectedElements
	 */
	public T assertSomeContains(String path, String... expectedElements) {
		Assert.assertTrue(expectedElements != null && expectedElements.length > 0,
				"Expected elements must be specified!");
		List<String> extractedSome = extractor.extractSome(path);
		Collection<String> expected = Arrays.asList(expectedElements);
		Assert.assertTrue(extractedSome.containsAll(expected));
		return myself;
	}

	/**
	 * assert elements extracted by path NOT contains expected element
	 * 
	 * @param path
	 * @param notContainsexpectedElement
	 */
	public T assertSomeNotContains(String path, String notContainsexpectedElement) {
		List<String> extractedSome = extractor.extractSome(path);
		Assert.assertTrue(!extractedSome.contains(notContainsexpectedElement));
		return myself;
	}

	/**
	 * assert elements extracted by path has the same elements and order as
	 * expected
	 * 
	 * @param path
	 * @param expectedElements
	 */
	public T assertSomeEquals(String path, String... expectedElements) {

		Assert.assertTrue(expectedElements != null && expectedElements.length > 0,
				"Expected elements must be specified!");
		List<String> extractedSome = extractor.extractSome(path);
		Collection<String> expected = Arrays.asList(expectedElements);
		Assert.assertEquals(extractedSome, expected);
		return myself;
	}

	/**
	 * Asserts that a condition is true. If it isn't, an AssertionError is
	 * thrown.
	 * 
	 * @param condition
	 *            the condition to evaluate
	 */
	public T assertTrue(boolean condition) {
		Assert.assertTrue(condition);
		return myself;
	}

	/**
	 * Asserts that a condition is false. If it isn't, an AssertionError is
	 * thrown.
	 * 
	 * @param condition
	 *            the condition to evaluate
	 */
	public T assertFalse(boolean condition) {
		Assert.assertFalse(condition);
		return myself;
	}

	/**
	 * assert value extracted by path condition result is true
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertTrue(String path, ExtractCondition condition) {
		Assert.assertTrue(condition.eval(extractor.extract(path)));
		return myself;
	}

	/**
	 * assert value extracted by path condition result is false
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertFalse(String path, ExtractCondition condition) {
		Assert.assertFalse(condition.eval(extractor.extract(path)));
		return myself;
	}

	/**
	 * ` assert count extracted by path condition result is true
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertTrue(String path, CountCondition condition) {
		Assert.assertTrue(condition.eval(extractor.getCount(path)));
		return myself;
	}

	/**
	 * assert count extracted by path condition result is false
	 * 
	 * @param path
	 * @param expected
	 */
	public T assertFalse(String path, CountCondition condition) {
		Assert.assertFalse(condition.eval(extractor.getCount(path)));
		return myself;
	}
}
