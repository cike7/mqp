package com.toprand.chainload.connect;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 抽象观察者
 * Author: zl
 */
public interface Observer<T> {
    /**
     * 订阅成功
     */
    void onSubscribe();
    /**
     *
     * @param t
     */
    void onNext(T t);

    /**
     * 订阅出错
     */
    void onError(Throwable e);

    /**
     * 全部完成
     */
    void onComplete();

}
