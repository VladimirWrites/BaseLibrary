package com.vlad1m1r.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.vlad1m1r.baselibrary.R;

import java.util.Locale;

/**
 * Created by vladimirjovanovic on 10/23/15.
 */
public class StoreUtils {

    private static final String KEY_LANGUAGE_CODE = "language_code";

    private static final String KEY_FIRST_TIME = "first_time";

    private final SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditPreferences;
    private final Context mContext;

    public StoreUtils(@NonNull Context context, @NonNull SharedPreferences preferences) {
        this.mPreferences = preferences;
        this.mContext = context;
    }

    @NonNull
    public String getLanguageCode() {
        String defaultLanguage = Locale.getDefault().getLanguage();
        final String[] languageCodes = mContext.getResources().getStringArray(R.array.language_codes);
        boolean isInside = false;
        for (String languageCode : languageCodes) {
            if (defaultLanguage.contains(languageCode)) {
                defaultLanguage = languageCode;
                isInside = true;
            }
        }
        if (!isInside) defaultLanguage = "en";


        return this.mPreferences.getString(KEY_LANGUAGE_CODE, defaultLanguage);
    }

    public void setLanguageCode(@NonNull String languageCode) {
        SharedPreferences.Editor editPreferences = this.mPreferences.edit();
        editPreferences.putString(KEY_LANGUAGE_CODE, languageCode);
        editPreferences.apply();
    }

    public boolean isFirstTime() {
        return this.mPreferences.getBoolean(KEY_FIRST_TIME, true);
    }

    public void setFirstTime(boolean isFirstTime) {
        SharedPreferences.Editor editPreferences = this.mPreferences.edit();
        editPreferences.putBoolean(KEY_FIRST_TIME, isFirstTime);
        editPreferences.apply();
    }
}
