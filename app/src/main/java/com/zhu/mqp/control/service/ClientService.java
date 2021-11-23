package com.zhu.mqp.control.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.tp.netty_client.NettyClient;
import com.tp.netty_client.ReceiveData;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description: 客户端服务，后台长连接
 * Author: zl
 */
public class ClientService extends Service {

    private ReceiveData receiveData;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        NettyClient client = new NettyClient.Build("192.168.2.244",20530).setOnReceive(new ReceiveData() {
            @Override
            public void onReceive(Object msg) {
                if(receiveData != null){
                    receiveData.onReceive(msg);
                }
            }
        }).create();

        client.start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ClientReceive();
    }

    public void setReceiveData(ReceiveData receiveData){
        this.receiveData = receiveData;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ClientReceive extends Binder{
        public ClientService getServer(){
            return ClientService.this;
        }
    }

}
