package com.zhu.mqp.control.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tp.netty_client.NettyClient;
import com.tp.netty_client.data.ReceiveClient;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description: 客户端服务，后台长连接
 * Author: zl
 */
public class ClientService extends Service {

    private Handler handler;

    private ReceiveClient<String> receiveData;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 200) {
                    receiveData.onReceive((String) msg.obj);
                } else if (msg.what == 404) {
                    receiveData.onError(new ArithmeticException((String) msg.obj));
                }
            }
        };

        NettyClient.Instance.init("192.168.1.147", 20530).setReceiveDataListener(new ReceiveClient() {
            @Override
            public void onError(Exception e) {
                if (receiveData != null) {
                    Message message = handler.obtainMessage();
                    message.what = 404;
                    message.obj = e.getMessage();
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onReceive(Object msg) {
                if (receiveData != null) {
                    Message message = handler.obtainMessage();
                    message.what = 200;
                    message.obj = msg;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ClientReceive();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setReceiveData(ReceiveClient<String> receiveData) {
        this.receiveData = receiveData;
    }

    public class ClientReceive extends Binder {
        public ClientService getServer() {
            return ClientService.this;
        }
    }

}
