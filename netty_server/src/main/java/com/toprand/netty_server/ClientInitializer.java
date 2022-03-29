package com.toprand.netty_server;

import com.toprand.netty_server.handler.ClientHandler;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description:
 * Author: zl
 */
public class ClientInitializer<T> extends ChannelInitializer<SocketChannel> {

    private ReceiveData<T> receiveData;

    public ClientInitializer(ReceiveData<T> receiveData){
        this.receiveData = receiveData;
    }

    @Override
    protected void initChannel(SocketChannel ch) {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(
                new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()),
                new IdleStateHandler(60,60,60, TimeUnit.SECONDS),
                new StringDecoder(CharsetUtil.UTF_8),
                new StringEncoder(CharsetUtil.UTF_8),
                new ClientHandler(receiveData)
        );

    }
}
