package com.wkw2.cache.annotation;

import java.lang.annotation.*;

/**
 * 缓存拦截器
 * @author wkw2
 * @since 0.0.1
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheInterceptor {

    /**
     * 通用拦截器
     *
     * 1. 耗时统计
     * 2. 慢日志统计
     *
     * etc.
     * @return 默认开启
     * @since 0.0.5
     */
    boolean common() default true;

    /**
     * 是否启用刷新
     * @return false
     * @since 0.0.5
     */
    boolean refresh() default false;

}
