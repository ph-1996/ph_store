package com.cph.lib.core.util.dimen;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.cph.lib.core.app.ConfigType;
import com.cph.lib.core.app.Core;

/**
 * Created by CPH on 2020/1/23
 */
public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Core.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Core.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}