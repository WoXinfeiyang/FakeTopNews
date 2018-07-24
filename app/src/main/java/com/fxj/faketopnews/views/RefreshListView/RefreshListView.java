package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.fxj.faketopnews.R;
import com.socks.library.KLog;

import java.util.List;

public class RefreshListView<H,F,T> extends RelativeLayout {

    private final String tag=RefreshListView.class.getSimpleName()+"_fxj";

    private SwipeToLoadLayout mSwipeToLoadLayout;

    private RefreshListViewHeader mRefreshListViewHeader;
    private RecyclerView mRecyclerView;
    private RefreshListViewHeader mHeaderView;
    private IDataLoader<H,F,T> mDataLoader;
    private RefreshListAdapter<H, F> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;


    private OnDataLoaderCallback<H,F,T> mOnDataLoaderCallback=new OnDataLoaderCallback<H,F,T>() {
        @Override
        public void onHeaderDataLoaderCallback(List<H> data, boolean success, boolean hasMore) {
            if(data==null||!success){
                mSwipeToLoadLayout.setRefreshing(false);
            }
            /*从网络上获取到数据之后修改UI*/
            mAdapter.onDataRefresh(data,success,hasMore);
            mSwipeToLoadLayout.setRefreshing(false);
        }

        @Override
        public void onFooterDataLoaderCallback(List<F> data) {
            mAdapter.onDataAdded(data);
        }

        @Override
        public void onTipsDataLoaderCallback(T data) {

        }
    };



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

    /**网络请求回调接口,一般供RefreshListView内部使用*/
    public interface OnDataLoaderCallback<H,F,T>{
        public void onHeaderDataLoaderCallback(List<H> data,boolean success,boolean hasMore);
        public void onFooterDataLoaderCallback(List<F> data);
        /**处理Tips*/
        public void onTipsDataLoaderCallback(T data);
    }

    private void initView(Context ct){
        LayoutInflater.from(ct).inflate(R.layout.refresh_list_view_layout,this,true);
        this.mSwipeToLoadLayout=(SwipeToLoadLayout) findViewById(R.id.swip_to_load_layout);
        this.mRecyclerView=(RecyclerView) findViewById(R.id.swipe_target);
        mHeaderView = (RefreshListViewHeader)findViewById(R.id.swipe_refresh_header);
    }

    public void init(IDataLoader<H,F,T> dataLoader, RefreshListAdapter<H,F> adapter, RecyclerView.LayoutManager layoutManager, final RecyclerView.ItemDecoration itemDecoration){
        mDataLoader = dataLoader;
        mAdapter = adapter;
        mLayoutManager = layoutManager;
        mItemDecoration = itemDecoration;

        this.mRecyclerView.setLayoutManager(mLayoutManager);
        if(this.mItemDecoration!=null){
            this.mRecyclerView.addItemDecoration(this.mItemDecoration);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);

        this.mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                KLog.i(tag,"***RefreshListView#init mSwipetoloadlayout.OnRefreshListener.onRefresh***");
                if(mDataLoader!=null){
                    mDataLoader.headerDataLoad(mOnDataLoaderCallback);
                }
            }
        });

        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean bottomIsReached=false;
                int itemCount=recyclerView.getAdapter().getItemCount();
                if(mLayoutManager instanceof StaggeredGridLayoutManager){
                    int[] lastVisibleItemPositions= ((StaggeredGridLayoutManager)mLayoutManager).findLastVisibleItemPositions(null);
                    for(int itemPosition:lastVisibleItemPositions){
                        if(itemPosition==itemCount-1){
                            bottomIsReached=true;
                            break;
                        }
                    }

                }else if(mLayoutManager instanceof GridLayoutManager){
                   int lastVisibleItemPosition= ((GridLayoutManager)mLayoutManager).findLastVisibleItemPosition();
                    if(lastVisibleItemPosition==itemCount-1){
                        bottomIsReached=true;
                    }
                }else if(mLayoutManager instanceof LinearLayoutManager){
                    int lastVisibleItemPosition=((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();
                    if(lastVisibleItemPosition==itemCount-1){
                        bottomIsReached=true;
                    }
                }

                if(bottomIsReached){
                    mDataLoader.footerDataLoad(mOnDataLoaderCallback);/*滑动到底部开始底部刷新*/
                }
            }
        });

    }

    public void headRefresh(boolean showHeaderAnimation){
        if(this.mAdapter.getDataItemCount()==0){

        }
        if(showHeaderAnimation){
            this.mSwipeToLoadLayout.setRefreshEnabled(true);
            this.mSwipeToLoadLayout.setRefreshing(true);
        }else if(!showHeaderAnimation&&mDataLoader!=null){
            this.mSwipeToLoadLayout.setRefreshEnabled(true);
            this.mSwipeToLoadLayout.setRefreshing(false);
            mDataLoader.headerDataLoad(mOnDataLoaderCallback);
        }
    }

    public void footRefresh(){
        if(this.mDataLoader!=null){
            this.mDataLoader.footerDataLoad(mOnDataLoaderCallback);
        }
    }

    public RecyclerView getRecyclerView(){
        if(this.mRecyclerView==null){
            throw new NullPointerException("RefreshListView中RecyclerView为空!");
        }
        return this.mRecyclerView;
    }

    public RefreshListViewHeader getmRefreshListViewHeader(){
        if(this.mHeaderView==null){
            throw new NullPointerException("RefreshListView中RefreshListViewHeader为空!");
        }
        return this.mHeaderView;
    }
}
