package com.unique.app.community.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetPermission {

    int value() default 0;
}
