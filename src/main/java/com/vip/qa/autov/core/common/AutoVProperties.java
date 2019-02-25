package com.vip.qa.autov.core.common;

import com.vip.qa.autov.core.utils.ClassPathPropertiesUtils;
import com.vip.qa.autov.core.utils.FilePathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import static com.vip.qa.autov.core.common.AutoVConstants.*;

/**
 * Created by kevin02.zhou on 2017/12/1.
 */
public class AutoVProperties {

    public static final String TEMPLATE_API_DIR = FilePathUtils.getRelativePathFromProps("template.api.dir",
            "template_api");

    public static final String TEMPLATE_RESP_DIR = FilePathUtils.getRelativePathFromProps("template.resp.dir",
            "template_resp");

    public static final String RUN_ID = ClassPathPropertiesUtils.getValue("runId");

    public static final String VTP_PROJECT_ID = getEnvOrProperty("projectId");

    public static final String VTP_ENVIRONMENT = getEnvOrProperty(KEY_VTP_ENVIRONMENT);

    private static String activeProfile = System.getProperty(SPRING_PROFILES_ACTIVE);

    public static final String LOG_LEVEL = ClassPathPropertiesUtils.getProperty(LOG_LEVEL_NAME, "INFO");

    public static final boolean IS_JSON_PRETTY = ClassPathPropertiesUtils.getBoolean(JSON_FORMAT_PRETTY_KEY, true);

    public static final boolean IS_USE_LOCAL_DNS = ClassPathPropertiesUtils.getBoolean("dns.local.flag", true);

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

    public static boolean isStagingEnvironment(){
        return VTP_ENVIRONMENT != null && VTP_ENVIRONMENT.equals(VTP_STAGING_ENVIRONMENT);
    }

}
