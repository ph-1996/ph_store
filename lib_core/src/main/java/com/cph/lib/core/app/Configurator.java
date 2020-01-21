package com.cph.lib.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by CPH on 2020/1/20
 */
public class Configurator {
    private static final HashMap<String,Object> CORE_CONFIG = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private Configurator(){
        //配置还没有完成
        CORE_CONFIG.put(ConfigType.CONFIG_READY.name(),false);
    }
    //获取单例
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public static HashMap<String,Object> getCoreConfig(){
        return CORE_CONFIG;
    }
    //表示配置已经完成
    public final void configure(){
        initIcons();
        CORE_CONFIG.put(ConfigType.CONFIG_READY.name(),true);
    }
    //设置api
    public final Configurator setApiHost(String host){
        CORE_CONFIG.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    //初始化字体
    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }
    //加入字体
    public final Configurator addIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    //检查配置是否完成
    private void checkConfiguration(){
        final boolean isReady = (boolean) CORE_CONFIG.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("配置未完成");

        }

    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) CORE_CONFIG.get(key.name());
    }

}
