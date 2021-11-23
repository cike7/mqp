package com.zhu.mqp.ui.item;

import com.zhu.mqp.ui.listener.UpdateMessage;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description: 抽象消息主题
 * Author: zl
 */
public interface MessageSubject<T> {

    /**
     * 注册观察者
     */
    void registerObserver(UpdateMessage observer);

    /**
     * 移除观察者
     */
    void removeObserver(UpdateMessage observer);

    /**
     * 通过索引移除观察者
     */
    void removeObserver(int position);

    /**
     * 通知指定观察者新消息
     * @param msg
     */
    void notifyObserver(int position,T msg);

    /**
     * 通知所有观察者新消息
     * @param msg
     */
    void notifyAllObserver(T msg);

}
