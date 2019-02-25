package com.vip.qa.autov.core.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.qa.autov.core.utils.XmlUtils;

/**
 * @deprecation 建议使用@JsonPathExtractor
 * @author alex.ma
 *
 */
@Deprecated
public class Json2XPathExtractor extends XPathExtractor {

	private static Logger logger = LoggerFactory.getLogger(Json2XPathExtractor.class);

	public Json2XPathExtractor(String input) {
		super.input = XmlUtils.json2XML(input);
	}

	@Override
	public String extract(String xpath) {
		logger.debug("Json convert to XMl:" + input);
		String result = super.extract(xpath);
		return result;
	}

	@Override
	public int getCount(String xpath) {
		logger.debug("Json convert to XMl:" + input);
		return super.getCount(xpath);
	}
}
