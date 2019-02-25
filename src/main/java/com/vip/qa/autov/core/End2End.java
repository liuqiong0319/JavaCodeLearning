package com.vip.qa.autov.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 端到端用例，自动通过YamlSingleMapProvider关联起来, 测试方法的parammap参数自动从上下文参数注入
 * 注意：不能使用其他的dataProvider
 * 
 * @author alex.ma
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface End2End {

}
