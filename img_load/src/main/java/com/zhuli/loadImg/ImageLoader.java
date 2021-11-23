package com.zhuli.loadImg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;

import com.zhuli.loadImg.manage.LoadManagerRetriever;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/28
 * Description: 图片加载器
 * Author: zl
 */
public class ImageLoader{

    private static ImageLoader instance;

    private LoadManagerRetriever loadManagerRetriever;

    /**
     * 同步生命周期
     * @param fragment
     * @return
     */
    public static LoadManage with(Fragment fragment){
        return getRetriever(fragment.getContext()).get(fragment);
    }

    /**
     * 同步生命周期
     * @param activity
     * @return
     */
    public static LoadManage with(Activity activity){
        return getRetriever(activity).get(activity);
    }

    /**
     * 同步生命周期
     * @param view
     * @return
     */
    public static LoadManage with(View view){
        return getRetriever(view.getContext()).get(view);
    }

    /**
     * 检查content
     * @param context
     * @return
     */
    @SuppressLint("RestrictedApi")
    private static LoadManagerRetriever getRetriever(@Nullable Context context) {
        Preconditions.checkNotNull(
                context,
                "您不能在尚未附加的 View 或 Fragment 上启动加载.");
        return ImageLoader.get(context).getLoadManagerRetriever();

    }

    @SuppressLint("RestrictedApi")
    private static ImageLoader get(Context context) {
        Preconditions.checkNotNull(
                context,
                "您不能在尚未附加的 View 或 Fragment 上启动加载.");

        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }

        if(instance.loadManagerRetriever == null){
            instance.loadManagerRetriever = new LoadManagerRetriever();
        }

        return instance;

    }


    private LoadManagerRetriever getLoadManagerRetriever() {
        return loadManagerRetriever;
    }


}
