package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.fxj.faketopnews.R;


public class RefreshListView extends RelativeLayout {

    private final String tag=RefreshListView.class.getSimpleName();

    private SwipeToLoadLayout mSwipeToLoadLayout;

    private RefreshListViewHeader mRefreshListViewHeader;
    private RecyclerView mRecyclerView;


    public RefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**网络请求回调接口*/
    public interface OnDataLoaderCallback<H,F>{
        public void onHeaderDataLoaderCallback(H data);
        public void onFooterDataLoaderCallback(F data);
    }

    private void initView(Context ct){
        LayoutInflater.from(ct).inflate(R.layout.refresh_list_view_layout,this,true);
        this.mSwipeToLoadLayout=findViewById(R.id.swip_to_load_layout);
        this.mRecyclerView=(RecyclerView) findViewById(R.id.swipe_target);
    }
}
