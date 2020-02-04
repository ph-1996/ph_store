package com.cph.lib.core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.delegates.web.WebDelegate;

/**
 * Created by CPH on 2020/2/4
 */
public abstract class Event implements IEvent{
    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView webView = null;

    public Context getContext() {
        return mContext;
    }

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public CoreDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
