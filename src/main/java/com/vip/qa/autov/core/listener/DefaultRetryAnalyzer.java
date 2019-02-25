package com.vip.qa.autov.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.vip.qa.autov.core.utils.Threads;

/**
 * testng失败重试的listener
 * 
 * @author alex.ma
 *
 */
public class DefaultRetryAnalyzer implements IRetryAnalyzer {

	private static final String MAX_RETRY = System.getProperty("maxRetries", "10");
	private int currentTry;
	private String previousTest = null;
	private String currentTest = null;

	private static Logger logger = LoggerFactory.getLogger(DefaultRetryAnalyzer.class);

	private static final String SLEEP_BETWEEN_VALUE = System.getProperty("sleepBetweenRetries", "1000");

	public DefaultRetryAnalyzer() {
		currentTry = 0;
	}

	@Override
	public boolean retry(ITestResult result) {

		// If a testcase has succeeded, this function is not called.
		boolean retValue = false;

		// If a testcase has been desigend by NoRetry,then it will not be retry
		NoRetry no = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(NoRetry.class);
		if (no != null) {

			boolean btn = no.noRetry();
			if (btn) {
				return false;
			}

		}

		int maxRetries = Integer.valueOf(MAX_RETRY);
		int sleepBetweenRetries = Integer.valueOf(SLEEP_BETWEEN_VALUE);

		currentTest = result.getTestContext().getCurrentXmlTest().getName();

		if (previousTest == null) {
			previousTest = currentTest;
		}
		if (!(previousTest.equals(currentTest))) {
			currentTry = 0;
		}

		if (currentTry < maxRetries && !result.isSuccess()) {
			Threads.sleep(sleepBetweenRetries);
			currentTry++;
			result.setStatus(ITestResult.SKIP);
			retValue = true;

		} else {
			currentTry = 0;
		}
		previousTest = currentTest;
		// if this method returns true, it will rerun the test once again.

		if (retValue) {
			logger.info("Test failed, current try count is " + currentTry);
		}

		return retValue;
	}

}
