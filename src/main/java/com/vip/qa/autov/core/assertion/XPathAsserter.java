package com.vip.qa.autov.core.assertion;

import org.apache.commons.lang3.Validate;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.testng.Assert;

import com.vip.qa.autov.core.extractor.XPathExtractor;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.XmlUtils;

public class XPathAsserter extends AbstractPathAsserter<XPathAsserter> {

	public XPathAsserter(String actual) {
		super(actual, XPathAsserter.class);
		extractor = new XPathExtractor(actual);
	}

	/**
	 * 断言两个两个xml结构、数据和属性不一致，顺序可以不一致
	 * 
	 */
	public XPathAsserter assertMatchLenient(String expected) {
		assertMatch(actual, expected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性不一致，顺序可以不一致
	 * 
	 */
	public XPathAsserter assertNotMatchLenient(String expected) {
		assertNotMatch(actual, expected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 */
	public XPathAsserter assertMatchStrict(String expected) {
		assertMatch(actual, expected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性不一致，顺序要一致
	 * 
	 */
	public XPathAsserter assertNotMatchStrict(String expected) {
		assertNotMatch(actual, expected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要可不一致
	 * 
	 * @xpath 对预期及结果先通过xpath提取
	 */
	public XPathAsserter assertNotMatchLenientPartial(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertNotMatch(newActual, newExpected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要可不一致
	 * 
	 * @xpath 对预期及结果先通过xpath提取
	 */
	public XPathAsserter assertMatchLenientPartial(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertMatch(newActual, newExpected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对预期及结果先通过xpath提取
	 */
	public XPathAsserter assertNotMatchStrictPartial(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertNotMatch(newActual, newExpected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对预期及结果先通过xpath提取
	 */
	public XPathAsserter assertMatchStrictPartial(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertMatch(newActual, newExpected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对预期先通过xpath提取
	 */
	public XPathAsserter assertNotMatchStrictExtractExpected(String expected, String xpath) {
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertNotMatch(actual, newExpected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序可不一致
	 * 
	 * @xpath 对预期先通过xpath提取
	 */
	public XPathAsserter assertMatchLenientExtractExpected(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertMatch(newActual, newExpected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序可不一致
	 * 
	 * @xpath 对预期先通过xpath提取
	 */
	public XPathAsserter assertNotMatchLenientExtractExpected(String expected, String xpath) {
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertNotMatch(actual, newExpected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对预期先通过xpath提取
	 */
	public XPathAsserter assertMatchStrictExtractExpected(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		String newExpected = new XPathExtractor(expected).extract(xpath);
		assertMatch(newActual, newExpected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要可不一致
	 * 
	 * @xpath 对结果先通过xpath提取
	 */
	public XPathAsserter assertNotMatchLenientExtractActual(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		assertMatch(newActual, expected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要可不一致
	 * 
	 * @xpath 对结果先通过xpath提取
	 */
	public XPathAsserter assertMatchLenientExtractActual(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		assertNotMatch(newActual, expected, true);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对结果先通过xpath提取
	 */
	public XPathAsserter assertMatchStrictExtractActual(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		assertMatch(newActual, expected, false);
		return this;
	}

	/**
	 * 断言两个两个xml结构、数据和属性一致，顺序要一致
	 * 
	 * @xpath 对结果先通过xpath提取
	 */
	public XPathAsserter assertNotMatchStrictExtractActual(String expected, String xpath) {
		String newActual = extractor.extract(xpath);
		assertNotMatch(newActual, expected, false);
		return this;
	}

	/**
	 * 断言两个xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，actual和expected的字段一样)
	 * 
	 * @param expected
	 * @param xPath
	 * @param notMatchedXPaths
	 * @return
	 */
	public XPathAsserter assertNotMatchFieldsStrict(String expected, String... notMatchedXPaths) {
		assertNotMatchFieldsEquals(expected, actual, false, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言两个xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致)
	 * 
	 * @param expected
	 * @param xPath
	 * @param notMatchedXPaths
	 * @return
	 */
	public XPathAsserter assertNotMatchFieldsLenient(String expected, String notMatchedXPaths) {
		assertNotMatchFieldsEquals(expected, actual, true, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言两个xml经过xpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致， actual和expected的字段一样)
	 * 
	 * @param expected
	 * @param jsonPath
	 * @param notMatchedXPaths
	 * @return
	 */
	public XPathAsserter assertNotMatchFieldsStrictPartial(String expected, String xPath, String notMatchedXPaths) {
		String extractedActual = ((XPathExtractor) extractor).extract(xPath);
		String extractedExpected = new XPathExtractor(expected).extract(xPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, false, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言两个xml经过xpath提后去对比的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致)
	 * 
	 * @param expected
	 * @param xPath
	 * @param notMatchedXPaths
	 * @return
	 */
	public XPathAsserter assertNotMatchFieldsLenientPartial(String expected, String xPath, String notMatchedXPaths) {
		String extractedActual = ((XPathExtractor) extractor).extract(xPath);
		String extractedExpected = new XPathExtractor(expected).extract(xPath);
		assertNotMatchFieldsEquals(extractedExpected, extractedActual, true, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言实际结果经xpath提取后和预期xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致)
	 */
	public XPathAsserter assertNotMatchFieldsStrictExtractActual(String expected, String xPath,
			String... notMatchedXPaths) {
		String extractedActual = ((XPathExtractor) extractor).extract(xPath);
		assertNotMatchFieldsEquals(expected, extractedActual, false, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言实际结果经xpath提取后和预期xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致)
	 */
	public XPathAsserter assertNotMatchFieldsLenientExtractActual(String expected, String xPath,
			String... notMatchedXPaths) {
		String extractedActual = ((XPathExtractor) extractor).extract(xPath);
		assertNotMatchFieldsEquals(expected, extractedActual, true, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言实际结果经xpath提取后和预期xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序一致，
	 * actual和expected字段一致)
	 */
	public XPathAsserter assertNotMatchFieldsStrictExtractExpected(String expected, String xPath,
			String... notMatchedXPaths) {
		String extractedExpected = new XPathExtractor(expected).extract(xPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, false, notMatchedXPaths);
		return this;
	}

	/**
	 * 断言预期结果经xpath提取后和实际xml对比后的不匹配项，包括有多的，少的或者值不匹配的field(顺序可不一致)
	 */
	public XPathAsserter assertNotMatchFieldsLenientExtractExpected(String expected, String xPath,
			String... notMatchedXPaths) {
		String extractedExpected = new XPathExtractor(expected).extract(xPath);
		assertNotMatchFieldsEquals(extractedExpected, actual, true, notMatchedXPaths);
		return this;
	}

	private void assertMatch(String expected, String actual, boolean isLenient) {
		try {
			Diff myDiff = new Diff(actual, expected);
			myDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
			StringBuffer sb = new StringBuffer();
			myDiff.appendMessage(sb);
			Assert.assertTrue(isLenient ? myDiff.similar() : myDiff.identical(), sb.toString());
		} catch (Exception e) {
			Exceptions.checked(e);
		}
	}

	private void assertNotMatch(String expected, String actual, boolean isLenient) {
		try {
			Diff myDiff = new Diff(actual, expected);
			myDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
			StringBuffer sb = new StringBuffer();
			myDiff.appendMessage(sb);
			Assert.assertFalse(isLenient ? myDiff.similar() : myDiff.identical(), sb.toString());
		} catch (Exception e) {
			Exceptions.checked(e);
		}
	}

	private void assertNotMatchFieldsEquals(String expected, String actual, boolean isLenient, String... notMatchXpaths) {
		Validate.notEmpty(notMatchXpaths);
		try {
			for (String unmatched : notMatchXpaths) {
				expected = XmlUtils.deleteNode(expected, unmatched);
				actual = XmlUtils.deleteNode(actual, unmatched);
			}
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		assertMatch(expected, actual, isLenient);
	}
}
