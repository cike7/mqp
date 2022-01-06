package com.toprand.chainload.connect;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/29
 * Description: 实现转换与传递的功能
 * Author: zl
 */
public interface Function<T,R> {

    /**
     * 对输入值运用一些计算或者检验，得到结果往下传递
     * @param t
     * @return
     */
    R next(T t);
}
