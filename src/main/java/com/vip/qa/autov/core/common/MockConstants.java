package com.vip.qa.autov.core.common;

/**
 * Mock常量定义，必须只定义常量，不允许执行任意代码
 * Created by kevin02.zhou on 2017/11/30.
 */
public class MockConstants {

    public static final String LOCAL_IP = "127.0.0.1";

    public static final String NAME_LOCAL_PROXY_PORT = "it.osp.proxy.port";
    public static final int DEFAULT_LOCAL_PROXY_PORT = 2080;
    public static final String DEFAULT_OSP_LOCAL_PROXY = LOCAL_IP + ":" + 2080;
    public static final int DEFAULT_OSP_CONTAINER_PORT = 2280;

    public static final String NAME_OVERRIDE_ZK_PORT = "it.zk.port";
    public static final String NAME_OVERRIDE_ZK_HOST = "it.zk.host";
    public static final int DEFAULT_OVERRIDE_ZK_PORT = 2181;

    public static final String NAME_OVERRIDE_OSP_PROXY_HOST = "it.proxy.host";

    public static final String NAME_HTTP_MOCK_SERVER_PORT = "it.http.port";
    public static final String NAME_HTTP_MOCK_SERVER_PORTS = "http.mock.server.ports";
    public static final int DEFAULT_HTTP_MOCK_SERVER_PORT = 9999;

    public static final String NAME_OSP_MOCK_SERVER_PORT = "it.osp.port";
    public static final int DEFAULT_OSP_MOCK_SERVER_PORT = 9998;

    public static final String NAME_JETTY_PORT = "it.jetty.port";
    public static final int DEFAULT_JETTY_PORT = 8080;

    public static final String NAME_RMI_PORT = "it.rmi.port";
    public static final int DEFAULT_RMI_PORT = 9997;

    public static final String NAME_ES_PORT = "it.es.port";
    public static final int DEFAULT_ES_PORT = 9200;

    public static final String NAME_SOCKET_PORT = "it.socket.port";
    public static final int DEFAULT_SOCKET_PORT = 10001;

    public static final String NGINX_SSL_DIR = "ssl";

    public static final String NAME_CFG_CENTER_DIR = "it.cfgcenter.dir";
    public static final String DEFAULT_CFG_CENTER_DIR = "cfgcenter";

    public static final String NAME_ASGARD_DIR = "it.asgard.dir";
    public static final String DEFAULT_ASGARD_DIR = "asgard";

    public static final String NAME_KAFKA_PORT = "it.kafaka.port";
    public static final int DEFAULT_KAFKA_PORT = 9092;

    public static final String NAME_LOCAL_PROXY = "VIP_OSP_LOCAL_PROXY"; // local proxy的ip/port信息
    public static final String NAME_REMOTE_PROXY = "VIP_OSP_REMOTE_PROXY"; // remote proxy的ip/port信息
    public static final String NAME_VIP_OSP_STATIC_ROUTE_ENV = "VIP_OSP_STATIC_ROUTE";
    public static final String NAME_VIP_OSP_SEND_TIMEOUT = "VIP_OSP_SEND_TIMEOUT";
    public static final String NAME_VIP_OSP_RECV_TIMEOUT = "VIP_OSP_RECV_TIMEOUT";
    public static final String NAME_VIP_OSP_STATIC_ROUTE = "osp.static.route";
    public static final String NAME_VIP_CFGCENTER_PARTITION = "VIP_CFGCENTER_PARTITION";
    public static final String DEFAULT_VIP_CFGCENTER_PARTITION = "default";
    public static final String NAME_CONTAINER_STARTED = "osp.container.started";
    public static final String NAME_CONTAINER_PORT = "osp.container.port";
    public static final String NAME_CONTAINER_RESTPORT = "osp.container.restport";
    public static final String NAME_DISABLE_BROWSER = "osp.disable.browser";
    public static final String NAME_PROXY_SHUTDOWNING = "OSP_PROXY_SHUTDOWNING";

    public static final String NAME_VIP_CFGCENTER_ZK_CONNECTION = "VIP_CFGCENTER_ZK_CONNECTION";
    public static final String LOCAL_VIP_CFGCENTER_ZK_CONNECTION = LOCAL_IP + ":" + DEFAULT_OVERRIDE_ZK_PORT;

    public static final String NAME_IT_MOCK_START = "it.mock.start";

    public static final String REDIS_EXEC_DIR = "classpath:/redis";

    public static final String TEMP_DIR = "temp";

    public static final String NGINX_SSL_CRT_FILE = "vip.com.crt";

    public static final String NGINX_SSL_KEY_FILE = "vip.com.key";

    public static final String NAME_VHOSTS_DIR = "it.vhosts.dir";
    public static final String DEFAULT_VHOSTS_DIR = "vhosts";

    public static final String NAME_IT_START_FLAG = "it.start.flag";

    public static final String MOCK_TYPE_KEY = "mock.type";

    public static final String PERF_MOCK_TYPE = "perf";

    public static final String NAME_PERF_MOCK = "mock.debug";

    public static final String KEY_PERF_MOCK_LOCAL = "mock.local.flag";

    public static final String NAME_IT_OSP_REGISTER_SKIP = "it.osp.register.skip";

    public static final String NAME_IT_MOCK_RPC_SERVER_PORT = "it.mock.rpc.service.port";

    public static final String NAME_IT_MOCK_RPC_SERVER = "it.mock.rpc.server";

    public static final String NAME_IT_MOCK_RPC_CONNECT_TIMEOUT = "it.mock.rpc.connect.timeout";

    public static final String NAME_IT_MOCK_BOSS_POOL_SIZE = "it.mock.boss.pool.size";

    public static final String NAME_IT_MOCK_WORKER_POOL_SIZE = "it.mock.worker.pool.size";

    public static final String NAME_IT_MOCK_RPC_READ_TIMEOUT = "it.mock.rpc.read.timeout";

    public static final String NAME_IT_MOCK_USE_LOCAL = "it.mock.use.local";

    public static final boolean DEFAULT_IT_MOCK_USE_LOCAL = false;

    public static final boolean DEFAULT_PERF_MOCK_DEBUG = false;

    public static final boolean DEFAULT_PERF_MOCK_LOCAL_FLAG = false;

    public static final String NAME_IT_OSP_SERVICE_TIMEOUT = "it.osp.service.timeout";

    public static final int DEFAULT_OSP_REST_PORT = 8060;

    public static final String NAME_IT_START_REST_TOOL = "it.osp.resttool.start";
    public static final boolean DEFAULT_IT_START_REST_TOOL = true;

    public static final String NAME_IT_DELETE_STATUS_ON_EXIT = "it.status.deleteOnExit";
    public static final boolean DEFAULT_IT_DELETE_STATUS_ON_EXIT = false;

    public static final String NAME_VTP_PROJECT_ID = "projectId";

    public static final String IT_STATUS_FILE_SUFFIX = "_it_status";

    public static final String COVERAGE_DEST_PATH = "target/coverage";

    public static final String HERMES_REPORTER_START = "reporter.start";

    public static final String NAME_IT_PROJECT_NAME = "it.project.name";

    //from com.vipshop.mercury.util.ClientConstants
    public static final String X_B3_TRACE_ID = "X-B3-Trace-Id";
    public static final String X_B3_SPAN_ID = "X-B3-Span-Id";
    public static final String X_B3_PARENT_ID = "X-B3-Parent-Id";
    public static final String X_B3_FLAG = "X-B3-Flag";
    public static final String X_B3_SAMPLED = "X-B3-Sampled";
    public static final String X_B3_SFQ = "X-B3-Sfq";
}
