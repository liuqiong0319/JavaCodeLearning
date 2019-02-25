package com.vip.qa.autov.core.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.vip.qa.autov.core.End2End;
import com.vip.qa.autov.core.params.ParamMap;
import com.vip.qa.autov.core.testcase.ParamHolder;
import com.vip.qa.autov.core.utils.Exceptions;

public class MethodInvokeListener implements ITestListener {

	protected static Logger logger = LoggerFactory.getLogger(MethodInvokeListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO 自动生成的方法存根
		removeDuplicatedSkipResult(result);
		handleTestParams(result);
	}

	/**
	 * 测试出错记录stacktrace日志
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		// Throwable exception = result.getThrowable();
		// if (exception != null) {
		// logger.error(Exceptions.getStackTraceAsString(exception));
		// }
		removeDuplicatedSkipResult(result);
		handleTestParams(result);
	}

	@SuppressWarnings("unchecked")
	private void handleTestParams(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		if (method.isAnnotationPresent(End2End.class) || method.getDeclaringClass().isAnnotationPresent(End2End.class)) {
			Object[] parameters = result.getParameters();
			if (parameters != null && parameters.length > 0) {
				List<ParamMap<String, ?>> paramContext = new ArrayList<>();
				for (Object parameter : parameters) {
					if (parameter instanceof ParamMap) {
						paramContext.add((ParamMap) parameter);
					}
				}
				ParamHolder.setParams(paramContext.toArray(new ParamMap[] {}));
				// Reporter.log("Test Parameters: " +
				// JsonMapper.toJson(parameters));
			}
		}

	}

	/**
	 * 测试skip记录stacktrace日志
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		removeDuplicatedSkipResult(result);
		Throwable exception = result.getThrowable();
		if (exception != null) {
			logger.error(Exceptions.getStackTraceAsString(exception));
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO 自动生成的方法存根
	}

	private void removeDuplicatedSkipResult(ITestResult result) {
		IResultMap skipResultMap = result.getTestContext().getSkippedTests();
		skipResultMap.removeResult(result.getMethod());
	}

}
