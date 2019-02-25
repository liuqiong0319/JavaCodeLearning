package com.vip.qa.autov.core.assertion;

import com.vip.qa.autov.core.extractor.Json2XPathExtractor;

/**
 * @deprecation 建议使用@XPathAsserter
 * @author alex.ma
 *
 */
@Deprecated
public class Json2XPathAsserter extends XPathAsserter {

	public Json2XPathAsserter(String actual) {
		super(actual);
		extractor = new Json2XPathExtractor(actual);
	}

}
