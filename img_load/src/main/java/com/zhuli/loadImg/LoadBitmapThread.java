package com.zhuli.loadImg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/10/8
 * Description: 加载url生成bitmap
 * Author: zl
 */
public class LoadBitmapThread extends Thread {

    private String cardUrl;

    private TaskFinish task;

    public LoadBitmapThread(String url) {
        this.cardUrl = url;
    }

    @Override
    public void run() {
        super.run();
        if (task != null) {
            try {
                Bitmap bitmap = getImageBitmap(cardUrl);
                if (bitmap != null){
                    task.onSuccess(bitmap);
                }else {
                    task.onFail("加载图片错误");
                }
            } catch (Exception e) {
                task.onFail("加载图片错误");
            }
        }
        Log.e("load_image", "----aaa----执行任务线程" + getId() + "，thread:" + Thread.currentThread().getName());
    }

    public String getCardUrl() {
        return cardUrl;
    }

    /**
     * 加载网络url转换Bitmap
     * @param url
     * @return
     */
    private Bitmap getImageBitmap(String url) {
        Bitmap bitmap = null;
        try {
            URL imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 任务完成返回当前线程
     */
    public interface TaskFinish {

        void onSuccess(Bitmap thread);

        void onFail(String error);

    }

    public void setTask(TaskFinish task) {
        this.task = task;
    }


}
