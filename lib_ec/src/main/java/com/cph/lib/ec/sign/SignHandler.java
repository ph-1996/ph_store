package com.cph.lib.ec.sign;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cph.lib.core.app.AccountManager;
import com.cph.lib.ec.database.DatabaseManager;
import com.cph.lib.ec.database.UserProfile;


/**
 * Created by CPH on 2019/7/6
 */
public class SignHandler {
    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId =profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar =profileJson.getString("avatar");
        final String gerder = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId+1,name,avatar,gerder,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经登陆和注册成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
    public static void onSignIn(String response,ISignListener signListener){
    /*    final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId =profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar =profileJson.getString("avatar");
        final String gerder = profileJson.getString("gender");
        final String address = profileJson.getString("address");*/

     /*   final UserProfile profile = new UserProfile(userId+1,name,avatar,gerder,address);
        //写入数据
        DatabaseManager.getInstance().getDao().insert(profile);*/

        //已经登陆和注册成功了
       AccountManager.setSignState(true);
        signListener.onSignInSuccess();
        //Acc
    }
}
