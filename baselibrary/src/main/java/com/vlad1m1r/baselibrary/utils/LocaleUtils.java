package com.vlad1m1r.baselibrary.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by vladimirjovanovic on 3/24/16.
 */
public class LocaleUtils {

    private Resources resources;
    private final StoreUtils storeUtils;

    public LocaleUtils(Resources resources, StoreUtils storeUtils) {
        this.resources = resources;
        this.storeUtils = storeUtils;
    }

    public String getLanguage() {
        return storeUtils.getLanguageCode();
    }

    public Locale getLocale() {

        // Change locale settings in the app.
        android.content.res.Configuration conf = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) return conf.getLocales().get(0);
        else return conf.locale;
    }

    public void setLanguage(String language) {
        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            conf.setLocale(new Locale(language));
        else conf.locale = new Locale(language);
        this.resources.updateConfiguration(conf, dm);
        this.storeUtils.setLanguageCode(language);
    }

    public void setResources(Resources resources) {
        this.resources = resources;
        setLanguage(storeUtils.getLanguageCode());
    }
}
