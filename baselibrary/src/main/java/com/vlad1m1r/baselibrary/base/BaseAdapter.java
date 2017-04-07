package com.vlad1m1r.baselibrary.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by vladimirjovanovic on 12/20/16.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, H> extends RecyclerView.Adapter<T> {

    protected ArrayList<H> list;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<H> getList() {
        return list;
    }

    public void setList(ArrayList<H> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
