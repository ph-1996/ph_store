package com.cph.lib.core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by CPH on 2020/1/20
 */
public final class Core {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getCoreConfig();

    }


}
