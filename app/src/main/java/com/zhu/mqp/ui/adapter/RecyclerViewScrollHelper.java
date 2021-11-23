package com.zhu.mqp.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/27
 * Description: 快速滚动RecyclerView位置动画
 * Author: zl
 */
public class RecyclerViewScrollHelper {

    public static void scrollToPosition(RecyclerView recyclerView, int position){
        RecyclerView.LayoutManager manager1 = recyclerView.getLayoutManager();
        if (manager1 instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) manager1;
            final TopSmoothScroller mScroller = new TopSmoothScroller(recyclerView.getContext());
            mScroller.setTargetPosition(position);
            manager.startSmoothScroll(mScroller);
        }
    }

    public static class TopSmoothScroller extends LinearSmoothScroller {
        TopSmoothScroller(Context context) {
            super(context);
        }
        @Override
        protected int getHorizontalSnapPreference() {
            return SNAP_TO_START;
        }
        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_START;
        }
    }

}
