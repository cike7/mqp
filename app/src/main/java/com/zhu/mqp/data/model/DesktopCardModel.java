package com.zhu.mqp.data.model;

import androidx.annotation.DrawableRes;

import com.zhu.mqp.R;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/6
 * Description: 桌面卡片
 * Author: zl
 */
public class DesktopCardModel {

    //id
    private long id;

    //图片url
    private String cardUrl;

    //点击数
    private float check;

    //评论
    private float comment;

    //标题
    private String title;

    //名字
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCheck(float check) {
        this.check = check;
    }

    public float getCheck() {
        return check;
    }

    public void setComment(float comment) {
        this.comment = comment;
    }

    public float getComment() {
        return comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 默认图片id
     * @return
     */
    public @DrawableRes int getCardImgId() {
        return R.mipmap.img_lotus;
    }
}
