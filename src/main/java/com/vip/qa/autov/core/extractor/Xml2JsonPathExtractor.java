package com.vip.qa.autov.core.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.qa.autov.core.utils.XmlUtils;

/**
 * @deprecation 建议使用@XPathExtractor
 * @author alex.ma
 *
 */
@Deprecated
public class Xml2JsonPathExtractor extends JsonPathExtractor {

	private static Logger logger = LoggerFactory.getLogger(Xml2JsonPathExtractor.class);

	public Xml2JsonPathExtractor(String input) {
		super.input = XmlUtils.xml2Json(input);
	}

	@Override
	public String extract(String jsonpath) {
		logger.debug("XML converted to Json:" + input);

		String result = super.extract(jsonpath);
		return result;
	}

	@Override
	public int getCount(String jsonpath) {
		logger.debug("XML converted to Json:" + input);
		return super.getCount(jsonpath);
	}
}
