package com.cph.lib.ec.launcher;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.ui.Launcher.LauncherHolderCreator;
import com.cph.lib.core.util.storage.CorePreference;
import com.cph.lib.ec.R;

import java.util.ArrayList;

/**
 * Created by CPH on 2020/1/24
 * 首页轮播
 */
public class LauncherScrollDelegate extends CoreDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
        .setOnItemClickListener(this)
        .setCanLoop(false);

    }
    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //判断点击是不是最后一个
        if(position == INTEGERS.size()-1){
            CorePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否登录
        }

    }
}
