package com.cph.phstore;

import android.app.AppComponentFactory;
import android.app.Application;

import com.cph.lib.core.app.Core;
import com.cph.lib.core.net.Interceptors.DebugInterceptor;
import com.cph.lib.ec.database.DatabaseManager;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.greendao.database.Database;

/**
 * Created by CPH on 2020/1/20
 */
public class StoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Core.init(this)
                .addIcon(new FontAwesomeModule())
               // .setApiHost("http://192.168.47.1:8090/myjson/moni.json")
                .setApiHost("https://www.baidu.com/")
           //     .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
    }

}
