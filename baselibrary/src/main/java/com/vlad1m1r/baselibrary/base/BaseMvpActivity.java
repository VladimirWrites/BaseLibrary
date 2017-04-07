package com.vlad1m1r.baselibrary.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vlad1m1r.baselibrary.R;
import com.vlad1m1r.baselibrary.databinding.ActivityBaseBinding;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by vladimirjovanovic on 10/19/15.
 */
public abstract class BaseMvpActivity<P extends IBasePresenter, F extends BaseFragment> extends BaseActivity implements BaseFragment.IFragmentHolder {

    protected P presenter;
    protected ActivityBaseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId() == 0 ? R.layout.activity_base : getLayoutId());

        F fragment = (F) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = getFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, fragment);
            transaction.commit();
        }

        if (fragment.getPresenter() != null) {
            presenter = (P) fragment.getPresenter();
        } else {
            presenter = getPresenter(fragment);
        }
    }

    public void setupToolbar(boolean homeAsUp, Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
    }

    @Override
    public ActionBar getFragmentActionBar() {
        return getSupportActionBar();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void refreshFragment() {

    }

    @LayoutRes
    protected int getLayoutId() {
        return 0;
    }


    protected abstract F getFragment();

    protected abstract P getPresenter(F fragment);
    
}