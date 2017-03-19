package com.vlad1m1r.baselibrary.sample.test;

public class TestPresenter implements TestContract.Presenter {

    private TestContract.View mView;

    public TestPresenter(TestContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}