package com.vip.qa.autov.core.api.caching;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;

/**
 * 所有成功请求放本地缓存，定时把本地缓存刷到redis
 * 
 * @author alex.ma
 *
 */
public class ApiCacheSender implements DisposableBean {

	private Map<String, ApiCacheModel> cache = new ConcurrentHashMap<String, ApiCacheModel>();

	private int sendCachePeriod = 15;

	private ApiCacheProvider provider;

	public ApiCacheSender(ApiCacheProvider provider, int sendCachePeriod) {
		this.provider = provider;
		this.sendCachePeriod = sendCachePeriod;
	}

	public ApiCacheSender(ApiCacheProvider provider) {
		this.provider = provider;
	}

	public <T, V> void putIfAbsent(T req, V resp) {
		if (provider.isActive() && resp != null) {
			ApiCacheModel model = provider.buildCacheModel(req, resp);
			if (model == null) {
				return;
			}
			String key = model.getApiKey();
			if (cache.get(key) == null) {
				cache.put(key, model);
			}
		}
	}

	@PostConstruct
	//@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行
	private void startSendRemoteTimer() {

//		ScheduledExecutorService executor = ThreadPoolExecutor.newSingleThreadScheduledExecutor();
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(new SendApiDataThread(), sendCachePeriod, sendCachePeriod, TimeUnit.SECONDS);
	}

	void sendRemoteApi() {
		Iterator<Entry<String, ApiCacheModel>> iter = cache.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, ApiCacheModel> entry = iter.next();
			provider.sendData(entry.getValue());
			iter.remove();
		}
	}

	@Override
	public void destroy() throws Exception {
		sendRemoteApi();
	}

	class SendApiDataThread implements Runnable {

		@Override
		public void run() {
			sendRemoteApi();
		}

	}

}
