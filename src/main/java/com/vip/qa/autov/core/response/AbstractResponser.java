package com.vip.qa.autov.core.response;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.assertion.AsserterBuilder;
import com.vip.qa.autov.core.extractor.ExtractorBuilder;
import com.vip.qa.autov.core.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResponser<T> implements Responser<T> {

	protected T response;

	protected JsonSerializeConfig jsonSerializeConfig = JsonSerializeConfig.builder().build();

	private JsonResultBuilder<T> jsonResultBuilder;

	protected static Logger logger = LoggerFactory.getLogger(AbstractResponser.class);

	protected String result;

	public AbstractResponser() {
	}

	public AbstractResponser(T response) {
		this.response = response;
	}

	@Override
	public String getResult() {
		serializeResult(response);
		return result;
	}

	@Override
	public AsserterBuilder asserter() {
		return new AsserterBuilder(getResult());
	}

	@Override
	public ExtractorBuilder extractor() {
		return new ExtractorBuilder(getResult());
	}

	@Override
	public T getResponse() {
		return response;
	}

	/**
	 * @deprecated Use JsonSerializeConfig, e.g.
	 *             JsonSerializeConfig.builder().serializeNulls().build()
	 * @return
	 */
	@Deprecated
	public JsonResultBuilder<T> jsonResultBuilder() {
		if (jsonResultBuilder == null) {
			jsonResultBuilder = new JsonResultBuilder<T>(this);
		}
		return jsonResultBuilder;
	}

	/**
	 * edit json string result
	 * 
	 * @return
	 */
	public JsonResultHandler<T> jsonResultHandler() {
		return new JsonResultHandler<T>(this);
	}

	/**
	 * edit xml string result
	 * 
	 * @return
	 */
	public XmlResultHandler<T> xmlResultHandler() {
		return new XmlResultHandler<T>(this);
	}

	/**
	 * set json serialize config for json serialization, use default config if
	 * not set
	 * 
	 * @param jsonSerializeConfig
	 */
	public void setJsonSerializeConfig(JsonSerializeConfig jsonSerializeConfig) {
		this.jsonSerializeConfig = jsonSerializeConfig;
	}

	protected void setResult(String result) {
		this.result = result;
		logger.debug("[RESULT]: " + File.separator + result);
	}

	/**
	 * 把response序列化为字符串并存在result变量
	 * 
	 * @param response
	 */
	protected void serializeResult(Object response) {
		if (response != null && result == null) {
			if (shouldSerialize(response)) {
				List<SerializerFeature> features = new ArrayList<>();
				features.add(SerializerFeature.WriteDateUseDateFormat);
				if (jsonSerializeConfig.isSerializeNulls()) {
					features.add(SerializerFeature.WriteMapNullValue);
				}
				if (AutoVConsts.IS_JSON_PRETTY) {
					features.add(SerializerFeature.PrettyFormat);
				}
				features.add(SerializerFeature.WriteBigDecimalAsPlain);
				this.result = JsonMapper.toJson(response, features.toArray(new SerializerFeature[] {}));
			} else {
				String result = response.toString();
				this.result = result;
			}
			setResult(result);
		}
	}

	JsonSerializeConfig getJsonSerializeConfig() {
		return jsonSerializeConfig;
	}

	private boolean shouldSerialize(Object response) {
		return !(response instanceof String) && !(response instanceof Boolean) && !(response instanceof Number);
	}

}
