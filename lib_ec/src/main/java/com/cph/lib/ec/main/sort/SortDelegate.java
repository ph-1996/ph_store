package com.cph.lib.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.bottom.BottomItemDelegate;
import com.cph.lib.ec.R;
import com.cph.lib.ec.main.sort.content.ContentDelegate;
import com.cph.lib.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by CPH on 2020/1/29
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {

        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
       final VerticalListDelegate listDelegate =new VerticalListDelegate();
       loadRootFragment(R.id.fl_list_container,listDelegate);
       //设置右侧第一个分类显示
       loadRootFragment(R.id.sort_content_containner, ContentDelegate.newInstance(1));
    }
}
