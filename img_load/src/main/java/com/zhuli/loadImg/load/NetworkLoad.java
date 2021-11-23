package com.zhuli.loadImg.load;

import android.graphics.Bitmap;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.zhuli.loadImg.LoadBitmapThread;
import com.zhuli.loadImg.util.GetKey;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description: 网络加载
 * Author: zl
 */
public class NetworkLoad extends LoadCacheBase implements LoadBitmapThread.TaskFinish {

    //加载队列线程
    private ArrayMap<String, ImageView> loadThreadPool;

    //真在运行队列
    private LinkedBlockingQueue<ImageView> runThread;

    public NetworkLoad(LruCache<String, Bitmap> bitmapPool) {
        super(bitmapPool);
        loadThreadPool = new ArrayMap<>();
        runThread = new LinkedBlockingQueue<>();
    }

    /**
     * 网络请求
     *
     * @param url
     * @param image
     */
    public void load(String url, ImageView image) {

        if (runThread.size() > 0) {

            containsTask(image);
            loadThreadPool.put(url, image);

            Log.e("load_image", "放入等待队列" + loadThreadPool.size());
        } else {

            runThread.offer(image);
            LoadBitmapThread itemThread = new LoadBitmapThread(url);
            itemThread.setTask(this);
            itemThread.start();

            Log.e("load_image", "加入执行队列" + runThread.size());
        }

    }


    /**
     * 任务完成
     */
    @Override
    public void onSuccess(Bitmap bitmap) {
        Log.e("load_image", "任务执行完毕" + " 剩余任务数量：" + loadThreadPool.size());
        ImageView image = runThread.poll();
        if (image != null) {
            if (!loadThreadPool.containsValue(image)) {
                image.setImageBitmap(bitmap);
            }
        }
        inspectNext(bitmap);
    }

    /**
     * 任务失败
     */
    @Override
    public void onFail(String errorUrl) {
        Log.e("load_image", "网络加载图片失败，" + errorUrl + " ，剩余任务数量：" + loadThreadPool.size());
        runThread.poll();
        inspectNext(null);
    }

    /**
     * 检查下一步
     */
    private void inspectNext(Bitmap bitmap) {
        if (loadThreadPool.size() > 0) {
            String url = loadThreadPool.keySet().iterator().next();
            if (loadThreadPool.get(url) != null) {
                runThread.offer(loadThreadPool.get(url));
                LoadBitmapThread itemThread = new LoadBitmapThread(url);
                itemThread.setTask(this);
                itemThread.start();

                if(bitmap != null){
                    bitmapPool.put(GetKey.getSafeKey(url),bitmap);
                }
                
                loadThreadPool.remove(url);
            }
        }
    }


    /**
     * 检查是否存在此任务
     */
    private void containsTask(ImageView image){
        //检查是否重复加载url，如果存在则淘汰之前的请求任务
        if (loadThreadPool.containsValue(image)) {
            for (int i = 0; i < loadThreadPool.values().size(); i++) {
                if(loadThreadPool.valueAt(i) == image){
                    loadThreadPool.removeAt(i);
                    Log.e("load_image", "淘汰之前的rul");
                    break;
                }
            }
        }
    }

    @Override
    protected Bitmap getBitmap() {
        return null;
    }

}
