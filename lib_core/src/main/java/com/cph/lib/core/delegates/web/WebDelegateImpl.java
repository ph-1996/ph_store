package com.cph.lib.core.delegates.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.web.chromeclient.WebChromeClientImpl;
import com.cph.lib.core.delegates.web.client.WebViewClientImpl;
import com.cph.lib.core.delegates.web.route.RouteKeys;
import com.cph.lib.core.delegates.web.route.Router;

/**
 * Created by CPH on 2020/2/3
 */
public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl()!=null){
            //用原生的方式模拟web跳转
            Router.getInstance().loadPage(this,getUrl());

        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
