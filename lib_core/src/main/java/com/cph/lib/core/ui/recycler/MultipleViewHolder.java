package com.cph.lib.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by CPH on 2020/1/31
 */
public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }
    public static  MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
