package com.zhu.mqp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhu.mqp.R;
import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.data.model.DesktopCardModel;
import com.zhu.mqp.ui.activity.MessageChatActivity;
import com.zhu.mqp.ui.custom.DesktopCardView;
import com.zhu.mqp.ui.item.ChatMessageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/6
 * Description:
 * Author: zl
 */
public class DesktopCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //一页数据
    public final static int MAX_PAGE = 10;

    private Context mContext;

    private ArrayList<DesktopCardModel> mCardModels;

    public DesktopCardAdapter(Context context, ArrayList<DesktopCardModel> cardModels) {
        mContext = context;
        mCardModels = cardModels;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_desktop_card, parent, false);
        return new DesktopCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        DesktopCardView card = (DesktopCardView)holder;
        card.setModel(mCardModels.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mCardModels.size();
    }

    /**
     * 更新数据
     * @param models
     */
    public void updateData(ArrayList<DesktopCardModel> models) {

        mCardModels.addAll(models);

        notifyDataSetChanged();

//        if (models.size() > 0) {
//
//            if(mCardModels.size() >= MAX_PAGE){
//
//                DesktopCardModel[] modelsTemporary = new DesktopCardModel[MAX_PAGE * 2];
//
//                for (int i = 0; i < modelsTemporary.length; i++) {
//                    if(i < MAX_PAGE){
//                        for (int j = 0; j < models.size(); j++) {
//                            modelsTemporary[i] = models.get(j);
//                        }
//                    }else {
//                        for (int k = 0; k < mCardModels.size(); k++) {
//                            modelsTemporary[i] = mCardModels.get(k);
//                        }
//                    }
//                }
//
//                replace(modelsTemporary);
//
//            }else {
//                mCardModels.addAll(models);
//            }
//
//            notifyDataSetChanged();
//
//        }

    }

    /**
     * 替换头部数据
     * @param modelsTemporary
     */
    private void replace(DesktopCardModel[] modelsTemporary){
        for (int i = 0; i < modelsTemporary.length; i++) {
            if(i < mCardModels.size()){
                mCardModels.set(i,modelsTemporary[i]);
//                notifyItemChanged(i);
            }else {
                mCardModels.add(modelsTemporary[i]);
//                notifyItemInserted(i);
            }
        }
    }


}
