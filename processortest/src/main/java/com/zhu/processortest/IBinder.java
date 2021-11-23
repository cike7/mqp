package com.zhu.processortest;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description: 绑定activity
 * Author: zl
 */
public interface IBinder<T> {
    void bind(T target);
}
