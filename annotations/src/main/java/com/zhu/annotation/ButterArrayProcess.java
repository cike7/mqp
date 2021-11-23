package com.zhu.annotation;

import com.zhu.processortest.IBinder;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description:
 * Author: zl
 */
public class ButterArrayProcess {

    /**
     * 绑定Activity
     */
    public static void bind(Object activity) {

        String className = activity.getClass().getName() + "_AutoArray";

        try {
            Class<?> mClass = Class.forName(className);

            IBinder binder = (IBinder) mClass.newInstance();

            binder.bind(activity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
