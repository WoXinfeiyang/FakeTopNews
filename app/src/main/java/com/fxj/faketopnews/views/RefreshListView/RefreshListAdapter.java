package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

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

    public void onDataRefresh(List<H> headerData, boolean success, boolean hasMore){
        this.success=success;
        this.hasMore=hasMore;
        if(headerData!=null){
            processDataRefresh(headerData);
        }
    }

    public void onDataAdded(List<F> footerData){
        if(footerData!=null){
            processDataAdded(footerData);
        }
        notifyItemChanged(getItemCount()-1);
    }



    protected abstract int getDataItemCount();
    protected abstract int getDataItemViewType(int position);
    protected abstract RefreshViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType);
    protected abstract void onBindDataViewHolder(RefreshViewHolder holder, int position);

    protected abstract void processDataRefresh(List<H> headerData);
    protected abstract void processDataAdded(List<F> footerData);


}


