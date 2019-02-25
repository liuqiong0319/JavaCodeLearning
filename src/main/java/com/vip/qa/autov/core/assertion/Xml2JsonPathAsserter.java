package com.vip.qa.autov.core.assertion;

import com.vip.qa.autov.core.extractor.Xml2JsonPathExtractor;

/**
 * @deprecation 建议使用@JsonPathAsserter
 * @author alex.ma
 *
 */
@Deprecated
public class Xml2JsonPathAsserter extends JsonPathAsserter {

	public Xml2JsonPathAsserter(String actual) {
		super(actual);
		extractor = new Xml2JsonPathExtractor(actual);
	}

}
