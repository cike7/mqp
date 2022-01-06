package com.tp.netty_client;

import com.tp.netty_client.data.ReceiveClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient extends Thread implements ReceiveData {

    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    private ReceiveClient receiveData;

    private String host = "103.46.128.21";

    private int port = 80;

    private NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer(this));

            bootstrap.connect(host, port).sync().channel();

        } catch (InterruptedException e) {
            receiveData.onError(e);
        }
    }

    /**
     * set receive data listener
     *
     * @param receiveData
     * @return
     */
    public NettyClient setReceiveDataListener(ReceiveClient receiveData) {
        this.receiveData = receiveData;
        return this;
    }

    /**
     * send message
     *
     * @param msg
     */
    public void send(String msg) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                receiveData.getSendServer().send(msg);
            }
        });
    }

    /**
     * close
     */
    public void close() {
        receiveData.getSendServer().onError(new ArithmeticException("close server"));
        receiveData = null;
        interrupt();
    }

    @Override
    public void onSuccess(SendDate sendDate) {
        if (receiveData != null) {
            receiveData.onSuccess(sendDate);
        }
    }

    @Override
    public void onError(Exception e) {
        if (receiveData != null) {
            receiveData.onError(e);
        }
    }

    @Override
    public void onReceive(Object msg) {
        if (receiveData != null) {
            receiveData.onReceive(msg);
        }
    }


    public static class Instance {

        private static NettyClient client;

        public static NettyClient init(String host, int port) {
            if (client == null) {
                synchronized (Instance.class) {
                    if (client == null) {
                        client = new NettyClient(host, port);
                    }
                }
            }
            return client;
        }

        public static NettyClient get() {
            if (client == null) {
                throw new IllegalArgumentException("not in init");
            }
            return client;
        }
    }


}