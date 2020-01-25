package com.cph.phstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cph.lib.core.activities.ProxyActivity;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.ec.launcher.LauncherDelegate;
import com.cph.lib.ec.launcher.LauncherScrollDelegate;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends ProxyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public CoreDelegate setRootDelegate() {


        return new LauncherDelegate();
    }


}
