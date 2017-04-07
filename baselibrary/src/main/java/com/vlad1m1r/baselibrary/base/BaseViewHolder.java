package com.vlad1m1r.baselibrary.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vladimirjovanovic on 12/20/16.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected final ViewDataBinding binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
