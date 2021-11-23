package com.zhu.mqp.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.databinding.FragmentChatBinding;
import com.zhu.mqp.ui.activity.MessageChatActivity;
import com.zhu.mqp.ui.adapter.ChatMessageAdapter;
import com.zhu.mqp.ui.item.ChatMessageView;

import java.util.ArrayList;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天界面
 * Author: zl
 */
public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;
    private FragmentChatBinding binding;

    private RecyclerView msgList;

    private EditText textMsg;

    private ChatMessageAdapter adapter;

    private boolean keyboardUp = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        msgList = binding.recyclerChatMessageList;

        textMsg = binding.editChatInputText;

        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                msgList.scrollToPosition(adapter.getItemCount() - 1);
                keyboardUp = true;
            }

            @Override
            public void keyBoardHide(int height) {
                keyboardUp = false;
            }
        });

        msgList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        msgList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (keyboardUp) {
                    //上滑动距离超过5像素就收起键盘
                    if (Math.abs(scrollY - oldScrollY) > 5) {
                        if (getActivity() != null){
                            ((MessageChatActivity) getActivity()).putAwayKeyboard(textMsg);
                        }
                    }
                }else {
                    //值表示是否能向下滚动，false表示已经滚动到顶部
                    if(!msgList.canScrollVertically(-1)) {
                        ChatMessageModel model = new ChatMessageModel();
                        model.setMsgName("加载历史消息");
                        model.setMsgContent(">" + System.currentTimeMillis());
                        adapter.updateMessage(model);
                    }

                    //值表示是否能向上滚动，false表示已经滚动到底部
                    if(!msgList.canScrollVertically(1)){

                    }
                }
            }
        });

        //添加分割器
        msgList.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));

        //加载历史记录
        chatViewModel.getHistoryMessage().observe(getViewLifecycleOwner(), new Observer<ArrayList<ChatMessageModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ChatMessageModel> messages) {

                adapter = new ChatMessageAdapter(root.getContext(), messages);

                adapter.setExamineListener(new ChatMessageAdapter.ExamineCloseListener() {
                    @Override
                    public void onExamine(int position) {
                        RecyclerView.LayoutManager manager = msgList.getLayoutManager();
                        if(manager != null){
                            for (int k = 0; k < manager.getChildCount(); k++) {
                                //关闭其它已开启的动画
                                if(k != position){
                                    View view = manager.getChildAt(k);
                                    if(view!= null){
                                        ChatMessageView<ChatMessageModel> msgView = (ChatMessageView)msgList.getChildViewHolder(view);
                                        msgView.closeAnim();
                                    }
                                }
                            }
                        }
                    }
                });

                msgList.setAdapter(adapter);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}