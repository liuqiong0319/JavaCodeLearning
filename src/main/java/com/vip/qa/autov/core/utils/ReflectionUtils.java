package com.vip.qa.autov.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

	public static Field getFieldByName(Object obj, String name) {
		Field f = null;
		try {
			f = obj.getClass().getDeclaredField(name);
		} catch (Exception e) {
		}
		return f;
	}

	public static Method getMethodByName(Class<?> clazz, String name) {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

	public static Method getMethodByName(Class<?> clazz, String name, int idx) {
		Method[] methods = clazz.getMethods();
		int counter = 0;
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				if (counter == idx) {
					return method;
				} else {
					counter++;
				}
			}
		}
		return null;
	}

	public static Object invokeGetterMethod(Object target, String propertyName) throws Exception {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		if (!isMethodExists(target, getterMethodName, new Class[] {})) {
			getterMethodName = "is" + StringUtils.capitalize(propertyName);
		}
		return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
	}

	@SuppressWarnings("unchecked")
	public static boolean isFieldAnnotationPresent(Object obj, String fieldName, Class clazz) {
		Field field = getFieldByName(obj, fieldName);
		try {
			return field.isAnnotationPresent(clazz);
		} catch (Exception e) {
		}
		return false;
	}

	public static Method getGetterMethodByPropertyName(Object target, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		if (!isMethodExists(target, getterMethodName, new Class[] {})) {
			getterMethodName = "is" + StringUtils.capitalize(propertyName);
		}
		Method method = null;
		try {
			method = target.getClass().getDeclaredMethod(getterMethodName, null);
		} catch (Exception e) {
		}
		return method;
	}

	public static void invokeSetterMethod(Object target, String propertyName, Object value) throws Exception {
		invokeSetterMethod(target, propertyName, value, null);
	}

	public static void invokeSetterMethod(Object target, String propertyName, Object value, Class<?> propertyType)
			throws Exception {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalize(propertyName);
		invokeMethod(target, setterMethodName, new Class[] { type }, new Object[] { value });
	}

	public static <T> T getFieldValue(final Object object, final String fieldName) {
		return (T) operaFieldValue("getFieldValue", object, fieldName, null);
	}

	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		operaFieldValue("setFieldValue", object, fieldName, value);
	}

	public static void setStaticFieldValue(final Class<?> clazz, final String fieldName, final Object value) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(clazz, value);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	public static Object getStaticFieldValue(final Class<?> clazz, String fieldName) throws Exception {
		Field field = getStaticField(clazz, fieldName);
		if (field != null) {
			Object value = field.get(clazz);
			return value;
		}
		return null;
	}

	public static Field getStaticField(final Class<?> clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
		}
		catch (NoSuchFieldException e) {
			//
		}
		return field;
	}

	private static Object operaFieldValue(String optype, final Object object, final String fieldName, final Object value) {
		Object result = null;
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		try {
			if ("setFieldValue".equals(optype)) {
				field.set(object, value);
			} else {
				result = field.get(object);
			}
		} catch (IllegalAccessException e) {
			Exceptions.checked(e);
		}
		return result;
	}

	public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
			final Object... parameters) throws Exception {
		Method method = getAccessbleMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}

		return method.invoke(object, parameters);
	}

	public static Object invokeStaticMethod(ClassLoader loader, final String clazzName, final String methodName,
			final Class<?>[] parameterTypes, final Object... parameters) {
		try {
			Class<?> clazz = Class.forName(clazzName, true, loader);
			return invokeStaticMethod(clazz, methodName, parameterTypes, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

	}

	public static Object invokeStaticMethod(final String clazzName, final String methodName,
			final Class<?>[] parameterTypes, final Object... parameters) {
		return invokeStaticMethod(Thread.currentThread().getContextClassLoader(), clazzName, methodName,
				parameterTypes, parameters);

	}

	public static Object invokeStaticMethod(final Class<?> clazz, final String methodName,
			final Class<?>[] parameterTypes, final Object... parameters) {
		try {
			Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
			return method.invoke(clazz, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

	}

	private static boolean isMethodExists(final Object object, final String methodName, final Class<?>[] parameterTypes) {
		Method method = getAccessbleMethod(object, methodName, parameterTypes);
		if (method == null) {
			return false;
		}
		return true;
	}

	public static Method getAccessbleMethod(Object object, String methodName, Class<?>... parameterTypes) {
		Validate.notNull(object);
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Method method = superClass.getDeclaredMethod(methodName, parameterTypes);
				method.setAccessible(true);
				return method;
			} catch (NoSuchMethodException e) {
			}
		}

		return null;
	}

	public static Field getDeclaredField(final Object object, final String fieldName) {
		Validate.notNull(object);
		Validate.notNull(fieldName);
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	public static <T> Object getInvokeValue(T t, String methodName) {
		try {
			Method method = MethodUtils.getAccessibleMethod(t.getClass(), methodName);
			return method.invoke(t);
		} catch (Exception e) {
			return null;
		}
	}

	public static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}


	public static Class<?> getClassWithAnnotationPresent(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Validate.notNull(clazz);
		Validate.notNull(annotationClass);
		Class<?> classWithAnnotation = null;
		for (Class<?> superClass = clazz; superClass != Object.class && superClass != null; superClass = superClass
				.getSuperclass()) {
			if (superClass.isAnnotationPresent(annotationClass)) {
				classWithAnnotation = superClass;
			}
			else {
				for (Class<?> interfaceClass : superClass.getInterfaces()) {
					if (interfaceClass.isAnnotationPresent(annotationClass)) {
						classWithAnnotation = interfaceClass;
						break;
					}
				}
			}
			if (classWithAnnotation != null) {
				break;
			}
		}
		return classWithAnnotation;
	}

	public static boolean isAnnotationPresent(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Validate.notNull(clazz);
		Validate.notNull(annotationClass);
		boolean hasAnnotation = false;
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			hasAnnotation |= superClass.isAnnotationPresent(annotationClass);
			if (!hasAnnotation) {
				for (Class<?> interfaceClass : superClass.getInterfaces()) {
					hasAnnotation |= interfaceClass.isAnnotationPresent(annotationClass);
				}
			}
			if (hasAnnotation) {
				break;
			}
		}

		return hasAnnotation;
	}
}
