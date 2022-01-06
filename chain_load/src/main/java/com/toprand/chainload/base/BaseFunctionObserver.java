package com.toprand.chainload.base;

import com.toprand.chainload.connect.Observer;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 抽象观察者
 * Author: zl
 */
public abstract class BaseFunctionObserver<T,R> implements Observer<T> {

    protected final Observer<R> observer;

    public BaseFunctionObserver(Observer<R> observer) {
        this.observer = observer;
    }

    /**
     * 订阅成功
     */
    @Override
    public void onSubscribe(){
        observer.onSubscribe();
    }

    /**
     * 订阅出错
     */
    @Override
    public void onError(Throwable e){
        observer.onError(e);
    }

    /**
     * 全部完成
     */
    @Override
    public void onComplete(){
        observer.onComplete();
    }

}
