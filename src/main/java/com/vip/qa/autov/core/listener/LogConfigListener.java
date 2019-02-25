package com.vip.qa.autov.core.listener;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.context.TestMethodHolder;
import com.vip.qa.autov.core.params.ParamMap;

/**
 * 用于对接VTP
 * 
 * @author alex.ma
 *
 */
public class LogConfigListener implements IInvokedMethodListener {

	protected static Logger logger = LoggerFactory.getLogger(LogConfigListener.class);

	private static final String TABLE_LOG_SEPERATOR = "#";

	private static final String TABLE_FILE_SEPERATOR = "@";

	@SuppressWarnings("unchecked")
	@Override
	public void beforeInvocation(IInvokedMethod im, ITestResult tr) {
		ITestNGMethod method = im.getTestMethod();

		if (method.isBeforeSuiteConfiguration() || method.isAfterSuiteConfiguration()) {
			return;
		}

		Object[] objs = tr.getParameters();
		String cl = method.getTestClass().getName();

		String name = method.getMethodName();

		boolean isChangeLogKey = false;

		if (method.isBeforeMethodConfiguration() && method.getMethodName().equals("beforeMethod")) {
			Validate.notEmpty(objs, "Parameter size error!");

			Method testMethod = (Method) objs[0];
			TestMethodHolder.set(testMethod);
			name = testMethod.getName();
			Test testAnno = testMethod.getAnnotation(Test.class);
			if (testAnno != null && StringUtils.isNotBlank(testAnno.dataProvider())) {
				Object[] params = (Object[]) objs[1];
				if (params != null && params.length > 0 && (params[0] instanceof ParamMap)) {
					ParamMap<String, ?> paramMap = (ParamMap<String, ?>) params[0];
					Object idx = paramMap.get(AutoVConsts.ACT_IDX_KEY);
					Object tabFileName = paramMap.get(AutoVConsts.DATA_FILE_NAME);
					if (idx != null) {
						String actIdx = idx.toString();
						String appendix = actIdx;
						if (tabFileName != null) {
							appendix += TABLE_FILE_SEPERATOR + tabFileName.toString();
						}
						// TODO will enable this later
						// LogAppendixHolder.set(appendix);
						name += TABLE_LOG_SEPERATOR + appendix;
					}
				}
			}
			isChangeLogKey = true;
		}

		if (method.isBeforeTestConfiguration() || method.isAfterTestConfiguration()
				|| method.isBeforeClassConfiguration() || method.isAfterClassConfiguration()) {
			isChangeLogKey = true;
		}

		// TODO will enable this later
		// if (isChangeLogKey) {
		// MDC.put("logKey", cl + "/" + name);
		// }
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	}
}
