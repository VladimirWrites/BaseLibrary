package com.vlad1m1r.baselibrary.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private int startPageNumber = 0;
    private int currentPage = 0;

    private int scrollPosition = 0;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private int[] spanArray;

    public EndlessScrollListener(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            this.gridLayoutManager = (GridLayoutManager) layoutManager;
        } else if (layoutManager instanceof LinearLayoutManager) {
            this.linearLayoutManager = (LinearLayoutManager) layoutManager;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            this.staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            spanArray = new int[staggeredGridLayoutManager.getSpanCount()];
        } else {
            throw new IllegalArgumentException("Selected LayoutManager has not supported");
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();

        if (linearLayoutManager != null) {
            totalItemCount = linearLayoutManager.getItemCount();
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        } else if (gridLayoutManager != null) {
            totalItemCount = gridLayoutManager.getItemCount();
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
        } else if (staggeredGridLayoutManager != null) {
            totalItemCount = staggeredGridLayoutManager.getItemCount();
            firstVisibleItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(spanArray)[0];
        }

        scrollPosition = recyclerView.computeVerticalScrollOffset();
        onScroll(firstVisibleItem, dy, scrollPosition);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            currentPage++;

            onLoadMore(currentPage, totalItemCount);

            loading = true;
        }
    }

    public void reset() {
        currentPage = startPageNumber;
        this.previousTotal = 0;
        this.loading = true;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setStartPageNumber(int startPageNumber, boolean restart) {
        this.startPageNumber = startPageNumber;
        if (restart)
            reset();
    }

    public abstract void onLoadMore(int currentPage, int totalItemCount);

    public abstract void onScroll(int firstVisibleItem, int dy, int scrollPosition);
}
