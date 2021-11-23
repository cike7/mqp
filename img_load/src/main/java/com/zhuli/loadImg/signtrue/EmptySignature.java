package com.zhuli.loadImg.signtrue;

import androidx.annotation.NonNull;
import com.zhuli.loadImg.load.Key;
import java.security.MessageDigest;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/18
 * Description: 一个空键总是等于所有其他空键
 * Author: zl
 */
public final class EmptySignature implements Key {
    private static final EmptySignature EMPTY_KEY = new EmptySignature();

    @NonNull
    public static EmptySignature obtain() {
        return EMPTY_KEY;
    }

    private EmptySignature() {
        // Empty.
    }

    @Override
    public String toString() {
        return "EmptySignature";
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        // Do nothing.
    }
}
