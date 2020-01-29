package com.cph.lib.core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.cph.lib.core.R;
import com.cph.lib.core.delegates.CoreDelegate;

/**
 * Created by CPH on 2020/1/29
 */
public abstract class BottomItemDelegate extends CoreDelegate implements View.OnKeyListener{
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - mExitTime >mExitTime)){
                Toast.makeText(getContext(),"双击退出"+getString(R.string.app_name),Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mExitTime !=0){
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView !=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }
}
