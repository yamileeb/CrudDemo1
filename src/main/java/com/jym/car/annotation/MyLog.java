package com.jym.car.annotation;

import java.lang.annotation.*;

/**
 * 配置自定义log注解类
 * @author crush
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //生成文档
public @interface MyLog {
    /** 操作     */
    String operation () default "";
    /** 类型 */
    int type ();
}
