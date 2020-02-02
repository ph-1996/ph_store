package com.cph.lib.ec.main.sort.content;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by CPH on 2020/2/1
 */
public class ContentDelegate extends CoreDelegate {

    public static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    @BindView(R2.id.rv_vertical_sort_content)
    RecyclerView mRecyclerView = null;
   // private static int num ;

    private List<SectionBean> dataList = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args!=null){
            mContentId = args.getInt(ARG_CONTENT_ID);
        }

    }

    public static ContentDelegate newInstance(int contentId){
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(args);
        //num =contentId;
        return contentDelegate;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    //初始化数据
    private void initData(){
        RestClient.builder()
                .url("sort_content")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        dataList = new SeletionDataConverter().convert(response);
                        final SectionAdapter adapter =
                                new SectionAdapter(R.layout.item_section_content,R.layout.item_section_header,dataList);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
            final StaggeredGridLayoutManager manager =
                    new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(manager);
            initData();
    }
}
