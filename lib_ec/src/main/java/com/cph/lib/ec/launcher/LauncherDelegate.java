package com.cph.lib.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.cph.lib.core.app.AccountManager;
import com.cph.lib.core.app.IUserChecker;
import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.ui.Launcher.ILauncherListener;
import com.cph.lib.core.ui.Launcher.OnLauncherFinishTag;
import com.cph.lib.core.ui.Launcher.ScrollLauncherTag;
import com.cph.lib.core.util.storage.CorePreference;
import com.cph.lib.core.util.timer.BaseTimerTask;
import com.cph.lib.core.util.timer.ITimerListener;
import com.cph.lib.ec.R;
import com.cph.lib.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CPH on 2020/1/24
 * 首页倒计时
 */
public class LauncherDelegate extends CoreDelegate implements ITimerListener {
    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;
    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTimer = null;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer !=null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    //初始化计时器
    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    //判断是否展示滚动页
    private void checkIsShowScroll(){
        if(!CorePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录
            AccountManager.checkAccout(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener !=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if(mTimer !=null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
