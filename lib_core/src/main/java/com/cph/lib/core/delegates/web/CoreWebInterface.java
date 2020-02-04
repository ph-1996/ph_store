package com.cph.lib.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.cph.lib.core.delegates.web.event.Event;
import com.cph.lib.core.delegates.web.event.EventManager;

/**
 * Created by CPH on 2020/2/3
 */
public class CoreWebInterface {

    private final WebDelegate DELEGATE;

    private CoreWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static CoreWebInterface create(WebDelegate delegate){
        return new CoreWebInterface(delegate);
    }
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if(event != null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
