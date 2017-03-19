package com.vlad1m1r.baselibrary.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by vladimirjovanovic on 12/20/16.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, H> extends RecyclerView.Adapter<T> {

    protected ArrayList<H> mList;

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ArrayList<H> getList() {
        return mList;
    }

    public void setList(ArrayList<H> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
}
