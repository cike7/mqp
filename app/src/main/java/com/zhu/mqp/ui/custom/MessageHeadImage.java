package com.zhu.mqp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 消息头像
 * Author: zl
 */
public class MessageHeadImage extends androidx.appcompat.widget.AppCompatImageView {

    //新消息数量
    private int mMessageNumber = 100;

    //新消息通知红点
    private Paint mNewMsgPaint;

    //红点半径
    private final float radius = 18;

    //文本信息
    private Rect mTextRect;

    //文本画笔
    private Paint mTextPaint;

    public MessageHeadImage(@NonNull @NotNull Context context) {
        this(context,null);
    }

    public MessageHeadImage(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MessageHeadImage(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);

        mTextRect = new Rect();

        mNewMsgPaint = new Paint();
        mNewMsgPaint.setColor(Color.RED);
        mNewMsgPaint.setAntiAlias(true);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//
//        //重新设置布局大小
//        setMeasuredDimension(width - 30,height - 30);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mMessageNumber > 0){
            String msgText = mMessageNumber > 99 ? "99+" : mMessageNumber + "";
            mTextPaint.getTextBounds(msgText,0,msgText.length(),mTextRect);
            canvas.drawCircle(getWidth() - radius,radius,radius,mNewMsgPaint);
            canvas.drawText(msgText,getWidth() - radius - mTextRect.width() * 0.5f,radius / 2 + mTextRect.height(),mTextPaint);
        }
    }

    public void setMessageNumber(int messageNumber) {
        this.mMessageNumber = messageNumber;
        invalidate();
    }

    private float pxToSp(){
        return 0;
    }

}
