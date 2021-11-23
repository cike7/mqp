package com.zhu.mqp.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhu.mqp.R;
import com.zhu.mqp.data.model.DesktopCardModel;
import com.zhu.mqp.ui.adapter.DesktopCardAdapter;
import com.zhu.mqp.ui.adapter.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/4/2021
 * Description: 聊天消息界面
 * Author: zl
 */
public class HomeFragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView cardList;
    private DesktopCardAdapter adapter;

    private ArrayList<String> images = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);

        refreshLayout = root.findViewById(R.id.refresh_home_card);
        cardList = root.findViewById(R.id.recycler_home_list);

        for (int i = 0; i < 1; i++) {
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fstatic01.coloros.com%2Fbbs%2Fdata%2Fattachment%2Fforum%2F201508%2F27%2F084205tb6gt3ugmutdodgd.jpg&refer=http%3A%2F%2Fstatic01.coloros.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=3d0d7896ee237493bfb66fed5cf19360");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fclubimg.club.vmall.com%2Fdata%2Fattachment%2Fforum%2F202004%2F27%2F091610zdzwft9diecywmoc.jpg&refer=http%3A%2F%2Fclubimg.club.vmall.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=766054800fd924472abd6d22a3dfa123");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2017-03-02%2Fd0650205791a134c836d79e3f02a1bc9%2F00002.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=68416c0d6bcef4686604210ae0f1dcbe");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.404v.com%2Fuploads%2Fallimg%2F181003%2F1-1Q003192640.jpg&refer=http%3A%2F%2Fwww.404v.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=886e7074e2f4a4d90f6beeb97aa136dc");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2018-08-18%2Fa683f7baf3f954cdfb9ddc4cac11127d%2F00003.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=a22e1609eea566a17e46e1af0ef14193");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.nga.178.com%2Fattachments%2Fmon_202104%2F07%2F-klbw3Q16u-7m6aX12ZakT3cS35s-1o0.jpg&refer=http%3A%2F%2Fimg.nga.178.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=fea27e8b4bf361a815df1fca68814b47");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fxy2-f.netease.com%2Fforum%2F201906%2F18%2F234018j9k1jripmmkprkli.jpg&refer=http%3A%2F%2Fxy2-f.netease.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=1c22e5dbb5cf4a39d38ce9e0f7132383");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.lnstzy.cn%2Fphotocnc%2F2020-03%2F18%2F202003180953385a889f70653fd47df8bcb626bc32a17b.jpg.w4096.h2160.jpg%3Fdown&refer=http%3A%2F%2Fimage.lnstzy.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=ab7194de6fb9cac9a9d6a07838893c6e");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2018-03-02%2Fe95f0c52d76effeb27a5a4855559b265%2F00001.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=21c313956f087e412759f8e6f2b23943");
            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2018-10-26%2Fb6f304ac6d33cf00a94791b057258663%2F00001.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636268398&t=c2224aa7aa205ac8007654b816d03142");
        }

        //设置网格管理器
        cardList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //设置间距
        cardList.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));

        adapter = new DesktopCardAdapter(getContext(),new ArrayList<>());
        cardList.setAdapter(adapter);

        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<DesktopCardModel> dataList = new ArrayList<>();

                        List<String> images = new ArrayList<>();

                        for (int i = 0; i < 1; i++) {
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.puchedu.cn%2Fuploads%2F0%2F26%2F609612408%2F1486271777.jpg&refer=http%3A%2F%2Fimg.puchedu.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=1655fe73db83a6adcfc1cdd70288f7a4");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdcopen.scity.cn%2Fimage%2Fget%2Fsystem%2Fbsdt%2Fbsdt-glcxysxk.jpg&refer=http%3A%2F%2Fdcopen.scity.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=dcd0750ab71d26b9c5a53a2a12ac8de3");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.meadtour.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fphoto300x200%2Fpublic%2Fimages%2F2018-04%2FHG%25E5%258A%25A8%25E7%2589%25A9c6.jpg%3Fitok%3DaolBqb6K&refer=http%3A%2F%2Fwww.meadtour.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=405c1ca0212d4d63a6c962ae970507f2");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffid-75186.picgzc.qpic.cn%2F20210827021943308v1972y8zj5piojn%3FnewsId%3DSN202108270219457c3435b3&refer=http%3A%2F%2Ffid-75186.picgzc.qpic.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=41e259783915bc6ec87f6bd575da6611");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.puchedu.cn%2Fuploads%2F3%2F26%2F413068397%2F2169071945.jpg&refer=http%3A%2F%2Fimg.puchedu.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=f7d07edb6b575d8bafab0efa26c49828");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx4.sinaimg.cn%2Flarge%2F003vHSCVly4guxc57r08oj608c05kq2w02.jpg&refer=http%3A%2F%2Fwx4.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=85ba7577a86da5d949cd6a4713239bcf");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_lfill%2Cw_300%2Ch_200%2Cg_faces%2Fimages%2F20190908%2F7b1d0ad307cb4143976960e255d14e94.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=4a5069dfdf62f352e33ab4c7ee03b7cd");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_lfill%2Cw_300%2Ch_200%2Cg_faces%2Fimages%2F20200326%2Faa8d9646bc6a434083eb6f7445c7c9ee.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=e9c6caf3a0cace47cb7e40b48408588a");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.17yy.com%2Fswf%2Fminjie%2F2021-09-08%2F38aae790e44921ea0696fd12e2381d87.jpg&refer=http%3A%2F%2Fpic2.17yy.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=aa8601c44ebd44c2554db83cbed75c7f");
                            images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_lfill%2Cw_300%2Ch_200%2Cg_faces%2Fimages%2F20200424%2F5e39c8f1ad5a48b09c2428f9db8bdefe.jpg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636254196&t=10b4080061877b841a0eb4ccc1b2f067");
                        }

                        for (int i = 0; i < DesktopCardAdapter.MAX_PAGE; i++) {
                            DesktopCardModel item = new DesktopCardModel();
                            item.setCardUrl(images.get(i));
                            dataList.add(item);
                        }

                        adapter.updateData(dataList);
                        refreshLayout.setRefreshing(false);
                    }
                },500);
            }
        });

        autoRefresh();

        return root;
    }


    /**
     * 首次自动刷新
     */
    private void autoRefresh(){

        // 进入页面时自动刷新一次
        // 需要手动关闭
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });

        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                ArrayList<DesktopCardModel> models = new ArrayList<>();

                for (int i = 0; i < DesktopCardAdapter.MAX_PAGE; i++) {
                    DesktopCardModel item = new DesktopCardModel();
                    item.setCardUrl(images.get(i));
                    models.add(item);
                }

                adapter.updateData(models);
                refreshLayout.setRefreshing(false);
            }
        },500);
    }

}