package com.zhuli.loadImg.load;

import androidx.annotation.NonNull;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description:
 * Author: zl
 */
public interface Key {
    String STRING_CHARSET_NAME = "UTF-8";
    Charset CHARSET = Charset.forName(STRING_CHARSET_NAME);

    /**
     * Adds all uniquely identifying information to the given digest.
     *
     * <p>Note - Using {@link java.security.MessageDigest#reset()} inside of this method will result
     * in undefined behavior.
     */
    void updateDiskCacheKey(@NonNull MessageDigest messageDigest);

    /**
     * For caching to work correctly, implementations <em>must</em> implement this method and {@link
     * #hashCode()}.
     */
    @Override
    boolean equals(Object o);

    /**
     * For caching to work correctly, implementations <em>must</em> implement this method and {@link
     * #equals(Object)}.
     */
    @Override
    int hashCode();
}
