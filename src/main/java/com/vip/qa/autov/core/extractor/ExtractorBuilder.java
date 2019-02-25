package com.vip.qa.autov.core.extractor;


public class ExtractorBuilder {

	private XPathExtractor xPathExtractor;

	private JsonPathExtractor jsonPathExtractor;

	private RegexExtractor regexExtractor;

	private Json2XPathExtractor json2XPathExtractor;

	private Xml2JsonPathExtractor xml2JsonPathExtractor;

	private String result;
	
	public ExtractorBuilder(String result) {
		this.result = result;
	}

	public XPathExtractor xpath() {
		if (xPathExtractor == null) {
			xPathExtractor = new XPathExtractor(result);
		}
		return xPathExtractor;
	}

	public JsonPathExtractor jsonPath() {
		if (jsonPathExtractor == null) {
			jsonPathExtractor = new JsonPathExtractor(result);
		}
		return jsonPathExtractor;
	}

	public RegexExtractor regex() {
		if (regexExtractor == null) {
			regexExtractor = new RegexExtractor(result);
		}
		return regexExtractor;
	}

	public Json2XPathExtractor json2XPath() {
		if (json2XPathExtractor == null) {
			json2XPathExtractor = new Json2XPathExtractor(result);
		}
		return json2XPathExtractor;
	}

	public Xml2JsonPathExtractor xml2JsonPath() {
		if (xml2JsonPathExtractor == null) {
			xml2JsonPathExtractor = new Xml2JsonPathExtractor(result);
		}
		return xml2JsonPathExtractor;
	}

}
