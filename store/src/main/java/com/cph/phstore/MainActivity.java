package com.cph.phstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.cph.lib.core.activities.ProxyActivity;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.ui.Launcher.ILauncherListener;
import com.cph.lib.core.ui.Launcher.OnLauncherFinishTag;
import com.cph.lib.ec.launcher.LauncherDelegate;
import com.cph.lib.ec.launcher.LauncherScrollDelegate;
import com.cph.lib.ec.sign.ISignListener;
import com.cph.lib.ec.sign.SignInDelegate;
import com.cph.lib.ec.sign.SignUpDelegate;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //添加微信上下文
        Core.getConfigurator().withActivity(this);
    }

    @Override
    public CoreDelegate setRootDelegate() {


        return new LauncherDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            //已经登陆
            case SIGNED:
                Toast.makeText(this,"已经登陆",Toast.LENGTH_LONG).show();
                start(new ExampleDelegate());
                break;
                //未登录
            case NOT_SIGNED:
                Toast.makeText(this,"没有登陆",Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
        }
    }
}
