package com.vip.qa.autov.core.response;

@Deprecated
public class JsonResultBuilder<T> {

	private boolean isSerializeNulls = false;

	private boolean isLongToString = false;

	private boolean isDecimalToString = false;

	private AbstractResponser<T> responser;

	JsonResultBuilder(AbstractResponser<T> responser) {
		this.responser = responser;
	}

	public JsonResultBuilder<T> serializeNulls() {
		this.isSerializeNulls = true;
		return this;
	}

	public JsonResultBuilder<T> longToString() {
		this.isLongToString = true;
		return this;
	}

	public JsonResultBuilder<T> decimalToString() {
		this.isDecimalToString = true;
		return this;
	}

	/**
	 * should be invoked only once for each responser instance and before other
	 * method of the responser is invoked
	 */
	public void build() {
		JsonSerializeConfig.Builder builder = JsonSerializeConfig.builder();

		if (isSerializeNulls) {
			builder.serializeNulls();
		}
		if (isLongToString) {
			builder.longToString();
		}
		if (isDecimalToString) {
			builder.decimalToString();
		}
		JsonSerializeConfig config = builder.build();
		responser.setJsonSerializeConfig(config);
	}
}
