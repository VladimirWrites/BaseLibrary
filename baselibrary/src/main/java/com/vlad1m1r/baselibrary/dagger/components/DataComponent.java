package com.vlad1m1r.baselibrary.dagger.components;

import android.app.Application;
import android.content.Context;

import com.vlad1m1r.baselibrary.base.BaseActivity;
import com.vlad1m1r.baselibrary.dagger.modules.AppModule;
import com.vlad1m1r.baselibrary.dagger.modules.DataModule;
import com.vlad1m1r.baselibrary.utils.LocaleUtils;
import com.vlad1m1r.baselibrary.utils.StoreUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vladimirjovanovic on 2/22/17.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class
})
public interface DataComponent {
    void inject(BaseActivity activity);

    Application getApplication();
    StoreUtils getStoreUtils();
    LocaleUtils getLocaleUtils();
    Context getContext();
}
