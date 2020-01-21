package com.cph.phstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cph.lib.core.activities.ProxyActivity;
import com.cph.lib.core.delegates.CoreDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public CoreDelegate setRootDelegate() {
        return new ExampleDelegate();
    }


}
