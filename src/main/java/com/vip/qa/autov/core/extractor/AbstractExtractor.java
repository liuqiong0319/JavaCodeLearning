package com.vip.qa.autov.core.extractor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public abstract class AbstractExtractor implements Extractor {

	public double extractDouble(String expression) {
		String value = extract(expression);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	public int extractInt(String expression) {
		String value = extract(expression);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	public long extractLong(String expression) {
		String value = extract(expression);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Long.valueOf(value);
	}

	public boolean extractBoolean(String expression) {
		String value = extract(expression);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	public float extractFloat(String expression) {
		String value = extract(expression);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Float.valueOf(value);
	}

	/**
	 * Extracted random element by expression
	 * 
	 * @param expression
	 * @return exists or not
	 */
	@Override
	public String extractRandom(String expression) {
		List<String> extractList = extractSome(expression);
		int size = extractList.size();
		if (size > 0) {
			return extractList.get(new Random().nextInt(size));
		}
		return null;
	}

	/**
	 * Element exists or not by expression
	 * 
	 * @param expression
	 * @return exists or not
	 */
	@Override
	public boolean isExists(String expression) {
		try {
			return extract(expression) != null || getCount(expression) > 0;
		} catch (Exception e) {
			return false;
		}
	}

}
