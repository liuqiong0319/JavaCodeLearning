package com.vip.qa.autov.core.testcase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.spi.JoranException;
import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.End2End;
import com.vip.qa.autov.core.dns.HostFileHelper;
import com.vip.qa.autov.core.listener.End2EndMethodInvokeListener;
import com.vip.qa.autov.core.listener.ListenerSupport;
import com.vip.qa.autov.core.listener.ListenerSupport.Informer;
import com.vip.qa.autov.core.listener.LogConfigListener;
import com.vip.qa.autov.core.listener.MethodInvokeListener;
import com.vip.qa.autov.core.listener.RealtimeReportListener;
import com.vip.qa.autov.core.listener.TestEventListener;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.JarUtils;
import com.vip.qa.autov.core.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IConfigurable;
import org.testng.IConfigurationListener;
import org.testng.IExecutionListener;
import org.testng.IHookable;
import org.testng.IInvokedMethodListener;
import org.testng.IMethodInterceptor;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.internal.ClassHelper;
import org.testng.internal.IConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 所有测试用例的基类，必须继承
 * 
 * @author alex.ma
 *
 */
@ContextConfiguration(locations = { BaseTestNgTestCase.TEST_XML_CONTEXT })
@Listeners({ MethodInvokeListener.class, LogConfigListener.class, End2EndMethodInvokeListener.class })
public abstract class BaseTestNgTestCase extends AbstractTestNGSpringContextTests {

	public static final String AUTOV_PARENT_TEST_XML_CONTEXT = "autov-parent-context.xml";

	public static final String TEST_XML_CONTEXT = "classpath*:/applicationContext-test.xml";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static ListenerSupport<TestEventListener> testEventListeners = new ListenerSupport<TestEventListener>();

	private static Map<Method, AtomicInteger> methodCounter = new HashMap<>();

	// spring初始化前执行
	@BeforeSuite(alwaysRun = true)
	public final void beforeSuite(ITestContext context) throws Exception {
		printVersion();
		setLogLevel();
		// 预先加载spring， 不然before suite的方法不能使用spring的bean
		super.springTestContextPrepareTestInstance();
		HostFileHelper.initDns();
		if(AutoVConsts.RUN_ID != null && AutoVConsts.ENABLE_REALTIME_REPORT) {
            registerListeners(context, RealtimeReportListener.class);
		}
        MethodGroupMappingHolder.init(context);
	}

	@BeforeMethod(alwaysRun = true)
	public final void beforeMethod(final Method method, Object[] params, ITestContext context) {
		String className = method.getDeclaringClass().getName();
		String logKey = className + File.separator + method.getName();

		// e2e测试的log key是testng.xml的test name
		if (method.getDeclaringClass().isAnnotationPresent(End2End.class) || method.isAnnotationPresent(End2End.class)) {
			logKey = context.getCurrentXmlTest().getName();
		}

		//对测试用例日志按照类名方法名进行分割，runId由VTP传入
		String runIdSuffix = AutoVConsts.RUN_ID;
		if (StringUtils.isNotBlank(runIdSuffix)) {
			logKey = runIdSuffix + File.separator + logKey;
		}


		boolean isDataDriven = params != null && params.length > 0;
		AtomicInteger counter = methodCounter.get(method);
		if (isDataDriven) {
			if (counter == null) {
				counter = new AtomicInteger();
				methodCounter.put(method, counter);
			}
			logKey = logKey + counter.incrementAndGet();
		}

		// TODO will disable this later
		MDC.put("logKey", logKey);

		logger.info("################## Running test method: {} ##################", className + "." + method.getName()
				+ (isDataDriven ? "#" + counter.get() : ""));
		if (isDataDriven) {
			logger.debug("[Table Params]: " + Arrays.asList(params).toString());
		}

		testEventListeners.apply(new Informer<TestEventListener>() {
			@Override
			public void inform(TestEventListener listener) {
				listener.onMethodStart(method);
			}
		});
	}

