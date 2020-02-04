package com.cph.lib.core.delegates.web;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cph.lib.core.delegates.web.client.WebViewClientImpl;

import java.nio.file.FileAlreadyExistsException;

/**
 * Created by CPH on 2020/2/3
 */
public class WebViewInitializer {

    public WebView createWebView(WebView webView){
        //可以调试
        webView.setWebContentsDebuggingEnabled(true);
        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        //初始化webSetting
        final WebSettings settings = webView.getSettings();
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua+"CORE");
        settings.setJavaScriptEnabled(true);
        //屏蔽缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //w文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }
}
