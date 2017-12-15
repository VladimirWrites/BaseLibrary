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

package com.vlad1m1r.baselibrary.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

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
        checkNotNull(list);
        this.list = list;
        notifyDataSetChanged();
    }
}
