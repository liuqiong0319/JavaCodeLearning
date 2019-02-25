package com.vip.qa.autov.core.assertion;

import com.vip.qa.autov.core.extractor.RegexExtractor;

public class RegexAsserter extends AbstractPathAsserter<RegexAsserter> {

	public RegexAsserter(String actual) {
		super(actual, RegexAsserter.class);
		extractor = new RegexExtractor(actual);
	}

}
