package com.toprand.chainload.connect;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/29
 * Description:
 * Author: zl
 */
public interface Emitter<T> {

    void onSubscribe();

    void onNext(T t);

    void onError(Throwable e);

}
