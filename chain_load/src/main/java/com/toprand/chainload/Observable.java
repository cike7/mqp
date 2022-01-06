package com.toprand.chainload;

import com.toprand.chainload.connect.Function;
import com.toprand.chainload.connect.ObservableOnSubscribe;
import com.toprand.chainload.connect.ObservableSource;
import com.toprand.chainload.connect.Observer;
import com.toprand.chainload.observable.ObservableCreate;
import com.toprand.chainload.observable.ObservableMap;
import com.toprand.chainload.observable.ObservableObserveOn;
import com.toprand.chainload.observable.ObservableSubscribeOn;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 具体被观察者
 * Author: zl
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribe(Observer<T> observer) {
        //让订阅功能子类去实现
        subscribeActual(observer);
    }

    /**
     * 实际订阅实现
     *
     * @param observer
     */
    protected abstract void subscribeActual(Observer<T> observer);

    /**
     * 连接被观察者
     *
     * @param disk
     * @return
     */
    public static <T> Observable<T> concat(Observable<T>... disk) {
        return null;
    }

    /**
     * 创建被观察者
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate(source);
    }

    /**
     * 添加检验条件
     *
     * @param function
     * @return
     */
    public <R> Observable<R> map(Function<T, R> function) {
        return new ObservableMap<>(this, function);
    }

    /**
     * 被观察者线程切换
     *
     * @return
     */
    public final Observable<T> subscribeOn() {
        return new ObservableSubscribeOn<>(this);
    }

    /**
     * 观察者线程切换
     *
     * @return
     */
    public final Observable<T> observeOn() {
        return new ObservableObserveOn<>(this);
    }

    /**
     * 截取第一个元素，跳过后续操作
     *
     * @return
     */
    public final Observable<T> firstElement() {
        return null;
    }

}
