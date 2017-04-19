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

import com.vlad1m1r.baselibrary.base.BaseMvpActivity;

public class TestActivity extends BaseMvpActivity<TestContract.Presenter, TestFragment> {

    @Override
    protected TestFragment getFragment() {
        return TestFragment.newInstance();
    }

    @Override
    protected TestContract.Presenter getPresenter(TestFragment fragment) {
        return new TestPresenter(fragment);
    }
}