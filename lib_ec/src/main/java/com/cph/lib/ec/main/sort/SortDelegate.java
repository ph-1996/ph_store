package com.cph.lib.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.bottom.BottomItemDelegate;
import com.cph.lib.ec.R;

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
}
