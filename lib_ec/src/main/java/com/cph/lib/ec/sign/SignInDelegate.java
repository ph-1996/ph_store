package com.cph.lib.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.wechat.PhWeChat;
import com.cph.lib.core.wechat.callbacks.IWeChatSignInCallback;
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CPH on 2020/1/26
 */
public class SignInDelegate extends CoreDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
   @OnClick(R2.id.btn_sign_in)
   void onClickSignIn(){
       if(checkForm()){
           RestClient.builder()
                 //  .url("http://192.168.47.1:8090/myjson/moni.json")
                   .url("https://www.baidu.com/")
                   //  .params("","")
                   //  .params("","")
                   .success(new ISuccess() {
                       @Override
                       public void onSuccess(String response) {
                           Log.d("lxxx","success");
                           SignHandler.onSignIn(response, mISignListener);

                       }
                   })
                   .failure(new IFailure() {
                       @Override
                       public void onFailure() {
                           Log.d("lxxx","failure");
                       }
                   })
                   .error(new IError() {
                       @Override
                       public void onError(int code, String msg) {
                           Log.d("lxxx","error");
                       }
                   })
                   .build()
                   .get();
           Toast.makeText(getContext(),"通过",Toast.LENGTH_LONG).show();
       }
   }
   //点击微信登录
   @OnClick(R2.id.icon_sign_in_wechat)
   void onClickWeChat(){
       PhWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
           @Override
           public void onSignInSuccess(String userInfo) {

           }
       }).signIn();
 }
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

 //跳转到注册页面
 @OnClick(R2.id.tv_link_sing_up)
 void onClickLinkSingUp(){
       start(new SignUpDelegate());
 }




 private boolean checkForm(){
  final String email = mEmail.getText().toString();
  final String password = mPassword.getText().toString();
  boolean isPass = true;
  if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
   mEmail.setError("邮箱格式错误");
   isPass = false;
  }else {
   mEmail.setError(null);
  }
  if (password.isEmpty()||password.length()<6){
   mPassword.setError("密码位数不对");
   isPass=false;
  }else {
   mPassword.setError(null);
  }
  return isPass;
 }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
