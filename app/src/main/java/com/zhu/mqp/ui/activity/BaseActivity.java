package com.zhu.mqp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zhu.mqp.R;

/**
 * Copyright (C) 王字旁的理
 * Date: 9/5/2021
 * Description: 聊天 BaseActivity
 * Author: zl
 */
public class BaseActivity extends AppCompatActivity {

    // TODO 通过BindView绑定id失败，原因未知
    //标题
    private Toolbar toolbar;

    //内容展示
    private ViewStub viewStub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = findViewById(R.id.toolbar_message_view);

        if(getBarTitleId() != 0){
            toolbar.setTitle(getBarTitleId());
        }else {
            if(getBarTitle() != null){
                toolbar.setTitle(getBarTitle());
            }
        }

        setSupportActionBar(toolbar);

        //监听返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

        if(getLayoutId() != 0){
            viewStub = findViewById(R.id.viewStub_base_content);
            viewStub.setLayoutResource(getLayoutId());
            viewStub.inflate();
        }

        onAwake();
    }

    protected void onAwake(){

    }

    protected void onBack(){
        print("返回");
        finish();
    }

    //导航栏标题
    protected int getBarTitleId(){
        return 0;
    }

    //导航栏标题
    protected String getBarTitle(){
        return null;
    }

    protected int getLayoutId(){
        return 0;
    }

    public static void print(String str){
        Log.e("activity print",">>" + str);
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
