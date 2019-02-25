package com.vip.qa.autov.core.assertion;

public class AsserterBuilder {

	private XPathAsserter xPathAsserter;

	private JsonPathAsserter jsonPathAsserter;

	private RegexAsserter regexAsserter;

	private Json2XPathAsserter json2XPathAsserter;

	private Xml2JsonPathAsserter xml2JsonPathAsserter;

	private NormalAsserter normalAsserter;

	private String result;

	public AsserterBuilder(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/*
	 * provides xpath assertions
	 */
	public XPathAsserter xpath() {
		if (xPathAsserter == null) {
			xPathAsserter = new XPathAsserter(result);
		}
		return xPathAsserter;
	}

	/*
	 * provides jsonpath assertions
	 */
	public JsonPathAsserter jsonPath() {
		if (jsonPathAsserter == null) {
			jsonPathAsserter = new JsonPathAsserter(result);
		}
		return jsonPathAsserter;
	}

	/*
	 * provides regex assertions
	 */
	public RegexAsserter regex() {
		if (regexAsserter == null) {
			regexAsserter = new RegexAsserter(result);
		}
		return regexAsserter;
	}

	/*
	 * provides json2xpath assertions
	 */
	public Json2XPathAsserter json2XPath() {
		if (json2XPathAsserter == null) {
			json2XPathAsserter = new Json2XPathAsserter(result);
		}
		return json2XPathAsserter;
	}

	/*
	 * provides xml2JsonPath assertions
	 */
	public Xml2JsonPathAsserter xml2JsonPath() {
		if (xml2JsonPathAsserter == null) {
			xml2JsonPathAsserter = new Xml2JsonPathAsserter(result);
		}
		return xml2JsonPathAsserter;
	}

	/*
	 * provides normal assertions
	 */
	public NormalAsserter normal() {
		if (normalAsserter == null) {
			normalAsserter = new NormalAsserter(result);
		}
		return normalAsserter;
	}
}
