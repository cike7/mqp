package com.toprand.chainload.observable;

import android.util.Log;

import com.toprand.chainload.base.AbstractObservableWithUpstream;
import com.toprand.chainload.connect.ObservableSource;
import com.toprand.chainload.connect.Observer;
import com.toprand.chainload.observer.SubscribeOnObserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 被观察者线程切换
 * Author: zl
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpstream {

    //线程池
    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    public ObservableSubscribeOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        Log.e(">>>>", "消息通知  4");

        SubscribeOnObserver<T> parent = new SubscribeOnObserver<T>(observer);
        observer.onSubscribe();
        //切换子线程执行
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.e(">>>>","1观察者" + Thread.currentThread().getName());
                source.subscribe(parent);
            }
        });

    }

}
