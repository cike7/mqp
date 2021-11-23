package com.zhu.processortest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description: 根据id绑定指定的视图
 * Author: zl
 */
//绑定目标
@Target(ElementType.FIELD)
//生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int value();
}