package com.toprand.netty_server.data;

import com.toprand.netty_server.NettyClient;
import com.toprand.netty_server.ReceiveData;
import com.toprand.netty_server.SendDate;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2022/1/6
 * Description:
 * Author: zl
 */
public class ReceiveClient implements ReceiveData<String> {

    @Override
    public void onSuccess(SendDate<String> sendDate) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onReceive(String msg) {

    }

    public NettyClient getSendServer() {
        return null;
    }
}
