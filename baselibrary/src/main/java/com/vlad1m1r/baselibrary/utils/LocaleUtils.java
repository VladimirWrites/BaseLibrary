package com.vlad1m1r.baselibrary.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by vladimirjovanovic on 3/24/16.
 */
public class LocaleUtils {

    private Resources mResources;
    private final StoreUtils mStoreUtils;

    public LocaleUtils(Resources resources, StoreUtils storeUtils) {
        mResources = resources;
        mStoreUtils = storeUtils;
    }

    public String getLanguage() {
        return mStoreUtils.getLanguageCode();
    }

    public Locale getLocale() {

        // Change locale settings in the app.
        android.content.res.Configuration conf = mResources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) return conf.getLocales().get(0);
        else return conf.locale;
    }

    public void setLanguage(String language) {
        DisplayMetrics dm = mResources.getDisplayMetrics();
        android.content.res.Configuration conf = mResources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            conf.setLocale(new Locale(language));
        else conf.locale = new Locale(language);
        mResources.updateConfiguration(conf, dm);
        mStoreUtils.setLanguageCode(language);
    }

    public void setResources(Resources resources) {
        mResources = resources;
        setLanguage(mStoreUtils.getLanguageCode());
    }
}
