package com.zhu.processortest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/19
 * Description:
 * Author: zl
 */
//绑定目标
@Target(ElementType.TYPE)
//生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoSetTarget {
    int value() default 0;
}
