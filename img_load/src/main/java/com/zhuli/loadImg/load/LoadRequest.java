package com.zhuli.loadImg.load;

import android.graphics.Bitmap;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/15
 * Description: 加载请求
 * Author: zl
 */
public interface LoadRequest {

    Bitmap load(String key);

}
