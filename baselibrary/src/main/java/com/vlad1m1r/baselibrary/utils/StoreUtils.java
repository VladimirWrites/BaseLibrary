package com.vlad1m1r.baselibrary.utils;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.vlad1m1r.baselibrary.R;

import java.util.Locale;

/**
 * Created by vladimirjovanovic on 10/23/15.
 */
public class StoreUtils {

    private static final String KEY_LANGUAGE_CODE = "language_code";

    private static final String KEY_FIRST_TIME = "first_time";

    private final SharedPreferences preferences;
    private final Resources resources;

    public StoreUtils(@NonNull Resources resources, @NonNull SharedPreferences preferences) {
        this.preferences = preferences;
        this.resources = resources;
    }


    public String getDefaultLanguage() {
        String defaultLanguage = Locale.getDefault().getLanguage();
        final String[] languageCodes = resources.getStringArray(R.array.language_codes);
        for (String languageCode : languageCodes) {
            if (defaultLanguage.contains(languageCode)) {
                return languageCode;
            }
        }
        return  "en";
    }

    @NonNull
    public String getLanguageCode() {
        return this.preferences.getString(KEY_LANGUAGE_CODE, getDefaultLanguage());
    }

    public void setLanguageCode(@NonNull String languageCode) {
        SharedPreferences.Editor editPreferences = this.preferences.edit();
        editPreferences.putString(KEY_LANGUAGE_CODE, languageCode);
        editPreferences.apply();
    }

    public boolean isFirstTime() {
        return this.preferences.getBoolean(KEY_FIRST_TIME, true);
    }

    public void setFirstTime(boolean isFirstTime) {
        SharedPreferences.Editor editPreferences = this.preferences.edit();
        editPreferences.putBoolean(KEY_FIRST_TIME, isFirstTime);
        editPreferences.apply();
    }
}
