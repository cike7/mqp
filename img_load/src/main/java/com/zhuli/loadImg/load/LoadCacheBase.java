package com.zhuli.loadImg.load;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.List;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description:
 * Author: zl
 */
public abstract class LoadCacheBase implements LoadRequest {

    protected LruCache<String, Bitmap> bitmapPool;

    private List<String> temporary;
    private List<String> actives;

    public LoadCacheBase(LruCache<String, Bitmap> bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Bitmap load(String key) {
        if (bitmapPool == null) return null;
        return bitmapPool.get(key);
    }

    public void put(String key, Bitmap bitmap) {
        if (bitmapPool == null) return;
        bitmapPool.put(key, bitmap);
    }

    private void remove(String key){
        if (bitmapPool == null) return;
        bitmapPool.remove(key);
    }

}