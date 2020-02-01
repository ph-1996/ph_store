package com.cph.lib.core.ui.recycler;

import androidx.annotation.ColorInt;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by CPH on 2020/2/1
 * recycleView分割线
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color,int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color,int size){
        return new BaseDecoration(color,size);
    }

    //分割线实现类
    class DividerLookupImpl implements DividerItemDecoration.DividerLookup{

        private final int COLOR;
        private final int SIZE;

        public DividerLookupImpl(int color, int size) {
            this.COLOR = color;
            this.SIZE = size;
        }

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder().size(SIZE).color(COLOR).build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder().size(SIZE).color(COLOR).build();
        }
    }
}
