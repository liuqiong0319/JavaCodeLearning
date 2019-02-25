package com.vip.qa.autov.core.response;

/**
 * 响应json序列化的配置
 * 
 * @author alex.ma
 *
 */
public class JsonSerializeConfig {

	private boolean isSerializeNulls = false;

	private boolean isLongToString = false;

	private boolean isDecimalToString = false;

	public boolean isSerializeNulls() {
		return isSerializeNulls;
	}

	@Deprecated
	public boolean isLongToString() {
		return isLongToString;
	}

	@Deprecated
	public boolean isDecimalToString() {
		return isDecimalToString;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private boolean isSerializeNulls = false;

		private boolean isLongToString = false;

		private boolean isDecimalToString = false;

		public Builder serializeNulls() {
			this.isSerializeNulls = true;
			return this;
		}

		@Deprecated
		public Builder longToString() {
			this.isLongToString = true;
			return this;
		}

		@Deprecated
		public Builder decimalToString() {
			this.isDecimalToString = true;
			return this;
		}

		public JsonSerializeConfig build() {
			JsonSerializeConfig config = new JsonSerializeConfig();
			config.isDecimalToString = isDecimalToString;
			config.isLongToString = isLongToString;
			config.isSerializeNulls = isSerializeNulls;
			return config;
		}
	}

}
