package com.zhuli.loadImg;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.LifecycleOwner;

import com.zhuli.loadImg.load.DiskLoad;
import com.zhuli.loadImg.load.LoadCacheBase;
import com.zhuli.loadImg.load.NetworkLoad;
import com.zhuli.loadImg.load.RamLoad;
import com.zhuli.loadImg.manage.LifecycleListener;
import com.zhuli.loadImg.util.GetKey;

import java.util.ArrayList;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description:
 * Author: zl
 */
public class LoadManage implements LifecycleListener {

    //占位符
    private @DrawableRes
    int placeholder = 0;

    private boolean
            isLoadRam = true,
            isLoadDisk = true;

    private String nowUrl;

    private Bitmap nowBitmap;

    private LruCache<String, Bitmap> bitmapPool;

    //缓存加载
    private ArrayList<LoadCacheBase> mCacheLoad;

    private LoadCacheBase mRamLoad;

    private LoadCacheBase mDiskLoad;

    //网络加载
    private NetworkLoad mNetworkLoad;

    public LoadManage(LifecycleOwner lifecycle){

        if(lifecycle != null){
            lifecycle.getLifecycle().addObserver(this);
        }

        bitmapPool = new LruCache<String, Bitmap>(1024 * 1024 * 5){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 获取图片占用内存大小
                return value.getRowBytes() * value.getHeight();
            }
        };

        mCacheLoad = new ArrayList<>();
        mRamLoad = new RamLoad(bitmapPool);
        mDiskLoad = new DiskLoad(bitmapPool);
        mNetworkLoad = new NetworkLoad(bitmapPool);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    /**
     * 加载缓存
     * @param isLoadRam 默认开启
     * @return
     */
    public LoadManage loadRam(boolean isLoadRam){
        this.isLoadRam = isLoadRam;
        return this;
    }

    /**
     * 加载磁盘
     * @param isLoadDisk 默认开启
     * @return
     */
    public LoadManage loadDisk(boolean isLoadDisk){
        this.isLoadDisk = isLoadDisk;
        return this;
    }

    /**
     * 加载前占位符
     *
     * @param resId
     * @return
     */
    public LoadManage placeholder(@DrawableRes int resId) {
        placeholder = resId;
        return this;
    }

    /**
     * 加载url
     * @param cardUrl
     * @return
     */
    public LoadManage load(String cardUrl) {
        nowBitmap = inspect(GetKey.getSafeKey(cardUrl));
        if (nowBitmap == null){
            nowUrl = cardUrl;
        }
        return this;
    }

    /**
     * 设置目标图片
     *
     * @param image
     */
    public void setImageView(ImageView image) {

        if (nowBitmap != null) {
            image.setImageBitmap(nowBitmap);
            nowBitmap = null;
        } else {
            if (placeholder != 0) {
                image.setImageResource(placeholder);
            }
            mNetworkLoad.load(nowUrl,image);
        }
    }

    /**
     * 检测是否有缓存
     * @param key
     * @return
     */
    private Bitmap inspect(String key){
        if(mRamLoad.load(key) != null && isLoadRam){
            Log.e("load_image", "缓存中取得位图");
            return mRamLoad.load(key);
        }
        if(mDiskLoad.load(key) != null && isLoadDisk){
            Log.e("load_image", "磁盘中取得位图");
            return mDiskLoad.load(key);
        }
        return null;
    }

}
