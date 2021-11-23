package com.tp.netty_client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description:
 * Author: zl
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    private ReceiveData<String> receiveData;

    public ClientHandler(ReceiveData receiveData){
        this.receiveData = receiveData;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if(receiveData != null){
            receiveData.onReceive(msg.toString());
        }
        System.out.println("2来自服务端消息" + msg.trim());
        ctx.channel().writeAndFlush("客户端响应" + "\r\n");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        System.out.println("来自客户端消息,用户" + ctx.channel().remoteAddress() + "长时间未响应");
    }
}
