package com.vip.qa.autov.core.api.repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识接口类属于api respority动态代理类，自动通过模板方式调用接口
 * 
 * @author alex.ma
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRepository {

	/**
	 * 指定api repo的bean name, 默认通过类型进行匹配
	 */
	String value() default "";

	/**
	 * 指定requester bean name, 不指定则需要通过ApiRepoSacnnerConfigurer进行绑定
	 */
	String requesterName() default "";

}
