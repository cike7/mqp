package com.zhu.annotation;

import android.app.Activity;

import com.zhu.processortest.IBinder;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description:
 * Author: zl
 */
public class ButterKnifeProcess {

    /**
     * 绑定Activity
     */
    public static void bind(Activity activity) {

        String className = activity.getClass().getName() + "_ViewBinding";

        try {
            Class<?> mClass = Class.forName(className);

            IBinder binder = (IBinder) mClass.newInstance();

            binder.bind(activity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//        Class annotationParent = activity.getClass();
//        //变量
//        Field[] fields = annotationParent.getDeclaredFields();
//        //方法
//        Method[] methods = annotationParent.getDeclaredMethods();

//        for (Field field:fields) {
//            Log.e("home","field name = " + field.getName());
//        }
//
    // 找到类里面所有的方法
//        for (Method method : methods) {
//
//            //找到添加了OnClick注解的方法
//            BindView clickMethod = method.getAnnotation(BindView.class);
//
//            Log.e("home","bindView id = " + clickMethod.value());
//
//            if (clickMethod != null && clickMethod.value().length != 0) {
//                for (int id : clickMethod.value()) {
//                    final View view = activity.findViewById(id);
//                    view.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            try {
//                                method.invoke(activity, view);
//                            } catch (IllegalAccessException | InvocationTargetException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
//            }
//
//        }

}
