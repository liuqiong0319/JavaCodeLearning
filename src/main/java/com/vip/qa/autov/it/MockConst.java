package com.vip.qa.autov.it;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.common.MockConstants;
import com.vip.qa.autov.core.common.MockProperties;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.FilePathUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Use {@link MockConstants}, ${@link MockProperties} instead
 */
@Deprecated
public class MockConst {

	public static final String LOCAL_IP = "127.0.0.1";

	public static final int LOCAL_PROXY_PORT = ClassPathPropertiesUtils.getInteger("it.osp.proxy.port", 2080);

	public static final int OSP_CONTAINER_PORT_DEFAULT = 2280;

	public static final int ZK_PORT = ClassPathPropertiesUtils.getInteger("it.zk.port", 2181);

	public static final String OVERIDE_OSP_PROXY_HOST = ClassPathPropertiesUtils.getValue("it.proxy.host");

	public static final int HTTP_MOCK_SERVER_PORT = ClassPathPropertiesUtils.getInteger("it.http.port", 9999);

	public static final int OSP_MOCK_SERVER_PORT = ClassPathPropertiesUtils.getInteger("it.osp.port", 9998);

	public static final int JETTY_PORT = ClassPathPropertiesUtils.getInteger("it.jetty.port", 8080);

	public static final int RMI_PORT = ClassPathPropertiesUtils.getInteger("it.rmi.port", 9997);

	public static final int ES_PORT = ClassPathPropertiesUtils.getInteger("it.es.port", 9200);

	public static final int SOCKET_PORT = ClassPathPropertiesUtils.getInteger("it.socket.port", 10001);

	public static final String NGINX_SSL_DIR = "ssl";

	public static final String CFG_CENTER_DIR = FilePathUtils.getRelativePathFromProps("it.cfgcenter.dir", "cfgcenter");

	public static final String ASGARD_DIR = FilePathUtils.getRelativePathFromProps("it.asgard.dir", "asgard");

	public static final int KAFKA_PORT = ClassPathPropertiesUtils.getInteger("it.kafaka.port", 9092);

	public static final String DEFAULT_ZK_PARTITION = "default";

	public static final String NAME_VIP_CFGCENTER_PARTITION = "VIP_CFGCENTER_PARTITION";

	public static final String VIP_CFGCENTER_PARTITION = StringUtils.defaultString(System.getenv(NAME_VIP_CFGCENTER_PARTITION), ClassPathPropertiesUtils.getProperty(NAME_VIP_CFGCENTER_PARTITION, DEFAULT_ZK_PARTITION));

	public static final String NAME_VIP_CFGCENTER_ZK_CONNECTION = "VIP_CFGCENTER_ZK_CONNECTION";

	public static final String BOOTSTRAP_PATH = "/" + VIP_CFGCENTER_PARTITION + "/bootstrap";

	public static final String IT_MOCK_KEY = "it.mock.start";

	public static final String REDIS_EXEC_DIR = "classpath:/redis";

	public static final String TEMP_DIR = "temp";

	public static final String NGINX_SSL_CRT_FILE = "vip.com.crt";

	public static final String NGINX_SSL_KEY_FILE = "vip.com.key";

	public static final String VHOSTS_DIR = FilePathUtils.getRelativePathFromProps("it.vhosts.dir", "vhosts");

	public static final String IT_START_FLAG = "it.start.flag";

	public static final String MOCK_TYPE_KEY = "mock.type";

	public static final String PERF_MOCK_TYPE = "perf";

	public static final String PERF_MOCK_KEY = "mock.debug";

	public static final String PERF_MOCK_LOCAL_KEY = "mock.local.flag";

	public static final String IT_OSP_REGISTER_SKIP_KEY = "it.osp.register.skip";

	public static final String IT_MOCK_RPC_SERVER_PORT_KEY = "it.mock.rpc.service.port";



	public static final String IT_MOCK_RPC_SERVER = "it.mock.rpc.server";

	public static final String IT_MOCK_RPC_CONNECT_TIMEOUT = "it.mock.rpc.connect.timeout";

	public static final String IT_MOCK_WORKER_POOL_SIZE = "it.mock.worker.pool.size";

	public static final String IT_MOCK_RPC_READ_TIMEOUT = "it.mock.rpc.read.timeout";

	public static final boolean IT_MOCK_USE_LOCAL = ClassPathPropertiesUtils.getBoolean("it.mock.use.local", false);

	public static final String IT_MOCK_RPC_ZK_ROOT_PATH = "/" + VIP_CFGCENTER_PARTITION  + "/autov/service";

	public static final boolean IT_OSP_REGISTER_SKIP = ClassPathPropertiesUtils.getBoolean(IT_OSP_REGISTER_SKIP_KEY,
			false);

	public static final boolean PERF_MOCK_DEBUG = ClassPathPropertiesUtils.getBoolean(PERF_MOCK_KEY, false);

	public static final boolean PERF_MOCK_LOCAL_FLAG = ClassPathPropertiesUtils.getBoolean(PERF_MOCK_LOCAL_KEY, false);

	public static final String IT_OSP_SERVICE_TIMEOUT = ClassPathPropertiesUtils.getValue("it.osp.service.timeout");

	public static final int OSP_REST_PORT_DEFAULT = 8060;

	public static final boolean IT_START_REST_TOOL = ClassPathPropertiesUtils.getBoolean("it.osp.resttool.start", true);

	public static final boolean IT_DELETE_STATUS_ON_EXIT = ClassPathPropertiesUtils.getBoolean(
			"it.status.deleteOnExit", false);

	public static final String VTP_PROJECT_ID = AutoVConsts.getEnvOrProperty("projectId");

	public static final String IT_STATUS_FILE_SUFFIX = "_it_status";

	public static final String COVERAGE_DEST_PATH = "target/coverage";

	private static boolean isStartZk;

	public static final String HERMES_REPORTER_START = "reporter.start";

	public static final String IT_PROJECT_NAME = ClassPathPropertiesUtils.getValue("it.project.name");

	private static boolean isStartProxy;

	public static boolean isStartZk() {
		return isStartZk;
	}

	public static boolean isStartProxy() {
		return isStartProxy;
	}

	public static File getTempDir() {
		File tempDir = new File("/tmp");
		return tempDir;
	}

	public static boolean isStartMock() {
		return ClassPathPropertiesUtils.getBoolean(IT_MOCK_KEY, false);
	}

	public static String getZkHost() {
		String overrideZkHost = ClassPathPropertiesUtils.getValue("it.zk.host");
		if (StringUtils.isBlank(overrideZkHost)) {
			isStartZk = true;
			try {
				return InetAddress.getLocalHost().getHostAddress() + ":" + ZK_PORT;
			}
			catch (UnknownHostException e) {
				return LOCAL_IP + ":" + ZK_PORT;
			}
		}
		return overrideZkHost;
	}

	public static String getProxyHost() {
		String overrideProxyHost = ClassPathPropertiesUtils.getValue("it.proxy.host");
		if (StringUtils.isBlank(overrideProxyHost)) {
			isStartProxy = true;
			return LOCAL_IP + ":" + LOCAL_PROXY_PORT;
		}
		return overrideProxyHost;
	}

	public static String getZkPartition() {
		return StringUtils.defaultString(AutoVConsts.getEnvOrProperty(NAME_VIP_CFGCENTER_PARTITION), DEFAULT_ZK_PARTITION);
	}
}
