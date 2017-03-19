package com.vlad1m1r.baselibrary;

import android.app.Application;

import com.vlad1m1r.baselibrary.dagger.DaggerLibWrapper;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by vladimirjovanovic on 3/17/17.
 */

public class BaseAppController extends Application {

    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        DaggerLibWrapper.initComponent(this);
    }
}
