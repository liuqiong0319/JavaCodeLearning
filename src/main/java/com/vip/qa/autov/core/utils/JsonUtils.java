package com.vip.qa.autov.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.vip.qa.autov.core.AutoVConsts;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin02.zhou on 2017/9/25.
 */
public class JsonUtils {

    private static SerializeConfig fastJsonSerializeConfig;

    static {
        fastJsonSerializeConfig = new SerializeConfig();
        fastJsonSerializeConfig.put(Date.class, new SimpleDateFormatSerializer(AutoVConsts.JSON_DATE_FORMAT));
    }

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String toSimpleJson(Object obj) {
        return JSON.toJSONString(obj, fastJsonSerializeConfig, SerializerFeature.WriteMapNullValue);
    }

    public static String toPrettyJson(Object obj) {
        return JSON.toJSONString(obj, fastJsonSerializeConfig, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }

    public static <T> T parseObject(String input, Type objectType) {
        return JSON.parseObject(input, objectType, Feature.IgnoreNotMatch, Feature.AllowISO8601DateFormat);
    }

    public static Object parseText(String input) {
        return JSON.parse(input, Feature.IgnoreNotMatch, Feature.AllowISO8601DateFormat);
    }

    public static List<Object> convertParams(Method method, String jsonParams) {
        Type[] paramTypes = method.getGenericParameterTypes();
        Object paramsJsonObject = parseText(jsonParams);
        if (paramsJsonObject instanceof Iterable) {
            List<String> paramList = new ArrayList<>();
            for (Object jsonObject : (Iterable)paramsJsonObject) {
                String paramString = null;
                if (jsonObject != null) {
                    paramString = toSimpleJson(jsonObject);
                }
                paramList.add(paramString);
            }
            String[] params = new String[paramList.size()];
            return convertParams(method, paramList.toArray(params));
        }
        else if (paramsJsonObject == null && paramTypes.length == 0) {
            return new ArrayList<>(0);
        }
        else {
            Validate.isTrue(1 == paramTypes.length, "param size not matched!");
            return convertParams(method, jsonParams);
        }
    }

    public static List<Object> convertParams(Method method, String... jsonParams) {
        Type[] paramTypes = method.getGenericParameterTypes();
        Validate.isTrue(jsonParams.length == paramTypes.length, "param size not matched!");
        List<Object> params = new ArrayList<>();
        int index = 0;
        for (String jsonParam : jsonParams) {
            Object param = null;
            if (jsonParam != null) {
                if (jsonParam.equals("null")) {
                    param = null;
                } else {
                    Type type = paramTypes[index];
                    param = parseObject(jsonParam, type);
                }
            }
            params.add(param);
            index++;
        }

        return params;
    }
}
