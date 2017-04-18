package com.vlad1m1r.baselibrary.utils;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vlad1m1r.baselibrary.R;

import java.util.Locale;

import static android.text.TextUtils.concat;

/**
 * Created by vladimirjovanovic on 10/23/15.
 */
public class StoreUtils {

    protected static final String KEY_LANGUAGE_CODE = "language_code";

    protected static final String KEY_FIRST_TIME = "first_time";

    protected final SharedPreferences preferences;
    protected final Resources resources;

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
        return  resources.getStringArray(R.array.language_codes)[0];
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
