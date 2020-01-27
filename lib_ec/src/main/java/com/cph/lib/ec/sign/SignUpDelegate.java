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
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CPH on 2020/1/25
 */
public class SignUpDelegate extends CoreDelegate {
   @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
   @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
   @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
   @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
   @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

   private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
   void onClickSignUp(){
     if(checkForm()){
          RestClient.builder()
                //   .url("http://192.168.47.1:8090/myjson/moni.json")
                  .url("https://www.baidu.com/")
              //  .params("","")
                //  .params("","")
                   .success(new ISuccess() {
                       @Override
                       public void onSuccess(String response) {
                           Log.d("lxxx","success");
                       SignHandler.onSignUp(response, mISignListener);

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
           Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();
       }
   }
   @OnClick(R2.id.tv_link_sign_in)
   void onClickLinkSignIn(){
       start(new SignInDelegate());
   }
    //检查输入
    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String repassword = mRePassword.getText().toString();
        boolean isPass = true;
        if(name.isEmpty()){
            mName.setError("请输入姓名");
            isPass=false;
        }else {
            mName.setError(null);
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("邮箱格式错误");
            isPass = false;
        }else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass=false;
        }else {
            mPhone.setError(null);
        }
        if (password.isEmpty()||password.length()<6){
            mPassword.setError("密码位数不对");
            isPass=false;
        }else {
            mPassword.setError(null);
        }
        if(!(repassword.equals(password))||repassword.isEmpty()||repassword.length()<6){
            mRePassword.setError("重复密码错误");
            isPass=false;
        }else {
            mRePassword.setError(null);
        }
        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
