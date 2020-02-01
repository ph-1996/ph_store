package com.cph.lib.core.ui.banner;


import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by CPH on 2020/1/31
 */
public class HolderCreator implements CBViewHolderCreator {

    @Override
    public Object createHolder() {
        return new ImageHolder();
    }
}
