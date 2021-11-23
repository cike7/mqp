package com.zhuli.loadImg.util;

import com.zhuli.loadImg.signtrue.EmptySignature;
import com.zhuli.loadImg.signtrue.GlideUrl;

import java.security.MessageDigest;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/18
 * Description:
 * Author: zl
 */
public class GetKey {

    /**
     * 缓存存储路径：/data/data/your_packagexxxxxxx/cache/image_manager_disk_cache
     *
     * @param url 图片地址url
     * @return 返回图片在磁盘缓存的key值
     */
    public static String getSafeKey(String url) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            EmptySignature signature = EmptySignature.obtain();
            signature.updateDiskCacheKey(messageDigest);
            new GlideUrl(url).updateDiskCacheKey(messageDigest);
            String safeKey = Util.sha256BytesToHex(messageDigest.digest());
            return safeKey;
        } catch (Exception e) {

        }
        return null;
    }
}
