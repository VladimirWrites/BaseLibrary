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

package com.vlad1m1r.baselibrary.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

public class LocaleUtils {

    private Resources resources;
    private final StoreUtils storeUtils;

    public LocaleUtils(Resources resources, StoreUtils storeUtils) {
        this.resources = checkNotNull(resources);
        this.storeUtils = checkNotNull(storeUtils);
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
        checkNotNull(language);
        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            conf.setLocale(new Locale(language));
        else conf.locale = new Locale(language);
        this.resources.updateConfiguration(conf, dm);
        this.storeUtils.setLanguageCode(language);
    }

    public void setResources(Resources resources) {
        this.resources = checkNotNull(resources);
        setLanguage(storeUtils.getLanguageCode());
    }
}
