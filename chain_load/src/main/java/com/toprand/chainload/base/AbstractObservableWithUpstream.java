package com.toprand.chainload.base;

import com.toprand.chainload.Observable;
import com.toprand.chainload.connect.ObservableSource;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 具体被观察者
 * Author: zl
 */
public abstract class AbstractObservableWithUpstream<T,R> extends Observable<R> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpstream(ObservableSource<T> source) {
        this.source = source;
    }

}
