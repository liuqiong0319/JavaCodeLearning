package com.vip.qa.autov.core.common;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.FilePathUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.vip.qa.autov.core.common.AutoVConstants.PROFILES_DIR;
import static com.vip.qa.autov.core.common.MockConstants.*;

/**
 * TBC
 * Created by kevin02.zhou on 2017/12/1.
 */
public class MockProperties {

    public static final int LOCAL_PROXY_PORT = ClassPathPropertiesUtils.getInteger(NAME_LOCAL_PROXY_PORT, DEFAULT_LOCAL_PROXY_PORT);

    public static final int ZK_PORT = ClassPathPropertiesUtils.getInteger(NAME_OVERRIDE_ZK_PORT, DEFAULT_OVERRIDE_ZK_PORT);

    public static final String OVERRIDE_OSP_PROXY_HOST = ClassPathPropertiesUtils.getValue(NAME_OVERRIDE_OSP_PROXY_HOST);

    public static final int HTTP_MOCK_SERVER_PORT = ClassPathPropertiesUtils.getInteger(NAME_HTTP_MOCK_SERVER_PORT, DEFAULT_HTTP_MOCK_SERVER_PORT);

    public static final List<String> HTTP_MOCK_SERVER_PORTS = Arrays.asList(ClassPathPropertiesUtils.getProperty(NAME_HTTP_MOCK_SERVER_PORTS, "80").split(","));

    public static final int OSP_MOCK_SERVER_PORT = ClassPathPropertiesUtils.getInteger(NAME_OSP_MOCK_SERVER_PORT, DEFAULT_OSP_MOCK_SERVER_PORT);

    public static final int JETTY_PORT = ClassPathPropertiesUtils.getInteger(NAME_JETTY_PORT, DEFAULT_JETTY_PORT);

    public static final int RMI_PORT = ClassPathPropertiesUtils.getInteger(NAME_RMI_PORT, DEFAULT_RMI_PORT);

    public static final int ES_PORT = ClassPathPropertiesUtils.getInteger(NAME_ES_PORT, DEFAULT_ES_PORT);

    public static final int SOCKET_PORT = ClassPathPropertiesUtils.getInteger(NAME_SOCKET_PORT, DEFAULT_SOCKET_PORT);

    public static final String CFG_CENTER_DIR = FilePathUtils.getRelativePathFromProps(NAME_CFG_CENTER_DIR, DEFAULT_CFG_CENTER_DIR);

    public static final String ASGARD_DIR = FilePathUtils.getRelativePathFromProps(NAME_ASGARD_DIR, DEFAULT_ASGARD_DIR);

    public static final int KAFKA_PORT = ClassPathPropertiesUtils.getInteger(NAME_KAFKA_PORT, DEFAULT_KAFKA_PORT);

    public static final String VIP_CFGCENTER_PARTITION = StringUtils.defaultIfBlank(System.getenv(NAME_VIP_CFGCENTER_PARTITION), ClassPathPropertiesUtils.getProperty(NAME_VIP_CFGCENTER_PARTITION, DEFAULT_VIP_CFGCENTER_PARTITION));

    public static final String BOOTSTRAP_PATH = "/" + VIP_CFGCENTER_PARTITION + "/bootstrap";

    public static final String VHOSTS_DIR = FilePathUtils.getRelativePathFromProps(NAME_VHOSTS_DIR, DEFAULT_VHOSTS_DIR);

    public static final boolean IT_MOCK_USE_LOCAL = ClassPathPropertiesUtils.getBoolean(NAME_IT_MOCK_USE_LOCAL, DEFAULT_IT_MOCK_USE_LOCAL);

    public static final String IT_MOCK_RPC_ZK_ROOT_PATH = "/" + VIP_CFGCENTER_PARTITION  + "/autov/service";

    public static final boolean IT_OSP_REGISTER_SKIP = ClassPathPropertiesUtils.getBoolean(NAME_IT_OSP_REGISTER_SKIP, false);

    public static final boolean PERF_MOCK_DEBUG = ClassPathPropertiesUtils.getBoolean(NAME_PERF_MOCK, DEFAULT_PERF_MOCK_DEBUG);

    public static final boolean PERF_MOCK_LOCAL_FLAG = ClassPathPropertiesUtils.getBoolean(KEY_PERF_MOCK_LOCAL, DEFAULT_PERF_MOCK_LOCAL_FLAG);

    public static final String IT_OSP_SERVICE_TIMEOUT = ClassPathPropertiesUtils.getValue(NAME_IT_OSP_SERVICE_TIMEOUT);

    public static final boolean IT_START_REST_TOOL = ClassPathPropertiesUtils.getBoolean(NAME_IT_START_REST_TOOL, DEFAULT_IT_START_REST_TOOL);

    public static final int IT_MOCK_BOSS_POOL_SIZE = ClassPathPropertiesUtils.getInteger(NAME_IT_MOCK_WORKER_POOL_SIZE, 1);

    public static final int IT_MOCK_WORKER_POOL_SIZE = ClassPathPropertiesUtils.getInteger(NAME_IT_MOCK_WORKER_POOL_SIZE, 0);

    public static final boolean IT_DELETE_STATUS_ON_EXIT = ClassPathPropertiesUtils.getBoolean(
            NAME_IT_DELETE_STATUS_ON_EXIT, DEFAULT_IT_DELETE_STATUS_ON_EXIT);

    public static final String VTP_PROJECT_ID = AutoVConsts.getEnvOrProperty(NAME_VTP_PROJECT_ID);

    public static final String IT_PROJECT_NAME = ClassPathPropertiesUtils.getValue(NAME_IT_PROJECT_NAME);

    public static final File TEMP_DIR = new File("/tmp");

    public static final String VIP_OSP_LOCAL_PROXY = StringUtils.defaultIfBlank(OVERRIDE_OSP_PROXY_HOST, DEFAULT_OSP_LOCAL_PROXY);

    public static final String VIP_CFGCENTER_ZK_CONNECTION = StringUtils.defaultIfBlank(ClassPathPropertiesUtils.getProperty(NAME_OVERRIDE_ZK_HOST, System.getenv(NAME_VIP_CFGCENTER_ZK_CONNECTION)), LOCAL_VIP_CFGCENTER_ZK_CONNECTION);

    public static final boolean START_MOCK = ClassPathPropertiesUtils.getBoolean(NAME_IT_MOCK_START, false);

    public static final boolean START_ZK = VIP_CFGCENTER_ZK_CONNECTION.equals(LOCAL_VIP_CFGCENTER_ZK_CONNECTION);

    public static final boolean START_PROXY = VIP_OSP_LOCAL_PROXY.equals(DEFAULT_OSP_LOCAL_PROXY);

    public static final String MOCK_DIR = FilePathUtils.getRelativePathFromProps("mock.dir", "mocks");

    public static final String JAR_DIR = FilePathUtils.getRelativePathFromProps("jar.dir", "jar_dir");

    public static final String activeMockProfile = ClassPathPropertiesUtils.getProperty("it.mock.profile", null);

    public static File getProfileMockDir() {
        File baseMockDir = new File(MOCK_DIR);
        File profileMockDir = new File(baseMockDir, PROFILES_DIR + File.separator + activeMockProfile);
        return profileMockDir;
    }


}
