/*
 * Copyright 2017 Vladimir Jovanovic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vlad1m1r.baselibrary.dagger;

import android.app.Application;

import com.vlad1m1r.baselibrary.dagger.components.DataComponent;
import com.vlad1m1r.baselibrary.dagger.modules.AppModule;
import com.vlad1m1r.baselibrary.dagger.modules.DataModule;
import com.vlad1m1r.baselibrary.dagger.components.DaggerDataComponent;

public class DaggerLibWrapper {

    private static DataComponent dataComponent;

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static void initComponent (Application application) {

        dataComponent = DaggerDataComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(application)) // This also corresponds to the name of your module: %component_name%Module
                .dataModule(new DataModule())
                .build();
    }
}
