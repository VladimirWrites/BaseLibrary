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

package com.vlad1m1r.baselibrary.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.vlad1m1r.baselibrary.utils.LocaleUtils;
import com.vlad1m1r.baselibrary.utils.NetworkUtils;
import com.vlad1m1r.baselibrary.utils.StoreUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    StoreUtils provideStoreUtils(Resources resources, SharedPreferences sharedPreferences) {
        return new StoreUtils(resources, sharedPreferences);
    }

    @Provides
    @Singleton
    LocaleUtils provideLocaleUtils(Resources resources, StoreUtils storeUtils) {
        return new LocaleUtils(resources, storeUtils);
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(Context context) {
        return new NetworkUtils(context);
    }
}
