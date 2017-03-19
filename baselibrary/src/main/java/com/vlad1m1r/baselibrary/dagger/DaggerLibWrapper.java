package com.vlad1m1r.baselibrary.dagger;

import android.app.Application;

import com.vlad1m1r.baselibrary.dagger.components.DataComponent;
import com.vlad1m1r.baselibrary.dagger.modules.AppModule;
import com.vlad1m1r.baselibrary.dagger.modules.DataModule;
import com.vlad1m1r.baselibrary.dagger.components.DaggerDataComponent;

/**
 * Created by vladimirjovanovic on 3/17/17.
 */

public class DaggerLibWrapper {

    private static DataComponent sDataComponent;

    public static DataComponent getDataComponent() {
        return sDataComponent;
    }

    public static void initComponent (Application application) {

        sDataComponent = DaggerDataComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(application)) // This also corresponds to the name of your module: %component_name%Module
                .dataModule(new DataModule())
                .build();
    }
}
