package com.vip.qa.autov.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kevin02.zhou on 2017/9/17.
 * 复制Bean属性并覆盖到另一个Bean，允许忽略Null字段，如果属性的值是对象，会进行递归复制
 * 对于List的复制，采取"合并"的方式，在两个List找到"相同"的Bean进行属性复制。比较Bean是否相等会使用BeanComparator，通过addBeanComparator可以添加Comparator，如果没有对应的Comparator，则按Bean在List中的序号进行匹配；
 * 如果没有找到匹配的Bean，则进行添加
 * 对于Map的复制，也是"合并"的方式，value不存在时则添加，存在时则复制并覆盖属性
 */
public class MergeBeanUtils {

    private static Map<String, Comparator> beanComparatorMap = new ConcurrentHashMap<>();


    public static <T> void addBeanComparator(Comparator<T> comparator, Class<T> beanClass) {
        beanComparatorMap.put(beanClass.getName(), comparator);
    }

    public static String[] getNullPropertyNames(Object src) {
        if (src != null && !src.getClass().isPrimitive()) {
            final BeanWrapper srcBeanWrapper = new BeanWrapperImpl(src);
            PropertyDescriptor[] pds = srcBeanWrapper.getPropertyDescriptors();
            Set<String> propertyNameWithNullValue = new HashSet<>();
            for (PropertyDescriptor propertyDescriptor : pds) {
                Object srcValue = srcBeanWrapper.getPropertyValue(propertyDescriptor.getName());
                if (srcValue == null) {
                    propertyNameWithNullValue.add(propertyDescriptor.getName());
                }
            }
            String[] result = new String[propertyNameWithNullValue.size()];
            return propertyNameWithNullValue.toArray(result);
        }
        else {
            return new String[] {};
        }
    }

    public static void copyProperties(Object src, Object dest) {
        copyProperties(src, dest, null, false);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object dest) {
        copyProperties(src, dest, null, true);
    }


    public static void mergeList(List sourceList, List targetList, boolean ignoreNull) {
        for (int i=0; i<sourceList.size(); i++) {
            Object sourceElement = sourceList.get(i);
            Comparator comparator = beanComparatorMap.get(sourceElement.getClass().getName());
            if (comparator == null) {
                if (i < targetList.size()) {
                    Object targetElement = targetList.get(i);
                    copyProperties(sourceElement, targetElement, null, ignoreNull);
                }
                else {
                    targetList.add(sourceElement);
                }
            }
            else {
                boolean foundMatched = false;
                for (int j=0; j<targetList.size(); j++) {
                    Object targetElement = targetList.get(j);
                    if (comparator.compare(sourceElement, targetElement) == 0) {
                        copyProperties(sourceElement, targetElement, null, ignoreNull);
                        foundMatched = true;
                        break;
                    }
                }
                if (!foundMatched) {
                    targetList.add(sourceElement);
                }
            }

        }
    }

    public static void mergeMap(Map sourceMap, Map targetMap, boolean ignoreNull) {
        Iterator<Map.Entry> iterator = sourceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            if (targetMap.containsKey(entry.getKey())) {
                Object targetValue = targetMap.get(entry.getKey());
                Object sourceValue = entry.getValue();
                if (targetValue != null) {
                    copyProperties(sourceValue, targetValue, null, ignoreNull);
                }
                else {
                    targetMap.put(entry.getKey(), sourceValue);
                }
            }
            else {
                targetMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void copyProperties(Object source, Object target, Class<?> editable, boolean ignoreNull, String... ignoreProperties)
            throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (ignoreNull && value == null) {
                                continue;
                            }
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (!readMethod.getReturnType().isPrimitive() && !readMethod.getReturnType().equals(String.class)) {
                                Method targetReadMethod =  targetPd.getReadMethod();
                                if (targetReadMethod != null) {
                                    Object targetValue = targetReadMethod.invoke(target);
                                    if (value instanceof List && targetValue instanceof List) {
                                        List sourceList = (List) value;
                                        if (!sourceList.isEmpty() && !sourceList.get(0).getClass().isPrimitive() && !sourceList.get(0).getClass().equals(String.class)) {
                                            mergeList((List) value, (List)targetValue, ignoreNull);
                                            continue;
                                        }
                                    }
                                    else if (value instanceof Map && targetValue instanceof Map) {
                                        mergeMap((Map)value, (Map)targetValue, ignoreNull);
                                        continue;
                                    }
                                    else if (targetValue != null && value != null && !targetReadMethod.getReturnType().isPrimitive()) {
                                        copyPropertiesIgnoreNull(value, targetValue);
                                        continue;
                                    }
                                }
                            }
                            writeMethod.invoke(target, value);
                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}