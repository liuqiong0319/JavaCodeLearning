package com.vip.qa.autov.core.listener;

import java.lang.reflect.Method;
import java.util.EventListener;

public interface TestEventListener extends EventListener {

	public void onMethodFinish(Method method);

	public void onMethodStart(Method method);

	public static class TestMethodEventListenerAdapter implements TestEventListener {

		@Override
		public void onMethodFinish(Method method) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void onMethodStart(Method method) {
			// TODO 自动生成的方法存根

		}

	}
}
