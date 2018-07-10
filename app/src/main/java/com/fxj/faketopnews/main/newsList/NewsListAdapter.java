package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fxj.faketopnews.model.bean.NewsContentBean;
import com.fxj.faketopnews.views.RefreshListView.RefreshListAdapter;
import com.fxj.faketopnews.views.RefreshListView.RefreshListView;
import com.fxj.faketopnews.views.RefreshListView.RefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

public class NewsListAdapter extends RefreshListAdapter<NewsContentBean,NewsContentBean> {
    /**纯文字布局(文章,广告)*/
    private static final int ITEM_VIEW_TYPE_TEXT_NEWS=0;

    private List<NewsContentBean> mNewsConteList=new ArrayList<NewsContentBean>();



    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getDataItemCount() {
        return mNewsConteList.size();
    }

    @Override
    protected int getDataItemViewType(int position) {
        return ITEM_VIEW_TYPE_TEXT_NEWS;
    }

    @Override
    protected RefreshViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View itemView=null;
        switch (viewType){
            case ITEM_VIEW_TYPE_TEXT_NEWS:
                ItemTextNewsView mItemTextNewsView=new ItemTextNewsView(this.mContext);
                itemView=mItemTextNewsView;
                break;
        }
        return new RefreshViewHolder(itemView);
    }

    @Override
    protected void onBindDataViewHolder(RefreshViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        NewsContentBean itemNewsContent=this.mNewsConteList.get(position);
        switch (viewType){
            case ITEM_VIEW_TYPE_TEXT_NEWS:
                ((ItemTextNewsView)holder.itemView).updateView(itemNewsContent);
                break;
        }
    }

    @Override
    protected void processDataRefresh(List<NewsContentBean> headerData) {
        mNewsConteList.clear();
        mNewsConteList.addAll(headerData);
        notifyDataSetChanged();
    }

    @Override
    protected void processDataAdded(List<NewsContentBean> footerData) {
        this.mNewsConteList.addAll(footerData);
    }




}
