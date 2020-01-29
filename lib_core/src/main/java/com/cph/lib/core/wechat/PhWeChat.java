package com.cph.lib.core.wechat;

import android.app.Activity;

import com.cph.lib.core.app.ConfigType;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;





public class PhWeChat  {
   public static final String APP_ID =  Core.getConfiguration(ConfigType.WE_CHAT_APP_ID.name());
    public static final String APP_SECRET  = Core.getConfiguration(ConfigType.WE_CHAT_APP_SECRET.name());
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder{
        private static final PhWeChat INSTANCE = new PhWeChat();
    }
    public static PhWeChat getInstance(){
        return Holder.INSTANCE;
    }
    //初始化
    private PhWeChat(){
        final Activity activity = Core.getConfiguration(ConfigType.ACTIVITY.name());
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }
    //得到微信API
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }
    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_suerinfon";
        req.state = "radom_state";
        WXAPI.sendReq(req);
    }

    public PhWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback = callback;
        return this;
    }
    public IWeChatSignInCallback getmSignInCallback(){
        return mSignInCallback;
    }
}
