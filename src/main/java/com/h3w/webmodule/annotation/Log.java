package com.h3w.webmodule.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Log {
    /**
     * 日志操作描述.例如:访问首页,添加角色,等等
     */
    String operation();

    /**
     * 被操作对象id,默认为空.可以采用SPEL的方式."#id"表示取被注解方法上的id参数,"#user.name"表示取被注解方法上的user参数的name属性
     */
    String objectId() default "";

    /**
     * 被操作对象,默认为空.如果使用"#user"取了被注解方法的user参数,则将其转换为json字符串
     */
    String object() default "";

    /**
     * 是否在注解方法执行后执行.比如新增用户,可能事先没有指定用户id,保存后自动生成,则需要指定此注解在被注解方法执行后执行
     */
    boolean afterInvocation() default false;
}
