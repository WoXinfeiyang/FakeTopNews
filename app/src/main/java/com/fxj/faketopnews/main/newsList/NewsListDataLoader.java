package com.fxj.faketopnews.main.newsList;

import com.alibaba.fastjson.JSON;
import com.fxj.faketopnews.model.HttpConstant;
import com.fxj.faketopnews.model.bean.NewsListBean;
import com.fxj.faketopnews.model.bean.NewsDataBean;
import com.fxj.faketopnews.model.bean.NewsListBean;
import com.fxj.faketopnews.model.bean.NewsListTipsBean;
import com.fxj.faketopnews.utils.PreferenceUtils;
import com.fxj.faketopnews.views.RefreshListView.IDataLoader;
import com.fxj.faketopnews.views.RefreshListView.RefreshListView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

public class NewsListDataLoader implements IDataLoader<NewsListBean,NewsListBean> {

    private final String tag=NewsListDataLoader.class.getSimpleName()+"_fxj";

    private String mCategoryCode;

    private long mLastTime;
    private final String KEY_REQUEST_NEWS_LIST_LAST_TIME="key_request_news_list_last_time";


    public NewsListDataLoader(String mCategoryCode) {
        this.mCategoryCode = mCategoryCode;
    }

    public void setCategoryCode(String categoryCode){
        this.mCategoryCode=categoryCode;
    }

    @Override
    public void headerDataLoad(final RefreshListView.OnDataLoaderCallback<NewsListBean,NewsListBean> onDataLoaderCallback) {
        this.mLastTime= PreferenceUtils.getLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,0);
        if(mLastTime==0){
            this.mLastTime=System.currentTimeMillis()/1000;
        }

        BaseHttpRequestCallback<NewsListBean> mHttpRequestCallback = new BaseHttpRequestCallback<NewsListBean>(){
            @Override
            protected void onSuccess(NewsListBean data) {
                super.onSuccess(data);
                KLog.i(tag,"NewsListDataLoader#headerDataLoad.onSuccess="+data.toString());
                PreferenceUtils.setLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,System.currentTimeMillis()/1000);

                if(onDataLoaderCallback!=null){
                    if(data!=null&&data.message.equals("success")&&data.data.size()>0){
                        onDataLoaderCallback.onHeaderDataLoaderCallback(data,data.message.equals("success"),data.has_more);
                    }else{
                        onDataLoaderCallback.onHeaderDataLoaderCallback(null,false,false);
                    }


                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                KLog.i(tag,"NewsListDataLoader#headerDataLoad.onFailure errorCode="+errorCode+",msg="+msg);

            }
        };
        if(this.mCategoryCode==null){
            throw new IllegalArgumentException("mCategoryCode参数不能为空!");
        }
        getNewsList(this.mCategoryCode,this.mLastTime,System.currentTimeMillis()/1000,mHttpRequestCallback);

    }

    @Override
    public void footerDataLoad(final RefreshListView.OnDataLoaderCallback<NewsListBean,NewsListBean> onDataLoaderCallback) {
        this.mLastTime= PreferenceUtils.getLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,0);
        if(mLastTime==0){
            this.mLastTime=System.currentTimeMillis()/1000;
        }

        BaseHttpRequestCallback<NewsListBean> mHttpRequestCallback = new BaseHttpRequestCallback<NewsListBean>(){
            @Override
            protected void onSuccess(NewsListBean data) {
                super.onSuccess(data);
                KLog.i(tag,"NewsListDataLoader#footerDataLoad.onSuccess="+data.toString());
                PreferenceUtils.setLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,System.currentTimeMillis()/1000);

                if(onDataLoaderCallback!=null){
                    if(data!=null&&data.message.equals("success")&&data.data.size()>0){
                        onDataLoaderCallback.onFooterDataLoaderCallback(data);
                    }else{
                        onDataLoaderCallback.onFooterDataLoaderCallback(null);
                    }
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                KLog.i(tag,"NewsListDataLoader#footerDataLoad.onFailure errorCode="+errorCode+",msg="+msg);
            }
        };
        if(this.mCategoryCode==null){
            throw new IllegalArgumentException("mCategoryCode参数不能为空!");
        }
        getNewsList(this.mCategoryCode,this.mLastTime,System.currentTimeMillis()/1000,mHttpRequestCallback);
    }

    private void getNewsList(String categoryCode,long lastTime,long currentTime,BaseHttpRequestCallback<NewsListBean> mHttpRequestCallback){
        RequestParams requestParams=new RequestParams(new HttpCycleContext(){

            @Override
            public String getHttpTaskKey() {
                return "HttpTaskKey_123" + hashCode();
            }
        });

        requestParams.addFormDataPart("category",categoryCode);
        requestParams.addFormDataPart("min_behot_time",lastTime);
        requestParams.addFormDataPart("last_refresh_sub_entrance",currentTime);
        HttpRequest.post(HttpConstant.GET_ARTICLE_LIST,requestParams,mHttpRequestCallback);
    }
}
