package com.vip.qa.autov.core.extractor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.XmlUtils;

public class XPathExtractor extends AbstractExtractor {

	private static Logger logger = LoggerFactory.getLogger(XPathExtractor.class);

	protected String input;

	public XPathExtractor(String input) {
		this.input = input;
	}

	protected XPathExtractor() {
	}

	/**
	 * Extract a value from xml input by xpath
	 * 
	 * @param xpath
	 * @return extracted value
	 */
	@Override
	public String extract(String xpath) {

		if (StringUtils.isBlank(xpath)) {
			return input;
		}

		String result = null;
		try {
			result = XmlUtils.getNode(input, xpath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		logger.debug("Extracted value is: " + result);

		return result;
	}

	/**
	 * The count of elements extracted by xpath
	 * 
	 * @param xpath
	 * @return element count
	 */
	@Override
	public int getCount(String xpath) {
		int count = 0;
		try {
			count = XmlUtils.getNodes(input, xpath).size();
		} catch (Exception e) {
		}
		logger.debug("Actual Count is " + count);
		return count;

	}

	/**
	 * Extract elements by xpath
	 * 
	 * @param xpath
	 * @return elements
	 */
	@Override
	public List<String> extractSome(String xpath) {
		List<String> result = null;
		try {
			result = XmlUtils.getNodes(input, xpath);
			logger.debug("Extracted value is: " + result);
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		return result;
	}

}
