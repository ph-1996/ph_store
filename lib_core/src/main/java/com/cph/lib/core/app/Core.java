package com.cph.lib.core.app;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by CPH on 2020/1/20
 */
public final class Core {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getCoreConfig();

    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration((Enum<ConfigType>) key);
    }




}
