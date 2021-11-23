package com.zhuli.loadImg.signtrue;

import java.util.Collections;
import java.util.Map;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/18
 * Description:
 * Author: zl
 */
public interface Headers {
    /**
     * An empty Headers object that can be used if users don't want to provide headers.
     *
     * @deprecated Use {@link #DEFAULT} instead.
     */
    @Deprecated
    Headers NONE =
            new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    return Collections.emptyMap();
                }
            };

    /**
     * A Headers object containing reasonable defaults that should be used when users don't want to
     * provide their own headers.
     */
    Headers DEFAULT = new LazyHeaders.Builder().build();

    /** Returns a non-null map containing a set of headers to apply to an http request. */
    Map<String, String> getHeaders();
}
