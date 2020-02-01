package com.cph.lib.core.app;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by CPH on 2020/1/20
 */
public class Configurator {
    private static final HashMap<String,Object> CORE_CONFIG = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTOR = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    private Configurator(){
        //配置还没有完成
        CORE_CONFIG.put(ConfigType.CONFIG_READY.name(),false);
        CORE_CONFIG.put(ConfigType.HANDLER.name(), HANDLER);
    }
    //获取单例
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public  HashMap<String,Object> getCoreConfig(){
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
        //Log.d("lxx",""+CORE_CONFIG.get(ConfigType.API_HOST.name()));
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

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTOR.add(interceptor);
        CORE_CONFIG.put(ConfigType.INTERCEPTOR.name(),INTERCEPTOR);
        return this;
    }
    //加入拦截器
    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTOR.addAll(interceptors);
        CORE_CONFIG.put(ConfigType.INTERCEPTOR.name(),INTERCEPTOR);
        return this;
    }
    //微信APPID
    public final Configurator withWechatAppId(String appId){
        CORE_CONFIG.put(ConfigType.WE_CHAT_APP_ID.name(),appId);
        return this;
    }
    //微信密码
    public final Configurator withWechatSecret(String appSecret){
        CORE_CONFIG.put(ConfigType.WE_CHAT_APP_SECRET.name(),appSecret);
        return this;
    }

    //微信上下文
    public final Configurator withActivity(Activity activity){
        CORE_CONFIG.put(ConfigType.ACTIVITY.name(),activity);
        return this;
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
    public final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) CORE_CONFIG.get(key.name());
    }
}
