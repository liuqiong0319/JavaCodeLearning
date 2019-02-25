package com.vip.qa.autov.core.utils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {
	
	private static ThreadLocal<SAXReader> readerLocal=new ThreadLocal<>();

	public static String json2XML(String json) {
		JSON jobj = null;
		if (json.startsWith("[") && json.endsWith("]")) {
			jobj = JSONArray.fromObject(json);
		} else {
			jobj = JSONObject.fromObject(json);
		}
		XMLSerializer serializer = new XMLSerializer();
		serializer.setTypeHintsEnabled(false);
		serializer.setArrayName("_A");
		serializer.setElementName("_E");
		serializer.setObjectName("_O");
		serializer.setForceTopLevelObject(false);
		String xml = serializer.write(jobj, "UTF-8");
		return xml;
	}

	public static String xml2Json(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.read(xml).toString();
	}
	
	public static boolean isXml(String input) {
		try {
			DocumentBuilderFactory foctory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = foctory.newDocumentBuilder();
			builder.parse(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static Document getDocument(InputStream is) throws Exception {
		Document doc = null;
		try {
			doc = getReader().read(is, "UTF-8");
		} catch (Exception e) {
			doc = getReader().read(is, "GBK");
		}
		return doc;
	}

	public static Document getDocument(String xml) throws Exception {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		} catch (Exception e) {
			is = new ByteArrayInputStream(xml.getBytes("GBK"));
		}
		return getDocument(is);
	}

	public static Element getSubElement(Document doc, String xpath) {
		Element el = (Element) doc.selectSingleNode(xpath);
		return el;
	}

	/**
	 * 删除xml节点
	 * 
	 * @param xml
	 * @param xpath
	 * @return
	 * @throws Exception
	 */
	public static String deleteNode(String xml, String xpath) throws Exception {
		Document source = getDocument(xml);
		Element node = (Element) source.selectSingleNode(xpath);
		node.detach();
		return source.asXML();
	}

	/**
	 * 删除多个xml节点
	 * 
	 * @param xml
	 * @param xpath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String deleteNodes(String xml, String xpath) throws Exception {
		Document source = getDocument(xml);
		List nodes = source.selectNodes(xpath);
		for (Object node : nodes) {
			((Element) node).detach();
		}
		return source.asXML();
	}

	/**
	 * update node value
	 * 
	 * @param xml
	 * @param xpath
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String setNode(String xml, String xpath, String value) throws Exception {
		Document source = getDocument(xml);
		Object node = source.selectObject(xpath);
		Element nodeElement = ((Element) node);
		nodeElement.setText(value);
		return source.asXML();
	}

	/**
	 * add node
	 * 
	 * @param xml
	 * @param xpath
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String addNode(String xml, String xpath, String key, String value) throws Exception {
		Document source = getDocument(xml);
		Object node = source.selectObject(xpath);
		if (node instanceof Element) {
			Element nodeElement = ((Element) node);
			Element newElement = nodeElement.addElement(key);
			newElement.setText(value);
		}

		return source.asXML();
	}

	@SuppressWarnings("unchecked")
	public static String getNode(String xml, String xpath) throws Exception {
		Document source = getDocument(xml);
		Object node = source.selectObject(xpath);
		String result = null;
		if (node instanceof Element) {
			Element nodeElement = ((Element) node);
			if (nodeElement.isTextOnly()) {
				result = nodeElement.getText();
			} else {
				result = nodeElement.asXML();
			}
		} else {
			List<Element> elements = (List<Element>) node;
			if (elements.size() > 0) {
				Element nodeElement = elements.get(0);
				if (nodeElement.isTextOnly()) {
					result = nodeElement.getText();
				} else {
					result = nodeElement.asXML();
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getNodes(String xml, String xpath) throws Exception {
		Document source = getDocument(xml);
		Object node = source.selectObject(xpath);
		List<String> resultList = new ArrayList<>();
		if (node instanceof Element) {
			Element nodeElement = ((Element) node);
			String result = null;
			if (nodeElement.isTextOnly()) {
				result = nodeElement.getText();
			} else {
				result = nodeElement.asXML();
			}
			resultList.add(result);
		} else {
			List<Element> elements = (List<Element>) node;
			String result = null;
			for (Element element : elements) {
				if (element.isTextOnly()) {
					result = element.getText();
				} else {
					result = element.asXML();
				}
				resultList.add(result);
			}
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public static List<Node> getSubElements(Document doc, String xpath) {
		List<Node> el = doc.selectNodes(xpath);
		return el;
	}
	
	private static SAXReader getReader(){
		SAXReader reader=readerLocal.get();
		if(reader==null){
			reader=new SAXReader();
			readerLocal.set(reader);
		}
		return reader;
	}
}
