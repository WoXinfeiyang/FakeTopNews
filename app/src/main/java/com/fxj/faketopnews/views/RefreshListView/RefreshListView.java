package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;


public class RefreshListView extends RelativeLayout {

    private final String tag=RefreshListView.class.getSimpleName();

    private SwipeToLoadLayout mSwipeToLoadLayout;

    private RefreshListViewHeader mRefreshListViewHeader;
    private RecyclerView mRecyclerView;


    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**网络请求回调接口*/
    public interface OnDataLoaderCallback<H,F>{
        public void onHeaderDataLoaderCallback(H data);
        public void onFooterDataLoaderCallback(F data);
    }
}
