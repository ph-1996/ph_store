package com.cph.lib.core.wechat.templates;


import com.cph.lib.core.activities.ProxyActivity;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.wechat.BaseWXEntryActivity;
import com.cph.lib.core.wechat.PhWeChat;


/**
 * Created by CPH on 2019/7/8
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        PhWeChat.getInstance().getmSignInCallback().onSignInSuccess(userInfo);
    }
}
