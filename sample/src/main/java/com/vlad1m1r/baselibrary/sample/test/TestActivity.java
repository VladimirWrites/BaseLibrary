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