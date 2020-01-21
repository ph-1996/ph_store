package com.cph.phstore;

import android.app.AppComponentFactory;
import android.app.Application;

import com.cph.lib.core.app.Core;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by CPH on 2020/1/20
 */
public class StoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Core.init(this)
                .addIcon(new FontAwesomeModule())
                .setApiHost("http://127.0.0.1")
                .configure();
    }
}
