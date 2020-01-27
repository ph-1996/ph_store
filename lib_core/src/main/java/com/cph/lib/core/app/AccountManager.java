package com.cph.lib.core.app;

import com.cph.lib.core.util.storage.CorePreference;

/**
 * Created by CPH on 2020/1/27
 */
public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登陆状态
    public static void setSignState(boolean state){
        CorePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }


    private static boolean isSignIn(){
        return CorePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static  void checkAccout(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
