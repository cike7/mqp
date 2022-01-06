package com.toprand.chainload.observer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.toprand.chainload.connect.Observer;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/29
 * Description: 订阅被观察者切换主线程
 * Author: zl
 */
public class SubscribeOnObserver<T> implements Observer<T> {

    private final Observer<T> observer;
    private final Handler handler;

    public SubscribeOnObserver(Observer<T> observer) {
        this.observer = observer;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onSubscribe() {
        this.observer.onSubscribe();
    }

    @Override
    public void onNext(T t) {
        Log.e(">>>>", "被观察者" + t + ",Thread:" + Thread.currentThread().getName());
        handler.post(new Runnable() {
            @Override
            public void run() {
                observer.onNext(t);
            }
        });
    }

    @Override
    public void onError(Throwable e) {
        this.observer.onError(e);
    }

    @Override
    public void onComplete() {
        this.observer.onComplete();
    }
}