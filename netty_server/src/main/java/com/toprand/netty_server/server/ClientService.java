package com.toprand.netty_server.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;

import com.toprand.netty_server.NettyClient;
import com.toprand.netty_server.data.ReceiveClient;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description: 客户端服务，后台长连接
 * Author: zl
 */
public class ClientService extends Service {

    private Handler handler;

    private ReceiveClient receiveData;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
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
            public void onReceive(String msg) {
                if (receiveData != null) {
                    Message message = handler.obtainMessage();
                    message.what = 200;
                    message.obj = msg;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ClientReceive();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setReceiveData(ReceiveClient receiveData) {
        this.receiveData = receiveData;
    }

    public class ClientReceive extends Binder {
        public ClientService getServer() {
            return ClientService.this;
        }
    }

    private IBinder binder = new IResultReceiver.Stub() {
        @Override
        public void send(int i, Bundle bundle) throws RemoteException {

        }
    };

}
