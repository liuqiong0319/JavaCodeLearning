package com.vip.qa.autov.core.listener;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.vip.qa.autov.core.End2End;

/**
 * 主要是跑e2e的时候某个用例挂了不要继续往下跑
 * 
 * @author alex.ma
 *
 */
public class End2EndMethodInvokeListener implements IInvokedMethodListener {

	private ThreadLocal<Boolean> failureFlag = new ThreadLocal<>();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// 第一次跑就把状态清掉，避免如果是新循环也当做是出错把后面执行终止了
		if (End2EndTestStateHolder.isFirstTest()) {
			failureFlag.remove();
			End2EndTestStateHolder.setTestStarted();
			return;
		}
		if (failureFlag.get() != null) {
			throw new SkipException("Skipped due to test failure");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (ITestResult.SKIP != testResult.getStatus() && (!testResult.isSuccess() && failureFlag.get() == null)) {
			if (isEnd2End(method.getTestMethod().getConstructorOrMethod().getMethod())) {
				failureFlag.set(true);
				testResult.getTestContext().getSuite().getSuiteState().failed();
			}
		}
	}

	private boolean isEnd2End(Method method) {
		return method.getDeclaringClass().isAnnotationPresent(End2End.class)
				|| method.isAnnotationPresent(End2End.class);
	}
}