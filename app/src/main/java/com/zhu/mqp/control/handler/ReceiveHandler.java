package com.zhu.mqp.control.handler;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.toprand.netty_server.model.ReceiveType;
import com.zhu.mqp.R;

import java.lang.ref.WeakReference;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/24/2021
 * Description: 消息刷新主线程
 * Author: zl
 */
public class ReceiveHandler <T extends Activity> extends Handler {

    private final NotificationManager notificationManager;

    private final WeakReference<T> weak;

    public ReceiveHandler(T activity){
        this.weak = new WeakReference<T>(activity);
        //创建一个通知管理器
        notificationManager= (NotificationManager) weak.get().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Log.e("client_service",">" + msg.obj.toString());
        if(msg.what == ReceiveType.Type_Notification){
            if(weak.get() != null && notificationManager != null){
                Notification notification = new Notification.Builder(weak.get())
                        .setContentTitle("新消息提醒")
                        .setContentText(">>>>" + msg.obj.toString())
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(weak.get().getResources(),R.mipmap.ic_launcher))
                        .build();
                notificationManager.notify(0,notification);
            }
        }
    }
}
