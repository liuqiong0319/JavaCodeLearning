package com.vip.qa.autov.core.utils;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kevin on 2017/3/21.
 */
public class GenericTypeUtils {

    public static boolean isType(final Object instance, final Type expectedType) {
        if (instance == null) {
            return true;
        }

        final Class<?> expectedClass = toClass(expectedType);
        if (instance.getClass().isArray()) {
            final Type componentType = getGenericType(expectedType, 0);
            final Class<?> componentClass = toClass(componentType);
            if (componentClass.isAssignableFrom(instance.getClass().getComponentType())) {
                if (Array.getLength(instance) > 0) {
                    return isType(Array.get(instance, 0), componentType);
                }
                else {
                    return true;
                }
            }
        }

        boolean matched = false;
        final TypeVariable[] expectedTypeVariables = expectedClass.getTypeParameters();
        final TypeVariable[] actualTypeVariables = instance.getClass().getTypeParameters();
        if (expectedClass.isInstance(instance) && actualTypeVariables.length == expectedTypeVariables.length) {
            matched = true;
            if (instance instanceof Map) {
                final Map mapInstance = (Map) instance;
                if (!mapInstance.isEmpty()) {
                    final Map.Entry entry = (Map.Entry) mapInstance.entrySet().iterator().next();
                    final Object mapKey = entry.getKey();
                    final Object mapValue = entry.getValue();
                    return isType(mapKey, getGenericType(expectedType, 0)) && isType(mapValue, getGenericType(expectedType, 1));
                }
            }
            else if (instance instanceof Iterable) {
                final Iterator iterator = ((Iterable) instance).iterator();
                if (iterator.hasNext()) {
                    final Object element = iterator.next();
                    return isType(element, getGenericType(expectedType, 0));
                }
            }
        }
        return matched;
    }

    public static Class toClass(final Type type) {
        if (type instanceof ParameterizedType) {
            return toClass(((ParameterizedType) type).getRawType());
        }
        else if (type instanceof TypeVariable) {
            return toClass(((TypeVariable) type).getBounds()[0]);
        }
        else if (type instanceof GenericArrayType) {
            return toClass(((GenericArrayType) type).getGenericComponentType());
        }
        else {// class本身也是type，强制转型
            return (Class) type;
        }
    }

    public static Class toClass(final Type type, final int i) {
        if (type instanceof ParameterizedType) {
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return toClass(((TypeVariable) type).getBounds()[0], 0);
        }
        else if (type instanceof GenericArrayType) {
            return toClass(((GenericArrayType) type).getGenericComponentType());
        }
        else {// class本身也是type，强制转型
            return (Class) type;
        }
    }

    public static Type getGenericType(final Type type, final int i) {
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType)type).getActualTypeArguments()[i];
        }
        else if (type instanceof TypeVariable) {
            return ((TypeVariable) type).getBounds()[i];
        }
        else if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        else {
            return type;
        }
    }

    public static Class getGenericClass(final ParameterizedType parameterizedType, final int i) {
        final Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) {
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) {
            return (Class) toClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            return (Class) genericClass;
        }
    }
}