	@AfterMethod(alwaysRun = true)
	public final void afterMethod(final Method method, Object[] params, ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		Throwable exception = result.getThrowable();
		if (exception != null) {
			logger.error(Exceptions.getStackTraceAsString(exception));
		}
		testEventListeners.apply(new Informer<TestEventListener>() {
			@Override
			public void inform(TestEventListener listener) {
				try {
					listener.onMethodFinish(method);
				}
				catch (Exception e) {
					logger.error("Error thrown by afterMethod event listener", e);
				}
			}
		});

		AtomicInteger counter = methodCounter.get(method);
		boolean isDataDriven = counter != null && params != null && params.length > 0;

		logger.info("################## Finished test method: {} ##################", method.getDeclaringClass()
				.getName() + "." + methodName + (isDataDriven ? "#" + counter.get() : ""));
	}

	/**
	 * allow users to add custom listeners
	 * 
	 * @param context
	 * @throws Exception
	 */
	@SafeVarargs
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected final void registerListeners(ITestContext context, Class<? extends ITestNGListener>... listenerClasses)
			throws Exception {
		if (listenerClasses == null || listenerClasses.length == 0) {
			return;
		}
		for (Class<? extends ITestNGListener> listenerClass : listenerClasses) {
			TestRunner tr = (TestRunner) context;
			Object listener = ClassHelper.newInstance(listenerClass);
			IConfiguration m_configuration = (IConfiguration) ReflectionUtils.getFieldValue(tr, "m_configuration");
			ISuite m_suite = tr.getSuite();

			if (listener instanceof IMethodInterceptor) {
				List m_methodInterceptors = (List) ReflectionUtils.getFieldValue(tr, "m_methodInterceptors");
				m_methodInterceptors.add(listener);
			}
			if (listener instanceof ISuiteListener) {
				m_suite.addListener((ISuiteListener) listener);
			}
			if (listener instanceof IInvokedMethodListener) {
				m_suite.addListener((ITestNGListener) listener);
			}
			if (listener instanceof ITestListener) {
				tr.addTestListener((ITestListener) listener);
			}
			if (listener.getClass().getSimpleName().equals("IClassListener")) {
				List m_classListeners = (List) ReflectionUtils.getFieldValue(tr, "m_classListeners");
				m_classListeners.add(listener);
			}
			if (listener instanceof IConfigurationListener) {
				ReflectionUtils.invokeMethod(context, "addConfigurationListener",
						new Class[] { IConfigurationListener.class }, listener);

			}
			if (listener instanceof IReporter) {
				m_suite.addListener((ITestNGListener) listener);
			}
			if (listener instanceof IConfigurable) {
				m_configuration.setConfigurable((IConfigurable) listener);
			}
			if (listener instanceof IHookable) {
				m_configuration.setHookable((IHookable) listener);
			}
			if (listener instanceof IExecutionListener) {
				IExecutionListener iel = (IExecutionListener) listener;
				iel.onExecutionStart();
				m_configuration.addExecutionListener(iel);
			}
		}

	}

	private void setLogLevel() throws JoranException, IOException {
		ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
		if (iLoggerFactory == null) {
			return;
		}
		if (iLoggerFactory instanceof LoggerContext) {
			LoggerContext loggerContext = (LoggerContext) iLoggerFactory;
			ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("ROOT");
			if (rootLogger != null && ClassPathPropertiesUtils.hasProperty(AutoVConsts.LOG_LEVEL_NAME)) {
				String level = AutoVConsts.LOG_LEVEL;
				if (level.equalsIgnoreCase("debug")) {
					rootLogger.setLevel(Level.DEBUG);
				} else if (level.equalsIgnoreCase("trace")) {
					rootLogger.setLevel(Level.TRACE);
				} else if (level.equalsIgnoreCase("warn")) {
					rootLogger.setLevel(Level.WARN);
				} else if (level.equalsIgnoreCase("error")) {
					rootLogger.setLevel(Level.ERROR);
				} else {
					rootLogger.setLevel(Level.INFO);
				}
			}
		}
	}

	public static void addTestMethodListener(TestEventListener listener) {
		testEventListeners.add(listener);
	}

	/**
	 * 打印版本方便定位问题
	 */
	private void printVersion() {
		String version = JarUtils.getJarVersion(BaseTestNgTestCase.class);
		if (version != null) {
			logger.info("AutoV version: {}", version);
		}
	}

}
