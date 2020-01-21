package com.cph.phstore;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.CoreDelegate;

/**
 * Created by CPH on 2020/1/21
 */
public class ExampleDelegate extends CoreDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
