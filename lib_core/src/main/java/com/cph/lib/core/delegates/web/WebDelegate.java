package com.cph.lib.core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.cph.lib.core.app.ConfigType;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by CPH on 2020/2/2
 */
public abstract class WebDelegate extends CoreDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = false;
    private CoreDelegate mTopDelegate = null;


    public WebDelegate(){

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUENE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Core.getConfiguration(ConfigType.JAVA_SCRIPT);
                mWebView.addJavascriptInterface(CoreWebInterface.create(this), name);
                mIsWebViewAbailable = true;
            } else {
                    throw new NullPointerException("初始器为空");
            }
        }
    }

    public void setTopDelegate(CoreDelegate delegate){
        mTopDelegate =delegate;
    }
    public CoreDelegate getTopDelegate(){
        if(mTopDelegate ==null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }
    public WebView getWebView(){
        if (mWebView == null){
            throw new NullPointerException("WEBVIEW IS NULL");
        }
        return mIsWebViewAbailable? mWebView:null;
    }

    public String getUrl(){
        if (mWebView == null){
            throw new NullPointerException("WEBVIEW IS NULL");
        }
        return mUrl;
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
