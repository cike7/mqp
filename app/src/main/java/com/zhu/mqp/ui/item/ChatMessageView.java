package com.zhu.mqp.ui.item;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhu.mqp.R;
import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.listener.OnClickEnterModelListener;
import com.zhu.mqp.ui.custom.MessageHeadImage;
import com.zhu.mqp.ui.listener.UpdateMessage;

import org.jetbrains.annotations.NotNull;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天列表消息
 * Author: zl
 */
public class ChatMessageView <T extends ChatMessageModel> extends RecyclerView.ViewHolder implements View.OnTouchListener, UpdateMessage<T> {

    private LinearLayout messageLayout;

    private MessageHeadImage imgHead;

    private TextView msgName;

    private TextView msgTime;

    private TextView msgContent;

    private TextView butDelete;

    private TextView butStick;

    //滑动自动对其动画
    private ValueAnimator upAnim;

    //聊天消息数据
    private ChatMessageModel mData;

    //点击移动的开始位置
    private float moveX;
    private float moveY;

    //滑动最大值
    private float maxMoveX = 360;
    //是否关闭
    private boolean isCloseAnim = false;

    @SuppressLint("ClickableViewAccessibility")
    public ChatMessageView(@NonNull @NotNull View itemView) {
        super(itemView);
        messageLayout = itemView.findViewById(R.id.linear_item_chat_message);
        imgHead = itemView.findViewById(R.id.image_item_message_head);
        msgName = itemView.findViewById(R.id.text_item_message_name);
        msgTime = itemView.findViewById(R.id.text_item_message_time);
        msgContent = itemView.findViewById(R.id.text_item_message_content);
        butDelete = itemView.findViewById(R.id.but_item_delete);
        butStick = itemView.findViewById(R.id.but_item_stick);

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onClick(view.getId(),getAdapterPosition());
                }
            }
        });

        butStick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    closeAnim();
                    mListener.onClick(view.getId(),getAdapterPosition());
                }
            }
        });

        messageLayout.setOnTouchListener(this);

        initAnim();
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        float moveXDec = (moveX - motionEvent.getRawX()) * 0.5f;
        float moveYDec = (moveY - motionEvent.getRawY()) * 0.5f;

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //拦截父级触摸事件
                messageLayout.getParent().requestDisallowInterceptTouchEvent(true);
                if(mListener != null){
                    //关闭其他开启动画的的item
                    mListener.onClick(-1,getAdapterPosition());
                }
                moveX = motionEvent.getRawX();
                moveY = motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                if(moveYDec > 100 || moveYDec < -100){
                    //拦截父级触摸事件
                    messageLayout.getParent().requestDisallowInterceptTouchEvent(false);

                }else {
                    if (moveXDec > 50) {//往左
                        view.setTranslationX(Math.max(-maxMoveX, -moveXDec));
                        //拦截父级触摸事件
                        messageLayout.getParent().requestDisallowInterceptTouchEvent(true);
                    } else if(moveXDec < -50){//往右
                        // 回去的话需要获取当前的位置减去移动的位置，
                        // 这里nowViewX和moveDec都为负数所以相减等于正数
                        view.setTranslationX(Math.min(0,view.getTranslationX() - moveXDec));
                        //拦截父级触摸事件
                        messageLayout.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //根据移动距离判断是否点击
                if (moveXDec == 0) {
                    if(mEnterListener != null){
                        mEnterListener.onClick(mData);
                    }
                }else {
                    upAnim.cancel();
                    upAnim.start();
                }

                //上下滑动时立马关闭已开启的动画
                if(moveYDec > 30 || moveYDec < -30){
                    if(mListener != null){
                        //关闭其他开启动画的的item
                        mListener.onClick(-1,getAdapterPosition());
                    }
                }

                //恢复父级触摸事件
                messageLayout.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return true;
    }


    /**
     * 初始化动画
     */
    private void initAnim(){
        upAnim = ValueAnimator.ofFloat(0,maxMoveX);
        upAnim.setDuration(500);
        upAnim.setRepeatCount(0);
        upAnim.setRepeatMode(ValueAnimator.RESTART);
        upAnim.setInterpolator(new DecelerateInterpolator());
        upAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float value = (float)valueAnimator.getAnimatedValue();
                float nowX = messageLayout.getTranslationX();

                if(isCloseAnim || messageLayout.getTranslationX() >= -maxMoveX / 2){
                    //关闭动画
                    messageLayout.setTranslationX(Math.min(0,nowX + value));
                }else {
                    //展开动画
                    messageLayout.setTranslationX(Math.max(-maxMoveX,nowX - value));
                }
            }
        });

        upAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isCloseAnim = false;
            }
        });
    }


    /**
     * 设置消息内容
     * @param data
     */
    public void setMessageContent(ChatMessageModel data) {
        mData = data;
        msgName.setText(data.getMsgName());
        msgContent.setText(mData.getMsgContent());
    }

    /**
     * 关闭滑动
     */
    public void closeAnim(){
        if(messageLayout.getTranslationX() != 0){
            isCloseAnim = true;
            upAnim.start();
        }
    }

    @Override
    public void receive(T model) {
        setMessageContent(model);
    }

    //按钮点击监听
    private OnClickListener mListener;

    //点击进入监听
    private OnClickEnterModelListener<ChatMessageModel> mEnterListener;

    public interface OnClickListener{
        void onClick(int viewId,int position);
    }

    /**
     * 添加点击监听
     */
    public void addOnClickListener(OnClickListener listener){
        mListener = listener;
    }

    /**
     * 点击进入监听
     */
    public void addOnClickEnterListener(OnClickEnterModelListener<ChatMessageModel> listener){
        mEnterListener = listener;
    }

}