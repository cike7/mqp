package com.zhu.mqp.ui.listener;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description: 更新消息
 * Author: zl
 */
public interface UpdateMessage<T> {

    /**
     * 收到新消息
     * @param msg
     */
    void receive(T msg);
}
