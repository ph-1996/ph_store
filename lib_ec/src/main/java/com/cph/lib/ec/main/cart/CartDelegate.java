package com.cph.lib.ec.main.cart;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.bottom.BottomItemDelegate;
import com.cph.lib.ec.R;

/**
 * Created by CPH on 2020/1/29
 */
public class CartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
