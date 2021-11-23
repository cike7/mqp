package com.zhu.mqp.ui.custom;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import com.zhu.mqp.R;
import com.zhu.mqp.data.model.DesktopCardModel;
import com.zhuli.loadImg.ImageLoader;

import org.jetbrains.annotations.NotNull;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/6
 * Description: 桌面卡片封装类
 * Author: zl
 */
public class DesktopCardView extends RecyclerView.ViewHolder {

    private ImageView imageCard;

    private TextView textCheck,textComment,textTitle,textName;

    private DesktopCardModel mModel;

    private View root;

    public DesktopCardView(@NotNull View root) {
        super(root);

        this.root = root;
        imageCard = root.findViewById(R.id.img_item_desktop_card);
        textCheck = root.findViewById(R.id.text_item_desktop_card_check);
        textComment = root.findViewById(R.id.text_item_desktop_card_comment);
        textTitle = root.findViewById(R.id.text_item_desktop_card_title);
        textName = root.findViewById(R.id.text_item_desktop_card_name);

    }

    public void setModel(DesktopCardModel model,int position){

        if(model.getCardUrl() == null || model.getCardUrl().equals("")){
            imageCard.setImageResource(model.getCardImgId());
            textTitle.setText(model.getTitle());
        }else {

            String[] str = model.getCardUrl().split("=");
            String urlName = str[str.length - 1];
            textTitle.setText(urlName);

            //ImageLoader.load(model.getCardUrl(),position).placeholder(R.mipmap.img_lotus).setImageView(imageCard);

            ImageLoader.with(root)
                    .placeholder(R.mipmap.img_lotus)
                    .load(model.getCardUrl())
                    .setImageView(imageCard);

//            Glide.with(root)
//                    .load(model.getCardUrl())
//                    .centerCrop()
//                    .placeholder(R.mipmap.img_lotus)
//                    .into(imageCard);

        }

        mModel = model;
        textCheck.setText(String.valueOf(model.getComment()));
        textComment.setText(String.valueOf(model.getComment()));
//        textTitle.setText(model.getTitle());
        textName.setText(model.getName());

    }


    public DesktopCardModel getModel() {
        return mModel;
    }
}
