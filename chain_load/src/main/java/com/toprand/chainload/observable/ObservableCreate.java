package com.toprand.chainload.observable;

import android.util.Log;

import com.toprand.chainload.Observable;
import com.toprand.chainload.connect.ObservableOnSubscribe;
import com.toprand.chainload.connect.Emitter;
import com.toprand.chainload.connect.Observer;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 创建被观察者
 * Author: zl
 */
public class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    /**
     * 实现消息通知
     *
     * @param observer
     */
    @Override
    protected void subscribeActual(Observer<T> observer) {
        Log.e(">>>>", "消息通知  1");
        //这里处理消息通知功能
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        observer.onSubscribe();
        //把消息发送器和被观察者绑定到一起，实现订阅功能
        source.subscribe(parent);

    }

    /**
     * 创建消息发射器
     * @param <T>
     */
    public static class CreateEmitter <T> implements Emitter<T> {

        private final Observer<T> observer;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
            this.observer.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            Log.e(">>>>", "A订阅内容：" + t + "isSuccess");
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            this.observer.onError(e);
        }

    }

}

