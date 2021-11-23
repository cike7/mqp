package com.zhu.mqp.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zhu.mqp.data.model.ChatMessageModel;

import java.util.ArrayList;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 加载历史聊天记录
 * Author: zl
 */
public class ChatViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ChatMessageModel>> mMessages;

    public ChatViewModel() {
        mMessages = new MutableLiveData<>();
        mMessages.setValue(loadHistoryMessage());
    }

    //加载历史消息
    private ArrayList<ChatMessageModel> loadHistoryMessage(){

        ArrayList<ChatMessageModel> messages = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            ChatMessageModel model = new ChatMessageModel();
            model.setHeadUrl("");
            model.setMsgName("这是" + i);
            model.setMsgContent("+" + System.currentTimeMillis());
            model.setMsgTime("2021.9." + i);
            messages.add(model);
        }

        return messages;

    }

    /**
     * 获取历史记录
     * @return
     */
    public LiveData<ArrayList<ChatMessageModel>> getHistoryMessage(){
        return mMessages;
    }


}