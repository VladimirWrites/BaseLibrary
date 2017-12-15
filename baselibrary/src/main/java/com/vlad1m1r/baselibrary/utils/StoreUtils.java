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

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vlad1m1r.baselibrary.R;

import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

public class StoreUtils {

    protected static final String KEY_LANGUAGE_CODE = "language_code";

    protected static final String KEY_FIRST_TIME = "first_time";

    protected final SharedPreferences preferences;
    protected final Resources resources;

    public StoreUtils(Resources resources, SharedPreferences preferences) {
        this.preferences = checkNotNull(preferences);
        this.resources = checkNotNull(resources);
    }

    public String getDefaultLanguage() {
        String defaultLanguage = Locale.getDefault().getLanguage();
        final String[] languageCodes = resources.getStringArray(R.array.language_codes);
        for (String languageCode : languageCodes) {
            if (defaultLanguage.contains(languageCode)) {
                return languageCode;
            }
        }
        return  resources.getStringArray(R.array.language_codes)[0];
    }

    @NonNull
    public String getLanguageCode() {
        return this.preferences.getString(KEY_LANGUAGE_CODE, getDefaultLanguage());
    }

    public void setLanguageCode(String languageCode) {
        checkNotNull(languageCode);
        SharedPreferences.Editor editPreferences = this.preferences.edit();
        editPreferences.putString(KEY_LANGUAGE_CODE, languageCode);
        editPreferences.apply();
    }

    public boolean isFirstTime(@Nullable String event) {
        return this.preferences.getBoolean(
                event != null ?
                        KEY_FIRST_TIME.concat("_").concat(event) :
                        KEY_FIRST_TIME,
                true
        );
    }

    public boolean isFirstTime() {
        return isFirstTime(null);
    }

    public void setFirstTime(String event, boolean isFirstTime) {
        SharedPreferences.Editor editPreferences = this.preferences.edit();
        editPreferences.putBoolean(
                event != null ?
                        KEY_FIRST_TIME.concat("_").concat(event) :
                        KEY_FIRST_TIME,
                isFirstTime
        );
        editPreferences.apply();
    }

    public void setFirstTime(boolean isFirstTime) {
        setFirstTime(null, isFirstTime);
    }
}
