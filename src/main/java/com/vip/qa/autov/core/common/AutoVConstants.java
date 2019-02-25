package com.vip.qa.autov.core.common;

/**
 * AutoV常量定义，必须只定义常量，不允许执行任意代码
 * Created by kevin02.zhou on 2017/12/1.
 */
public class AutoVConstants {


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

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int MAX_API_CACHE_SIZE = 50 * 1024;

    public static final String AUTOV_API_CACHE_HOSTS_KEY = "api.cache.conn.host";

    public static final String API_CACHE_CONN_TIMEOUT_KEY = "api.cache.conn.timeout";

    public static final String API_CACHE_KEY_PREFIX = "API:";

    public static final String API_CACHE_REQ_KEY_PREFIX = "REQ:";

    public static final String API_CACHE_RESP_KEY_PREFIX = "RESP:";

    public static final String API_CACHE_TS_KEY_PREFIX = "TS:";

    public static final String KEY_VTP_ENVIRONMENT = "environment";

    public static final int DEFAULT_API_CACHE_KEY_EXP_TIME = 100 * 24 * 3600;

    public static final String LOG_LEVEL_NAME = "log.level";

    public static final String MOCK_RPC_SERVICE_NAME = "serviceName";

    public static final String MOCK_RPC_METHOD_NAME = "methodName";

    public static final String MOCK_RPC_SERVER = "mock.rpc.server";

    public static final String MOCK_RPC_CONNECT_TIMEOUT = "mock.rpc.connect.timeout";

    public static final String MOCK_WORKER_POOL_SIZE = "mock.worker.pool.size";

    public static final String MOCK_RPC_READ_TIMEOUT = "mock.rpc.read.timeout";

    public static final String MOCK_STUB_NOT_HIT = "STUB_NOT_HIT";

    public static final int MOCK_RPC_DEFAULT_SERVER_PORT = 3081;

    public static final String HTTP_HEADER_SEQUENCE = "$mock_http_seq$";

    public static final String HTTP_HEADER_EXCEPTION = "$mock_http_exception$";

    public static final String URI_PING = "ping";

    public static final String MOCK_LOG_FILE = "logs/mock.log";

    // hermes report开关
    public static final String HERMES_REPORTER_START = "reporter.start";

    public static final String VTP_STAGING_ENVIRONMENT = "production";

}
