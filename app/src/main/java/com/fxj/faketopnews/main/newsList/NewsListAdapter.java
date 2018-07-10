package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.fxj.faketopnews.model.bean.NewsListBean;
import com.fxj.faketopnews.views.RefreshListView.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

public class NewsListAdapter extends RefreshListView.RefreshListAdapter<NewsListBean,NewsListBean> {

    private List<NewsListBean> mNewsConteList=new ArrayList<NewsListBean>();

    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getDataItemCount() {
        return 0;
    }

    @Override
    protected int getDataItemViewType(int position) {
        return 0;
    }

    @Override
    protected RefreshListView.RefreshViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindDataViewHolder(RefreshListView.RefreshViewHolder holder, int position) {

    }

    @Override
    protected void processDataRefresh(NewsListBean headerData) {

    }

    @Override
    protected void processDataAdded(NewsListBean footerData) {

    }




}
