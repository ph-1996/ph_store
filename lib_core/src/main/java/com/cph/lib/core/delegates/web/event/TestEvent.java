package com.cph.lib.core.delegates.web.event;

import android.widget.Toast;

/**
 * Created by CPH on 2020/2/4
 */
public class TestEvent extends Event{
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),params,Toast.LENGTH_LONG).show();
        return null;
    }
}
