package com.zhuli.loadImg.load;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description:
 * Author: zl
 */
public abstract class LoadCacheBase implements LoadRequest {

    protected LruCache<String, Bitmap> bitmapPool;

    public LoadCacheBase(LruCache<String, Bitmap> bitmapPool){
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Bitmap load(String key) {
        return getBitmap();
    }

    protected abstract Bitmap getBitmap();
}
