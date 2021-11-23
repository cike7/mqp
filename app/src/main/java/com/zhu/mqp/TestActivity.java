package com.zhu.mqp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhu.annotation.ButterArrayProcess;
import com.zhu.processortest.AutoGetTargetArray;

import java.util.ArrayList;
import java.util.List;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterArrayProcess.bind(this);

        findViewById(R.id.fl_main_design).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}