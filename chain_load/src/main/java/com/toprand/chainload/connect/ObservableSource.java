package com.toprand.chainload.connect;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 抽象被观察者
 * Author: zl
 */
public interface ObservableSource<T> {

    /**
     * 订阅功能
     * @param observer
     */
    void subscribe(Observer<T> observer);

}
