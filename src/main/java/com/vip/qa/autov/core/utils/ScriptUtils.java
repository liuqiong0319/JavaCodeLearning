package com.vip.qa.autov.core.utils;

import com.vip.qa.autov.core.json.JsonMapper;
import groovy.lang.GString;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * Created by kevin02.zhou on 2017/12/5.
 */
public class ScriptUtils {

    public static  <T> T convertResult(Object result, Type returnType) {
        Object returnResult = result;
        if (result != null) {
            if (returnType.equals(void.class) || returnType.equals(Void.class)) {
                returnResult = null;
            }
            else if (result instanceof String || result instanceof GString) {
                if (returnType.equals(String.class)) {
                    returnResult = result.toString();
                }
                else {
                    returnResult = JsonMapper.fromJson(result.toString(), returnType);
                }
            }
            else if (!GenericTypeUtils.isType(result, returnType)) {
                returnResult = JsonMapper.fromJson(JsonMapper.toJson(result), returnType);
            }
        }
        return (T) returnResult;
    }

    public static boolean evaluateBoolean(final Object value) {
        boolean result;
        if (value instanceof Collection) {
            result = !((Collection) value).isEmpty();
        } else if (value instanceof Map) {
            result = !((Map) value).isEmpty();
        } else if (value instanceof String || value instanceof GString) {
            result = value.toString().length() > 0;
        } else if (value instanceof Number) {
            result = (((Number) value).doubleValue() > 0 || ((Number) value).doubleValue() < 0);
        } else if (value instanceof Boolean) {
            result = Boolean.TRUE.equals(value);
        } else {
            result = value != null;
        }
        return result;
    }
}
