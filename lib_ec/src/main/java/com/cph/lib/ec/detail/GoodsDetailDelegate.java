package com.cph.lib.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.ec.R;

/**
 * Created by CPH on 2020/2/1
 */
public class GoodsDetailDelegate extends CoreDelegate {
    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}
