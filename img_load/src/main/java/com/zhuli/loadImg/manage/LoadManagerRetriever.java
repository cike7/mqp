package com.zhuli.loadImg.manage;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.zhuli.loadImg.LoadManage;

import org.jetbrains.annotations.NotNull;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description:
 * Author: zl
 */
public class LoadManagerRetriever {

    private LoadManage mLoadManage;

    private Context context;

    public LoadManage get(View view) {

        if (mLoadManage == null) {
            if (context == null) {
                context = view.getContext();
            }
            mLoadManage = new LoadManage(null);

        } else {

//            Log.e("load_image", "get context:" + context.getClass().getName());
//            Log.e("load_image", "get view:" + view.getClass().getName());
//
//            if (!context.getClass().getName().equals(view.getClass().getName())) {
//                context = view.getContext();
//                mLoadManage = new LoadManage(null);
//            }
        }

        return mLoadManage;
    }


    public LoadManage get(Fragment fragment) {

        if (mLoadManage == null) {
            if (context == null) {
                context = fragment.getContext();
            }
            mLoadManage = new LoadManage(fragment);

        } else {
            if (!context.getClass().getName().equals(fragment.getClass().getName())) {
                context = fragment.getContext();
                mLoadManage = new LoadManage(fragment);
            }
        }

        return mLoadManage;
    }


    public LoadManage get(Activity activity) {
        if (activity instanceof FragmentActivity) {
            if (mLoadManage == null) {
                if (context == null) {
                    context = activity;
                }
                mLoadManage = new LoadManage((FragmentActivity) activity);

            } else {
                if (!context.getClass().getName().equals(activity.getClass().getName())) {
                    context = activity;
                    mLoadManage = new LoadManage((FragmentActivity) activity);
                }
            }

            return mLoadManage;
        }
        return new LoadManage(new LifecycleOwner() {
            @NonNull
            @NotNull
            @Override
            public Lifecycle getLifecycle() {
                return null;
            }
        });
    }

}
