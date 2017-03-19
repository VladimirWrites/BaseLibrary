package com.vlad1m1r.baselibrary.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.vlad1m1r.baselibrary.R;
import com.vlad1m1r.baselibrary.databinding.ActivityBaseBinding;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by vladimirjovanovic on 10/19/15.
 */
public abstract class BaseMvpActivity<P extends IBasePresenter, F extends BaseFragment> extends BaseActivity implements BaseFragment.IFragmentHolder {

    protected P mPresenter;
    protected ActivityBaseBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, getLayoutId() == 0 ? R.layout.activity_base : getLayoutId());

        F fragment = (F) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = getFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, fragment);
            transaction.commit();
        }

        if (fragment.getPresenter() != null) {
            mPresenter = (P) fragment.getPresenter();
        } else {
            mPresenter = getPresenter(fragment);
        }
    }

    public void setupToolbar(boolean homeAsUp, Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getColorFromAttr(this, R.attr.colorPrimary));
        toolbar.setTitleTextColor(getColorFromAttr(this, R.attr.titleTextColor));
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

    private static int getColorFromAttr(Context context, int attrRes) {

        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{attrRes});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }
}