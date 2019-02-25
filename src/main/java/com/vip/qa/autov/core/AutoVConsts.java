package com.vip.qa.autov.core;

import com.vip.qa.autov.core.common.AutoVConstants;
import com.vip.qa.autov.core.common.AutoVProperties;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.FilePathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;

/**
 * Use {@link AutoVConstants}, {@link AutoVProperties} instead
 */
@Deprecated
//注解表示过时.
public class AutoVConsts {

	public static final String TEMPLATE_API_DIR = FilePathUtils.getRelativePathFromProps("template.api.dir",
			"template_api");

	public static final String TEMPLATE_RESP_DIR = FilePathUtils.getRelativePathFromProps("template.resp.dir",
			"template_resp");

	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

	public static final String PROFILES_DIR = "profiles";

	public static final String GLOBAL_PARAM_FILE = "global";

	public static final String HOSTS_FILE = "hosts";

	public static final String APPLICATION_PROPERTIES = "classpath:/application.test.properties";

	public static final String HTTP_POST_DATA_PARAM_NAME = "body";

	public static final String ACT_IDX_KEY = "ACT_IDX";

	public static final String DATA_FILE_NAME = "DATA_FILE";

	public static final String PROPERTIES_SOURCE_KEY = "autov.properties.source";

	public static final String JSON_FORMAT_PRETTY_KEY = "json.format.pretty";

	private static String activeProfile;

	public static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final int MAX_API_CACHE_SIZE = 50 * 1024;

	public static final String AUTOV_API_CACHE_HOSTS_KEY = "api.cache.conn.host";

	public static final String API_CACHE_CONN_TIMEOUT_KEY = "api.cache.conn.timeout";

	public static final String API_CACHE_KEY_PREFIX = "API:";

	public static final String API_CACHE_REQ_KEY_PREFIX = "REQ:";

	public static final String API_CACHE_RESP_KEY_PREFIX = "RESP:";

	public static final String API_CACHE_TS_KEY_PREFIX = "TS:";

	public static final String RUN_ID = ClassPathPropertiesUtils.getValue("runId");

	public static final String VTP_PROJECT_ID = getEnvOrProperty("projectId");

	public static final String KEY_VTP_ENVIRONMENT = "environment";

	public static final String VTP_ENVIRONMENT = getEnvOrProperty(KEY_VTP_ENVIRONMENT);

	public static final String VTP_STAGING_ENVIRONMENT = "production";

	public static final int DEFAULT_API_CACHE_KEY_EXP_TIME = 100 * 24 * 3600;

	public static final String MOCK_DIR = FilePathUtils.getRelativePathFromProps("mock.dir", "mocks");

	public static final String activeMockProfile = ClassPathPropertiesUtils.getProperty("it.mock.profile", null);

	public static final String LOG_LEVEL_NAME = "log.level";

	public static final String LOG_LEVEL = ClassPathPropertiesUtils.getProperty(LOG_LEVEL_NAME, "INFO");

	public static final boolean IS_JSON_PRETTY = ClassPathPropertiesUtils.getBoolean(JSON_FORMAT_PRETTY_KEY, true);

	public static final boolean IS_USE_LOCAL_DNS = ClassPathPropertiesUtils.getBoolean("dns.local.flag", true);

	public static final String MOCK_RPC_SERVICE_NAME = "serviceName";

	public static final String MOCK_RPC_METHOD_NAME = "methodName";

	public static final String MOCK_RPC_SERVER = "mock.rpc.server";

	public static final String MOCK_RPC_CONNECT_TIMEOUT = "mock.rpc.connect.timeout";

	public static final String MOCK_WORKER_POOL_SIZE = "mock.worker.pool.size";

	public static final String MOCK_RPC_READ_TIMEOUT = "mock.rpc.read.timeout";

	public static final String MOCK_STUB_NOT_HIT = "STUB_NOT_HIT";

	public static final int MOCK_RPC_DEFAULT_SERVER_PORT = 3081;

//	public static final String MOCK_RPC_ZK_ROOT_PATH = "/" + ClassPathPropertiesUtils.getProperty("VIP_CFGCENTER_PARTITION", "default")  + "/autov/service";

	public static final String HTTP_HEADER_SEQUENCE = "$mock_http_seq$";

	public static final String HTTP_HEADER_EXCEPTION = "$mock_http_exception$";

	public static final String URI_PING = "ping";

	public static final String MOCK_LOG_FILE = "logs/mock.log";

	public static final boolean ENABLE_REALTIME_REPORT = false;

	// public static final String PRINT_STACKTRACE_ON_ERROR_KEY =
	// "error.print.stacktrace";

	// hermes report开关
	public static final String HERMES_REPORTER_START = "reporter.start";

	// public static boolean isPrintStacetraceOnError() {
	// return ClassPathPropertiesUtils.getBoolean(PRINT_STACKTRACE_ON_ERROR_KEY,
	// true);
	// }

	public static int getApiCacheTimeout() {
		String timeoutStr = System.getProperty(API_CACHE_CONN_TIMEOUT_KEY);
		if (StringUtils.isBlank(timeoutStr)) {
			timeoutStr = ClassPathPropertiesUtils.getProperty(API_CACHE_CONN_TIMEOUT_KEY, "4000");
		}
		return Integer.valueOf(timeoutStr);
	}

	public static String getActiveProfile() {
		if (activeProfile == null) {
			activeProfile = System.getProperty(SPRING_PROFILES_ACTIVE);
		}
		return activeProfile;
	}

	public static void setActiveProfile(String profile) {
		activeProfile = profile;
		System.setProperty(SPRING_PROFILES_ACTIVE, profile);
	}

	public static String getProfileFile(String fileName) {
		Validate.notNull(activeProfile, "Profile not specified!");
		return "classpath:/profiles/" + activeProfile + "/" + fileName;
	}

	public static String getEnvOrProperty(String key) {
		String value = System.getenv(key);
		if (value != null) {
			return value;
		}
		return ClassPathPropertiesUtils.getValue(key);
	}

	public static File getProfileMockDir() {
		File baseMockDir = new File(MOCK_DIR);
		File profileMockDir = new File(baseMockDir, PROFILES_DIR + File.separator + activeMockProfile);
		return profileMockDir;
	}

	public static boolean isStaging(){
		return VTP_ENVIRONMENT != null && VTP_ENVIRONMENT.equals(VTP_STAGING_ENVIRONMENT);
	}

}
