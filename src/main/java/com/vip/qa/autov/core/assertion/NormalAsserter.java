package com.vip.qa.autov.core.assertion;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

public class NormalAsserter {

	private String actual;

	public NormalAsserter(String actual) {
		this.actual = actual;
	}

	/**
	 * 断言结果和预期字符串相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(String expected) {
		Assert.assertEquals(actual, expected);
		return this;
	}

	/**
	 * 断言结果和预期字符串不等
	 * 
	 * @param expected
	 */
	public void assertNotEquals(String expected) {
		Assert.assertNotEquals(actual, expected);
	}

	/**
	 * 断言结果包含预期字符串
	 * 
	 * @param expected
	 */
	public NormalAsserter assertContains(String expected) {
		Assert.assertTrue(actual.contains(expected));
		return this;
	}

	/**
	 * 断言结果不包含预期字符串
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotContains(String expected) {
		Assert.assertFalse(actual.contains(expected));
		return this;
	}

	/**
	 * 断言结果为Blank, 参考StringUtils.isBlank
	 * 
	 * @param expected
	 */
	public NormalAsserter assertBlank() {
		Assert.assertTrue(StringUtils.isBlank(actual));
		return this;
	}

	/**
	 * 断言结果为非Blank, 参考StringUtils.isBlank
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotBlank() {
		Assert.assertTrue(StringUtils.isNotBlank(actual));
		return this;
	}

	/**
	 * 断言结果为Empty, 参考StringUtils.isEmpty
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEmpty() {
		Assert.assertTrue(StringUtils.isEmpty(actual));
		return this;
	}

	/**
	 * 断言结果非Empty, 参考StringUtils.isEmpty
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEmpty() {
		Assert.assertTrue(StringUtils.isEmpty(actual));
		return this;
	}

	/**
	 * 断言结果为非Null
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotNull() {
		Assert.assertNotNull(actual);
		return this;
	}

	/**
	 * 断言结果为Null
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNull() {
		Assert.assertNull(actual);
		return this;
	}

	/**
	 * Asserts that a condition is true. If it isn't, an AssertionError is
	 * thrown.
	 * 
	 * @param condition
	 *            the condition to evaluate
	 */
	public NormalAsserter assertTrue(boolean condition) {
		Assert.assertTrue(condition);
		return this;
	}

	/**
	 * Asserts that a condition is false. If it isn't, an AssertionError is
	 * thrown.
	 * 
	 * @param condition
	 *            the condition to evaluate
	 */
	public NormalAsserter assertFalse(boolean condition) {
		Assert.assertFalse(condition);
		return this;
	}

	/**
	 * 断言整形结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(int expected) {
		int actualValue = Integer.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言整形结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(int expected) {
		int actualValue = Integer.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言double类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(double expected) {
		double actualValue = Double.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言double类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(double expected) {
		double actualValue = Double.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言float类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(float expected) {
		float actualValue = Float.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言float类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(float expected) {
		float actualValue = Float.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言short类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(short expected) {
		short actualValue = Short.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言short类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(short expected) {
		short actualValue = Short.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(long expected) {
		long actualValue = Long.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(long expected) {
		long actualValue = Long.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言byte类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(byte expected) {
		byte actualValue = Byte.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言long类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(byte expected) {
		byte actualValue = Byte.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言boolean类型结果相等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertEquals(boolean expected) {
		boolean actualValue = Boolean.valueOf(actual);
		Assert.assertEquals(actualValue, expected);
		return this;
	}

	/**
	 * 断言boolean类型结果不等
	 * 
	 * @param expected
	 */
	public NormalAsserter assertNotEquals(boolean expected) {
		boolean actualValue = Boolean.valueOf(actual);
		Assert.assertNotEquals(actualValue, expected);
		return this;
	}

}
