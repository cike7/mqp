package com.tp.netty_client;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description: 接收消息
 * Author: zl
 */
public interface ReceiveData<T> {

    /**
     * 连接成功
     */
    void onSuccess(SendDate<T> sendDate);

    /**
     * 连接失败
     */
    void onError(Exception e);

    /**
     * 收到消息时
     * @param msg
     */
    void onReceive(T msg);

}
