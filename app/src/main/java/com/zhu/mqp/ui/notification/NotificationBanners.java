package com.zhu.mqp.ui.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.zhu.mqp.R;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/25/2021
 * Description: 横幅通知
 * Author: zl
 */
public class NotificationBanners {

    // 唯一的通知通道的id.
    //前台（横幅）通知
    public static final String notificationChannelId = "notification_channel_id_01";

    //通知管理器
    private NotificationManager notificationManager;

    private Activity activity;

    /**
     * 前台（横幅）通知
     */
    private Notification createForegroundNotification() {

        notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        // Android8.0以上的系统，新建消息通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //用户可见的通道名称
            String channelName = "Foreground Service Notification";
            //通道的重要程度
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, channelName, importance);
            notificationChannel.setDescription("Channel description");
            //LED灯
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            //震动
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            // 声音(没有声音)
            notificationChannel.setSound(null, null);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity.getApplicationContext(), notificationChannelId);
        builder.setCategory(Notification.CATEGORY_RECOMMENDATION);
        //通知小图标
        builder.setSmallIcon(R.drawable.ic_baseline_arrow_back_24);
        //通知标题
        builder.setContentTitle("NotificationTitle");
        //通知内容
        builder.setContentText("ContentText");
        //设定通知显示的时间
        builder.setWhen(System.currentTimeMillis());
//        //设定启动的内容
//        Intent activityIntent = new Intent(this, ForegroundSerActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setFullScreenIntent(pendingIntent, false);
        // sdk5.0以上使用
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        //创建通知并返回
        return builder.build();
    }


//    /**
//     * 跳转横幅通知权限,详细channelId授予权限
//     */
//    private void getHangUpPermission(String channelId) {
//        Intent intent = new Intent();
//        if (Build.VERSION.SDK_INT >= 26) {
//            // android8.0单个channelid设置
//            intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
//        } else {
//            // android 5.0以上一起设置
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            intent.putExtra("app_package", getPackageName());
//            intent.putExtra("app_uid", getApplicationInfo().uid);
//        }
//        startActivity(intent);
//    }

}
