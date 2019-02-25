package com.vip.qa.autov.core.api.caching;

public interface ApiCacheProvider {

	/**
	 * 发送缓存数据到Redis
	 * 
	 * @param model
	 */
	void sendData(ApiCacheModel model);

	/**
	 * 构造缓存数据的对象
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	<T, V> ApiCacheModel buildCacheModel(T req, V resp);

	/**
	 * 是否连接上Redis
	 * 
	 * @return
	 */
	boolean isActive();

}
