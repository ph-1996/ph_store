package com.cph.lib.core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.delegates.web.WebDelegate;
import com.cph.lib.core.delegates.web.WebDelegateImpl;

/**
 * Created by CPH on 2020/2/3
 */
public class Router {
    private Router(){}

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static final Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate,String url){
        //如果是个电话
        if(url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }
        final CoreDelegate topDelegate = delegate.getTopDelegate();
        topDelegate.start(delegate);
        return true;
    }
    private void loadWebPage(WebView webView, String url){
        if (webView != null){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("WebView is null !");
        }
    }
    private void loadLocalPage(WebView webView, String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    private  void loadPage(WebView webView, String url){
        if(URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadLocalPage(webView,url);
        }
    }
    public void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }

    private void callPhone(Context context, String url){
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
       // context.startActivity(intent);
        ContextCompat.startActivity(context,intent,null);

    }
}
