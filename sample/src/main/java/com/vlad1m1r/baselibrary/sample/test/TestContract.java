package com.vlad1m1r.baselibrary.sample.test;

import com.vlad1m1r.baselibrary.base.IBasePresenter;
import com.vlad1m1r.baselibrary.base.IBaseView;

public interface TestContract {

    interface Presenter extends IBasePresenter {

    }

    interface View<T extends Presenter> extends IBaseView {
        void setPresenter(T presenter);
    }
}