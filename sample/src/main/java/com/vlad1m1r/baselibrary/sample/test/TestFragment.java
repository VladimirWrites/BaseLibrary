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
        mBinding = FragmentTestBinding.bind(view);

        return view;
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {

    }
}