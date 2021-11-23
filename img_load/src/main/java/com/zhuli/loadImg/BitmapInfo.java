package com.zhuli.loadImg;

import android.graphics.Bitmap;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/10/8
 * Description:
 * Author: zl
 */
public class BitmapInfo {

    private String id;

    private Bitmap bitmap;

    public BitmapInfo(String id, Bitmap bitmap) {
        this.id = id;
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
