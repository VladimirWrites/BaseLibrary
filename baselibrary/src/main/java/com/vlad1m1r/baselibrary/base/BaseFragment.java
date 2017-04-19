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

package com.vlad1m1r.baselibrary.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;

import static com.google.firebase.analytics.FirebaseAnalytics.getInstance;

public abstract class BaseFragment<P extends IBasePresenter, B extends ViewDataBinding> extends Fragment {

    public interface IFragmentHolder {
        void invalidateOptionsMenu();
        void setupToolbar(boolean homeAsUp, Toolbar toolbar);
        ActionBar getFragmentActionBar();
        void refreshFragment();
    }

    protected P presenter;
    protected B binding;


    protected IFragmentHolder fragmentHolder;

    protected FirebaseAnalytics firebaseAnalytics;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAnalytics = getInstance(getContext());
        this.setRetainInstance(retainInstance());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentHolder = (IFragmentHolder) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentHolder = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getPresenter() != null) getPresenter().onStart();
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null) getPresenter().onDestroy();
        super.onDestroy();
    }

    protected boolean retainInstance() {
        return true;
    }

    protected IBasePresenter getPresenter() {
        return presenter;
    }

}
