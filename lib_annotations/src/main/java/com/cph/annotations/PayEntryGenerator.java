package com.cph.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CPH on 2020/1/28
 */
@Target(ElementType.TYPE)//用在类上面
@Retention(RetentionPolicy.SOURCE)//源码阶段处理
public @interface PayEntryGenerator {

    String packageName();
    Class<?> payEntryTemplate();
}
