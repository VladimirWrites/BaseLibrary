package com.vlad1m1r.baselibrary.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.vlad1m1r.baselibrary.utils.LocaleUtils;
import com.vlad1m1r.baselibrary.utils.StoreUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vladimirjovanovic on 2/22/17.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    StoreUtils provideStoreUtils(Context context, SharedPreferences sharedPreferences) {
        return new StoreUtils(context, sharedPreferences);
    }

    @Provides
    @Singleton
    LocaleUtils provideLocaleUtils(Resources resources, StoreUtils storeUtils) {
        return new LocaleUtils(resources, storeUtils);
    }
}
