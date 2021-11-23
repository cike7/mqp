package com.tp.netty_client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient extends Thread {

    private Build build;

    private NettyClient(Build build){
        this.build = build;
    }

    @Override
    public void run() {
        super.run();

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer(build.receiveData));

            bootstrap.connect(build.host,build.port).sync().channel();

            build.receiveData.onReceive("连接成功");

        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class Build{

        private String host = "127.0.0.1";

        private int port = 20530;

        private ReceiveData receiveData;

        public Build(){ }

        public Build(String host,int port){
            this.host = host;
            this.port = port;
        }

        public Build setOnReceive(ReceiveData receiveData){
            this.receiveData = receiveData;
            return this;
        }

        public NettyClient create(){
            return new NettyClient(this);
        }

    }


//    public static void main(String[] args) {
//
//        NettyClient client = new NettyClient.Build("127.0.0.1",20530).setOnReceive(new ReceiveData() {
//            @Override
//            public void onReceive(Object msg) {
//                System.out.println(">" + msg.toString());
//            }
//        }).create();
//
//        client.start();
//    }

}