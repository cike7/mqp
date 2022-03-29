package com.toprand.netty_server.handler;

import com.toprand.netty_server.ReceiveData;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2022/1/6
 * Description:
 * Author: zl
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> implements ChannelHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    public <T> ClientHandler(ReceiveData<T> receiveData) {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

}
