package com.zhu.mqp.data.model;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天消息列表元素
 * Author: zl
 */
public class ChatMessageModel {

    private String headUrl;

    private String msgName;

    private String msgTime;

    private String msgContent;

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgContent() {
        return msgContent;
    }


}
