package com.vlad1m1r.baselibrary.sample;

import com.vlad1m1r.baselibrary.BaseAppController;

public class AppController extends BaseAppController {

    private static AppController sAppController;

    public void onCreate() {
        super.onCreate();

        sAppController = this;
    }

    public static AppController getAppController() {
        return sAppController;
    }
}

