package com.zhuli.loadImg.load;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description: 磁盘加载
 * Author: zl
 */
public class DiskLoad extends LoadCacheBase {

    public DiskLoad(LruCache<String, Bitmap> bitmapPool) {
        super(bitmapPool);
    }

    public void put(String key,Bitmap bitmap){

    }
}
