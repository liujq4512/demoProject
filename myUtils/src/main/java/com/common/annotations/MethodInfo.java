package com.common.annotations;

import java.lang.annotation.*;

/**
 * Created by liujq on 17-10-17.
 */

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "liujq";
    int version() default 1;
    String comments();
}
