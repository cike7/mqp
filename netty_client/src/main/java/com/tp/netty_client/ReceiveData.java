package com.tp.netty_client;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description: 接收消息
 * Author: zl
 */
public interface ReceiveData<T> {
    /**
     * 当收到消息时
     */
    void onReceive(T msg);
}
