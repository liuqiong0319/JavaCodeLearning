package com.vip.qa.autov.core.extractor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.vip.qa.autov.core.utils.Exceptions;

public class RegexExtractor extends AbstractExtractor {

	private static Logger logger = LoggerFactory.getLogger(RegexExtractor.class);

	private String input;

	public RegexExtractor(String input) {
		this.input = input;
	}

	/**
	 * Extract a value from xml input by regex
	 * 
	 * @param regex
	 * @return extracted value
	 */
	@Override
	public String extract(String regex) {

		Pattern pattern = null;
		String result = null;
		try {
			pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				result = matcher.group(1);
			}
			logger.debug("Extracted value is: " + result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * The count of elements extracted by regex
	 * 
	 * @param regex
	 * @return element count
	 */
	@Override
	public int getCount(String regex) {
		Pattern pattern = null;
		try {
			pattern = Pattern.compile(regex);
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		int count = 0;
		try {
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				count++;
			}
		} catch (Exception e) {
		}
		logger.debug("Actual Count is " + count);
		return count;

	}

	/**
	 * Extract elements by regex
	 * 
	 * @param xml
	 *            input
	 * @return elements
	 */
	@Override
	public List<String> extractSome(String regex) {
		Pattern pattern = null;
		try {
			pattern = Pattern.compile(regex);
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		List<String> matchFound = Lists.newArrayList();
		try {
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				matchFound.add(matcher.group(1));
			}
			logger.debug("Extracted value is: " + matchFound.toString());
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		return matchFound;
	}
}
