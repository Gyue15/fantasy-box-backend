package cn.edu.nju.fantasybox.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于注明是否需要验证用户权限
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    /**
     * 默认需要验证
     * @return 是否需要验证
     */
    boolean required() default true;
}
