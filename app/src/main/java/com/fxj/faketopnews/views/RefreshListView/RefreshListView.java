package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.fxj.faketopnews.R;
import com.socks.library.KLog;


public class RefreshListView<H,F> extends RelativeLayout {

    private final String tag=RefreshListView.class.getSimpleName()+"_fxj";

    private SwipeToLoadLayout mSwipeToLoadLayout;

    private RefreshListViewHeader mRefreshListViewHeader;
    private RecyclerView mRecyclerView;
    private IDataLoader<H, F> mDataLoader;
    private RefreshListAdapter<H, F> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;


    private OnDataLoaderCallback<H,F> mOnDataLoaderCallback=new OnDataLoaderCallback<H, F>() {
        @Override
        public void onHeaderDataLoaderCallback(H data, boolean success, boolean hasMore) {
            /*从网络上获取到数据之后修改UI*/
            mAdapter.onDataRefresh(data,success,hasMore);
            mSwipeToLoadLayout.setRefreshing(false);
        }

        @Override
        public void onFooterDataLoaderCallback(F data) {
            mAdapter.onDataAdded(data);
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
    public interface OnDataLoaderCallback<H,F>{
        public void onHeaderDataLoaderCallback(H data,boolean success,boolean hasMore);
        public void onFooterDataLoaderCallback(F data);
    }

    private void initView(Context ct){
        LayoutInflater.from(ct).inflate(R.layout.refresh_list_view_layout,this,true);
        this.mSwipeToLoadLayout=(SwipeToLoadLayout) findViewById(R.id.swip_to_load_layout);
        this.mRecyclerView=(RecyclerView) findViewById(R.id.swipe_target);
    }

    public void init(IDataLoader<H,F> dataLoader, RefreshListAdapter<H,F> adapter, RecyclerView.LayoutManager layoutManager, final RecyclerView.ItemDecoration itemDecoration){
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

    public abstract class RefreshListAdapter<H,F> extends RecyclerView.Adapter<RefreshViewHolder>{

        private final String tag=RefreshListAdapter.class.getSimpleName()+"_fxj";
        protected final int ITEM_VIEW_TYPE_LOADING=Integer.MAX_VALUE;

        protected Context mContext;
        protected boolean success;
        protected boolean hasMore;

        public RefreshListAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getItemViewType(int position) {
            if(position==getItemCount()-1){
                return ITEM_VIEW_TYPE_LOADING;
            }
            return getDataItemViewType(position);
        }

        @Override
        public RefreshViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==ITEM_VIEW_TYPE_LOADING){
                AdaperLoadingView loadingView=new AdaperLoadingView(this.mContext);
                return new RefreshViewHolder(loadingView);
            }else{
                return onCreateDataViewHolder(parent,viewType);
            }
        }

        @Override
        public void onBindViewHolder(RefreshViewHolder holder, int position) {
            if(position==getItemCount()-1){
                AdaperLoadingView loadingView = (AdaperLoadingView) holder.itemView;
                loadingView.update();
            }else{
                onBindDataViewHolder(holder,position);
            }
        }

        @Override
        public int getItemCount() {
            return getDataItemCount()+1;
        }

        public void onDataRefresh(H headerData,boolean success,boolean hasMore){
            this.success=success;
            this.hasMore=hasMore;
            if(headerData!=null){
                processDataRefresh(headerData);
            }
        }

        public void onDataAdded(F footerData){
            if(footerData!=null){
                processDataAdded(footerData);
            }
            notifyItemChanged(getItemCount()-1);
        }



        protected abstract int getDataItemCount();
        protected abstract int getDataItemViewType(int position);
        protected abstract RefreshViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType);
        protected abstract void onBindDataViewHolder(RefreshViewHolder holder, int position);

        protected abstract void processDataRefresh(H headerData);
        protected abstract void processDataAdded(F footerData);
    }

    public class RefreshViewHolder extends RecyclerView.ViewHolder{

        public RefreshViewHolder(View itemView) {
            super(itemView);
        }
    }

}
