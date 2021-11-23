package com.zhu.mqp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhu.mqp.R;
import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.listener.OnClickEnterModelListener;
import com.zhu.mqp.ui.activity.MessageChatActivity;
import com.zhu.mqp.ui.item.ChatMessageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天消息
 * Author: zl
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ChatMessageView.OnClickListener {

    private Context mContext;

    private ArrayList<ChatMessageModel> mMsg;

    //聊天界面
    private Intent chatIntent;

    //上次点击的索引
    private int previous = -1;

    public ChatMessageAdapter(Context context, ArrayList<ChatMessageModel> msg) {
        mContext = context;
        mMsg = msg;
        chatIntent = new Intent(context, MessageChatActivity.class);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_message, parent, false);
        ChatMessageView<ChatMessageModel> item = new ChatMessageView(view);
        item.addOnClickListener(this);
        item.addOnClickEnterListener(new OnClickEnterModelListener() {
            @Override
            public void onClick(Object model) {

                chatIntent.putExtra(MessageChatActivity.ChatActivityTitle,((ChatMessageModel)model).getMsgName());

                mContext.startActivity(chatIntent);
            }
        });
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ChatMessageView item = (ChatMessageView) holder;
        item.setMessageContent(mMsg.get(position));
    }

    @Override
    public int getItemCount() {
        return mMsg.size();
    }

    @Override
    public void onClick(int viewId, int position) {

        if (viewId == -1) {
            if (previous != position) {
                if (examineListener != null) {
                    examineListener.onExamine(position);
                }
            }
            previous = position;
        } else if (viewId == R.id.but_item_stick) {//置顶

            ChatMessageModel item = mMsg.get(position);
            mMsg.remove(position);
            mMsg.add(0,item);

            notifyItemMoved(position, 0);

        } else if (viewId == R.id.but_item_delete) {//删除
            removeItem(position);
        }
    }

    //移除item
    private void removeItem(int position) {
        mMsg.remove(position);
        notifyItemRemoved(position);
    }


    //添加消息
    private void addItem(ChatMessageModel model) {
        mMsg.add(model);
        notifyItemInserted(mMsg.size());
    }


    /**
     * 更新消息
     * @param model
     */
    public void updateMessage(ChatMessageModel model) {

        if(model == null) return;

        //查找消息接收者是否存在
        int chatObjectIndex = getChatObjectIndex(model.getMsgName());

        if(chatObjectIndex == -1){
            //添加新的观察者对象
            addItem(model);
        }else {
            //通知观察者对象更新消息
            mMsg.set(chatObjectIndex,model);
            notifyItemChanged(chatObjectIndex);
        }

    }


    /**
     * 获取聊天对象索引
     * @param objectName
     * @return
     */
    private int getChatObjectIndex(String objectName){
        //查找消息接收者是否存在
        for (int i = 0; i < mMsg.size(); i++) {
            if(mMsg.get(i).getMsgName().equals(objectName)){
                return i;
            }
        }
        return -1;
    }


    //检查关闭动画
    private ExamineCloseListener examineListener;

    public interface ExamineCloseListener {
        /**
         * 检查消息列表是否有未关闭的动画
         * @param position 当前item索引
         */
        void onExamine(int position);
    }

    /**
     * 检查其他item是否开启动画
     * @param examineListener
     */
    public void setExamineListener(ExamineCloseListener examineListener) {
        this.examineListener = examineListener;
    }
}
