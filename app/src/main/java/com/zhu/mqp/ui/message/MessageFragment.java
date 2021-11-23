package com.zhu.mqp.ui.message;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.databinding.FragmentMessageBinding;
import com.zhu.mqp.ui.adapter.ChatMessageAdapter;
import com.zhu.mqp.ui.item.ChatMessageView;

import java.util.ArrayList;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天消息界面
 * Author: zl
 */
public class MessageFragment extends Fragment {

    private MessageViewModel homeViewModel;
    private FragmentMessageBinding binding;

    private SwipeRefreshLayout refreshLayout;

    private RecyclerView msgList;
    private ChatMessageAdapter adapter;

    public MessageFragment() { }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        binding = FragmentMessageBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        refreshLayout = binding.refreshChatMessage;

        msgList = binding.recyclerChatMessageList;

        msgList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        //添加分割器
        msgList.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));

        //加载历史记录
        homeViewModel.getHistoryMessage().observe(getViewLifecycleOwner(), new Observer<ArrayList<ChatMessageModel>>() {
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


        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ChatMessageModel model = new ChatMessageModel();
                        model.setMsgName("新人来了");
                        model.setMsgContent(">" + System.currentTimeMillis());
                        adapter.updateMessage(model);
                        refreshLayout.setRefreshing(false);

                    }
                },500);
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