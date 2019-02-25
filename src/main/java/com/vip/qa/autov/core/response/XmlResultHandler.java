package com.vip.qa.autov.core.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.XmlUtils;

/**
 * 用于对响应的xml进行修改
 * 
 * @author alex.ma
 *
 * @param <T>
 */
public class XmlResultHandler<T> {

	private AbstractResponser<T> responser;

	private static Logger logger = LoggerFactory.getLogger(XmlResultHandler.class);

	public XmlResultHandler(AbstractResponser<T> responser) {
		this.responser = responser;
	}

	/**
	 * 删除xml节点
	 * 
	 * @param xpath
	 * @return
	 * @throws Exception
	 */
	public XmlResultHandler<T> deleteNode(String xpath) {
		String result = responser.getResult();
		if (result != null) {
			try {
				result = XmlUtils.deleteNode(result, xpath);
			} catch (Exception e) {
				Exceptions.checked(e);
			}
			responser.setResult(result);
			logger.info("Xml Delete Result: " + result);
		}

		return this;
	}

	/**
	 * 删除多个xml节点
	 * 
	 * @param xpath
	 * @return
	 * @throws Exception
	 */
	public XmlResultHandler<T> deleteNodes(String xpath) {
		String result = responser.getResult();
		if (result != null) {
			try {
				result = XmlUtils.deleteNodes(result, xpath);
			} catch (Exception e) {
				Exceptions.checked(e);
			}
			responser.setResult(result);
			logger.info("Xml Delete Result: " + result);
		}
		return this;
	}

	/**
	 * update node value
	 * 
	 * @param xpath
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public XmlResultHandler<T> setNode(String xpath, String value) {
		String result = responser.getResult();
		if (result != null) {
			try {
				result = XmlUtils.setNode(result, xpath, value);
			} catch (Exception e) {
				Exceptions.checked(e);
			}
			responser.setResult(result);
			logger.info("Xml Set Result: " + result);
		}
		return this;
	}

	/**
	 * add node
	 * 
	 * @param xpath
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public XmlResultHandler<T> addNode(String xpath, String key, String value) {
		String result = responser.getResult();
		if (result != null) {
			try {
				result = XmlUtils.addNode(result, xpath, key, value);
			} catch (Exception e) {
				Exceptions.checked(e);
			}
			responser.setResult(result);
			logger.info("Xml Add Result: " + result);
		}

		return this;
	}
}
