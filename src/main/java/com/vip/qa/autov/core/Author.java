package com.vip.qa.autov.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

	String value();

	/**
	 * 用例新建时间
	 * 
	 * @return
	 */
	String createdOn() default "";

}
