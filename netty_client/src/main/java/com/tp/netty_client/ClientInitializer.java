package com.tp.netty_client;

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
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private ReceiveData receiveData;

    public ClientInitializer(ReceiveData receiveData){
        this.receiveData = receiveData;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(
                new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()),
                new IdleStateHandler(60,10,10, TimeUnit.SECONDS),
                new StringDecoder(CharsetUtil.UTF_8),
                new StringEncoder(CharsetUtil.UTF_8),
                new ClientHandler(receiveData)
        );

    }
}
