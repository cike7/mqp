package com.zhu.mqp.ui.item;

import com.zhu.mqp.ui.listener.UpdateMessage;

import java.util.ArrayList;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description:
 * Author: zl
 */
public class ConcreteMessageSubject<T> implements MessageSubject<T> {

    private ArrayList<UpdateMessage> messages;

    public ConcreteMessageSubject(){
        messages = new ArrayList<>();
    }

    @Override
    public void registerObserver(UpdateMessage observer) {
        messages.add(observer);
    }

    @Override
    public void removeObserver(UpdateMessage observer) {
        messages.remove(observer);
    }

    @Override
    public void removeObserver(int position) {
        messages.remove(position);
    }

    @Override
    public void notifyObserver(int position,T msg) {
        messages.get(position).receive(msg);
    }


    @Override
    public void notifyAllObserver(T msg) {
        for (int i = 0; i < messages.size(); i++) {
            if(messages.get(i) != null){
                messages.get(i).receive(msg);
            }
        }
    }

}
