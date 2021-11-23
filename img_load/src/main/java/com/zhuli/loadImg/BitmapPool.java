package com.zhuli.loadImg;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/10/8
 * Description: Bitmap存储池
 * Author: zl
 */
public class BitmapPool {

    private List<BitmapInfo> bitmapInfo;

    public BitmapPool() {
        bitmapInfo = new ArrayList<>();
    }

    public void add(String url, int position, Bitmap bitmap) {
        String id = combinationId(url, position);
        BitmapInfo info = new BitmapInfo(id, bitmap);
        bitmapInfo.add(info);
    }

    public int containId(String url, int position) {
        String id = combinationId(url, position);
        for (int i = 0; i < bitmapInfo.size(); i++) {
            if (bitmapInfo.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找相同的url
     * @param url
     * @return
     */
    public Bitmap containUrl(String url) {
        for (int i = 0; i < bitmapInfo.size(); i++) {
            try {
                String idJson = bitmapInfo.get(i).getId();
                JSONObject jsonObject = new JSONObject(idJson);
                String itemUrl = jsonObject.getString("url");
                if (url.equals(itemUrl)) {
                    return bitmapInfo.get(i).getBitmap();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bitmap getBitmap(int index) {
        if (index >= 0 && index < bitmapInfo.size()) {
            return bitmapInfo.get(index).getBitmap();
        } else {
            throw new IllegalArgumentException("索引不存在");
        }
    }

    /**
     * 组合id
     *
     * @param url
     * @param position
     * @return
     */
    private String combinationId(String url, int position) {
        return "{\"position\":" + position + ",\"url\":\"" + url + "\"}";
    }
}
