package com.cph.lib.core.ui.Launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by CPH on 2020/1/24
 */
public class LauncherHolderCreator implements CBViewHolderCreator {


    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
