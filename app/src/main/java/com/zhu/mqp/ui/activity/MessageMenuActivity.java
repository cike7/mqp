package com.zhu.mqp.ui.activity;

import com.zhu.mqp.R;
import com.zhu.mqp.ui.message.MessageFragment;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description: 消息菜单界面
 * Author: zl
 */
public class MessageMenuActivity extends BaseActivity {

    @Override
    protected void onAwake() {
        super.onAwake();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_message,new MessageFragment()).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_menu;
    }

}
