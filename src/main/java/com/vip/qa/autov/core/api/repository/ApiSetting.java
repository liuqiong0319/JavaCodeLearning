package com.vip.qa.autov.core.api.repository;

import com.vip.qa.autov.core.json.JsonKeyFormatStrategy;
import com.vip.qa.autov.core.json.JsonValueFormatStrategy;
import com.vip.qa.autov.core.json.strategy.NoneKeyFormater;
import com.vip.qa.autov.core.json.strategy.NoneValueFormater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * public @interface xxx 定义注解

 @interface 不是interface，是注解类
 是jdk1.5之后加入的，java没有给它新的关键字，所以就用@interface 这么个东西表示了
 这个注解类，就是定义一个可用的注解，包括这个注解用于什么地方，是类，还是方法，还是property，还是方法入参等等
 @Retention定义了该Annotation被保留的时间长短：
 某些Annotation仅出现在源代码中，而被编译器丢弃；
 而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略;
 而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 使用这个meta-Annotation可以对 Annotation的“生命周期”限制。

 取值（RetentionPoicy）有：
 　1.SOURCE:在源文件中有效（即源文件保留）
 　　　　2.CLASS:在class文件中有效（即class保留）
 　　　　3.RUNTIME:在运行时有效（即运行时保留）

 @Target:注解的作用目标

 @Target(ElementType.TYPE)   //接口、类、枚举、注解
 　　　　　　　　@Target(ElementType.FIELD) //字段、枚举的常量
　　　　　　　　@Target(ElementType.METHOD) //方法
　　　　　　　　@Target(ElementType.PARAMETER) //方法参数
　　　　　　　　@Target(ElementType.CONSTRUCTOR)  //构造函数
　　　　　　　　@Target(ElementType.LOCAL_VARIABLE)//局部变量
　　　　　　　　@Target(ElementType.ANNOTATION_TYPE)//注解
　　　　　　　　@Target(ElementType.PACKAGE) ///包

 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface ApiSetting {

	/**
	 * 接口名称（domain或者service下的接口模板路径），如果不指定默认为方法名
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 接口模板后缀
	 * 
	 * @return
	 */
	String suffix() default "";

	/**
	 * 对查询结果的的json的所有key进行转换, 例如大小写
	 * 
	 */
	Class<?> keyFormater() default NoneKeyFormater.class;

	/**
	 * 对查询结果的的json的所有key进行转换, 例如大小写
	 * 
	 */
	@Deprecated
	JsonKeyFormatStrategy keyFormatStrategy() default JsonKeyFormatStrategy.NONE;

	/**
	 * 对查询结果的的json的所有value进行转换, 例如数字小数位等
	 * 
	 */
	Class<?>[] valueFormater() default NoneValueFormater.class;

	/**
	 * 对查询结果的的json的所有value进行转换, 例如数字小数位等
	 * 
	 */
	@Deprecated
	JsonValueFormatStrategy[] valueFormatStrategy() default {};

}
