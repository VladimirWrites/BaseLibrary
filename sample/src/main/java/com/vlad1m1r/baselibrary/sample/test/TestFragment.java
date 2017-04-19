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

package com.vlad1m1r.baselibrary.sample.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad1m1r.baselibrary.base.BaseFragment;
import com.vlad1m1r.baselibrary.sample.R;
import com.vlad1m1r.baselibrary.sample.databinding.FragmentTestBinding;

public class TestFragment extends BaseFragment<TestContract.Presenter, FragmentTestBinding> implements TestContract.View {


    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        this.binding = FragmentTestBinding.bind(view);

        return view;
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {

    }
}