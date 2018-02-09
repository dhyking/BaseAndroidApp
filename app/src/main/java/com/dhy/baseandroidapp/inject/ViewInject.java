package com.dhy.baseandroidapp.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dhy on 2018/1/19.
 */

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    int value() default -1;
}
