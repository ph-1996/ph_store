package com.cph.lib.core.ui.refresh;


import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.ui.recycler.DatatConverter;
import com.cph.lib.core.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by CPH on 2019/8/4
 */
public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {
    //刷新布局
    private  final SwipeRefreshLayout REFRESH_LAYOUT;
    //存储分页数据
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DatatConverter CONVERTER;
    private RefreshHandler(SwipeRefreshLayout refresh_layout,RecyclerView recyclerView,DatatConverter datatConverter,PagingBean bean) {
        this.REFRESH_LAYOUT = refresh_layout;
        this.REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = datatConverter;
        this.BEAN = bean;
    }
    //初始化refreshHandler
    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,DatatConverter datatConverter){
        return new RefreshHandler(swipeRefreshLayout,recyclerView,datatConverter,new PagingBean());
    }
    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Core.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }
    //加载第一页内容
    public void firstPage(String url){
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject jsonObject = JSON.parseObject(response);
                        BEAN.setTotal(jsonObject.getInteger("total"))
                                .setPageSize(jsonObject.getInteger("paze_size"));
                         //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonDate(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("lxx","firstPage-failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d("lxx","firstPage-error");
                    }
                })
                .build().get();}

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
