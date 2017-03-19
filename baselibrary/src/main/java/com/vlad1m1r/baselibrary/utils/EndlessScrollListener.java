package com.vlad1m1r.baselibrary.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessScrollListener.class.getSimpleName();

    private int mPreviousTotal = 0; // The total number of items in the dataset after the last load
    private boolean mLoading = true; // True if we are still waiting for the last set of data to load.
    private int mVisibleThreshold = 5; // The minimum amount of items to have below your current scroll position before mLoading more.
    private int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;

    private int mStartPageNumber = 0;
    private int mCurrentPage = 0;

    private int mScrollPosition = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private int[] mSpanArray;

    public EndlessScrollListener(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            this.mGridLayoutManager = (GridLayoutManager) layoutManager;
        } else if (layoutManager instanceof LinearLayoutManager) {
            this.mLinearLayoutManager = (LinearLayoutManager) layoutManager;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            this.mStaggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            mSpanArray = new int[mStaggeredGridLayoutManager.getSpanCount()];
        } else {
            throw new IllegalArgumentException("Selected LayoutManager has not supported");
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();

        if (mLinearLayoutManager != null) {
            mTotalItemCount = mLinearLayoutManager.getItemCount();
            mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        } else if (mGridLayoutManager != null) {
            mTotalItemCount = mGridLayoutManager.getItemCount();
            mFirstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
        } else if (mStaggeredGridLayoutManager != null) {
            mTotalItemCount = mStaggeredGridLayoutManager.getItemCount();
            mFirstVisibleItem = mStaggeredGridLayoutManager.findFirstVisibleItemPositions(mSpanArray)[0];
        }

        mScrollPosition = recyclerView.computeVerticalScrollOffset();
        onScroll(mFirstVisibleItem, dy, mScrollPosition);

        if (mLoading) {
            if (mTotalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = mTotalItemCount;
            }
        }
        if (!mLoading && (mTotalItemCount - mVisibleItemCount)
                <= (mFirstVisibleItem + mVisibleThreshold)) {
            // End has been reached

            // Do something
            mCurrentPage++;

            onLoadMore(mCurrentPage, mTotalItemCount);

            mLoading = true;
        }
    }

    public void reset() {
        mCurrentPage = mStartPageNumber;
        this.mPreviousTotal = 0;
        this.mLoading = true;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
    }

    public void setStartPageNumber(int startPageNumber, boolean restart) {
        this.mStartPageNumber = startPageNumber;
        if (restart)
            reset();
    }

    public abstract void onLoadMore(int currentPage, int totalItemCount);

    public abstract void onScroll(int firstVisibleItem, int dy, int scrollPosition);
}
