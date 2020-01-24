package com.cph.phstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cph.lib.core.activities.ProxyActivity;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.delegates.CoreDelegate;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends ProxyActivity {

    @Override
    public CoreDelegate setRootDelegate() {


        return new ExampleDelegate();
    }


}
