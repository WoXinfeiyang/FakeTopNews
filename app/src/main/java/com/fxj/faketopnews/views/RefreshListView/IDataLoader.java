package com.fxj.faketopnews.views.RefreshListView;



public interface IDataLoader<H,F> {
    /**头部数据加载接口,负责网络数据请求，并将请求到的数据传递给RefreshListView.OnDataLoaderCallback接口*/
    public void headerDataLoad(RefreshListView.OnDataLoaderCallback<H,F> onDataLoaderCallback);

    public void footerDataLoad(RefreshListView.OnDataLoaderCallback<H,F> onDataLoaderCallback);
}
