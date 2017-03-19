package com.vlad1m1r.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.vlad1m1r.baselibrary.dagger.DaggerLibWrapper;
import com.vlad1m1r.baselibrary.utils.LocaleUtils;

import javax.inject.Inject;

/**
 * Created by vladimirjovanovic on 3/17/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    protected LocaleUtils mLocaleUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        DaggerLibWrapper.getDataComponent().inject(this);
        mLocaleUtils.setResources(getResources());
    }
}
