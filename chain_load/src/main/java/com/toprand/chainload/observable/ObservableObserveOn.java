package com.toprand.chainload.observable;


import android.util.Log;

import com.toprand.chainload.base.AbstractObservableWithUpstream;
import com.toprand.chainload.connect.ObservableSource;
import com.toprand.chainload.connect.Observer;
import com.toprand.chainload.observer.SubscribeOnObserver;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 观察者线程切换
 * Author: zl
 */
public class ObservableObserveOn<T> extends AbstractObservableWithUpstream {

    public ObservableObserveOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        Log.e(">>>>", "消息通知  3");
        SubscribeOnObserver<T> parent = new SubscribeOnObserver<>(observer);
        observer.onSubscribe();
        source.subscribe(parent);
    }

}
