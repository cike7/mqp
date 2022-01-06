package com.toprand.chainload.connect;

import com.toprand.chainload.connect.Emitter;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/29
 * Description:
 * Author: zl
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> parent);
}
