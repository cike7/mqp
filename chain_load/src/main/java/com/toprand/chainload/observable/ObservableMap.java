package com.toprand.chainload.observable;

import com.toprand.chainload.base.AbstractObservableWithUpstream;
import com.toprand.chainload.base.BaseFunctionObserver;
import com.toprand.chainload.connect.Function;
import com.toprand.chainload.connect.ObservableSource;
import com.toprand.chainload.connect.Observer;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/11/24
 * Description: 具体被观察者
 * Author: zl
 */
public class ObservableMap<T,R> extends AbstractObservableWithUpstream<T,R> {

    private final Function<T,R> function;

    public ObservableMap(ObservableSource<T> source, Function<T,R> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        source.subscribe(new MapObserver<>(observer,function));
    }

    /**
     * 检验方法，可以多层叠加
     * @param <T>
     * @param <R>
     */
    private static final class MapObserver<T,R> extends BaseFunctionObserver<T,R> {

        private final Function<T,R> function;

        public MapObserver(Observer<R> observer,Function<T,R> function) {
            super(observer);
            this.function = function;
        }

        @Override
        public void onNext(T t) {
            R apply = this.function.next(t);
            observer.onNext(apply);
        }
    }
}
