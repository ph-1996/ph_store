package com.cph.lib.core.delegates;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by CPH on 2020/1/21
 */
public abstract class CoreDelegate extends PermissionCheckerDelegate {
    public <T extends CoreDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
