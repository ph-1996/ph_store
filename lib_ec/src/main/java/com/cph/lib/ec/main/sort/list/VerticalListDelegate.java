package com.cph.lib.ec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.ui.recycler.MultipleItemEntity;
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;
import com.cph.lib.ec.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by CPH on 2020/2/1
 */
public class VerticalListDelegate extends CoreDelegate {
    @BindView(R2.id.rv_vertical_sort_list)
    RecyclerView mRecyclerView;
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
          //  initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecyclerView();
        RestClient.builder().url("sort_list")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonDate(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
