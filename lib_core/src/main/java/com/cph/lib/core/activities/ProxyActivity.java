package com.cph.lib.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.cph.lib.core.R;
import com.cph.lib.core.delegates.BaseDelegate;
import com.cph.lib.core.delegates.CoreDelegate;


import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by CPH on 2020/1/21
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract CoreDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
           loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }
}
