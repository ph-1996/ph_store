package com.cph.lib.core.ui.Launcher;

import android.content.Context;
import android.print.PrinterId;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by CPH on 2020/1/24
 */
public class LauncherHolder implements  Holder {
    private ImageView mImageView = null;
    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Object data) {
        mImageView.setImageResource((Integer) data);
    }
}
