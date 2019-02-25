package com.vip.qa.autov.core.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记方法为不重试，即使设置了失败重试次数
 * 
 * @author alex.ma
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRetry {
	public boolean noRetry() default true;
}
