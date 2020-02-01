package com.cph.lib.ec.main.index;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cph.lib.core.app.Core;
import com.cph.lib.core.delegates.bottom.BottomItemDelegate;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.ui.loader.LoderStyle;
import com.cph.lib.core.ui.recycler.BaseDecoration;
import com.cph.lib.core.ui.recycler.MultipleFields;
import com.cph.lib.core.ui.recycler.MultipleItemEntity;
import com.cph.lib.core.ui.refresh.RefreshHandler;
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;
import com.cph.lib.ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import org.greenrobot.greendao.generator.Index;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by CPH on 2020/1/29
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mAppCompatEditText = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage = null;
    private RefreshHandler mRefreshHandler = null;
    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        Log.d("lxx","lazy");
       mRefreshHandler.firstPage("index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler =RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
      //  mRefreshHandler.firstPage("index");
    }

    //初始化recyclerView的布局
    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));

        final EcBottomDelegate bottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(bottomDelegate));
    }



}
