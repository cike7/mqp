package com.zhu.mqp.ui.activity;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zhu.mqp.R;
import com.zhu.mqp.ui.chat.ChatFragment;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description: 聊天界面
 * Author: zl
 */
public class MessageChatActivity extends BaseActivity {

    public final static String ChatActivityTitle = "UserName";

    @Override
    protected void onAwake() {
        super.onAwake();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_message,new ChatFragment()).commit();
    }

    @Override
    protected String getBarTitle() {
        return getIntent().getStringExtra(MessageChatActivity.ChatActivityTitle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_menu;
    }

    /**
     * 收起键盘
     */
    public void putAwayKeyboard(EditText textMsg){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textMsg.getWindowToken(), 0) ;
    }

}
