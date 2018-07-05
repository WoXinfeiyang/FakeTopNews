package com.fxj.faketopnews.presenter;

import com.alibaba.fastjson.JSONObject;
import com.fxj.faketopnews.model.HttpConstant;
import com.fxj.faketopnews.utils.PreferenceUtils;
import com.fxj.faketopnews.view_inface.INewsList;
import com.socks.library.KLog;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public class NewsListPresenter extends BasePresenter<INewsList> {
    private final String tag=NewsListPresenter.class.getSimpleName()+"_fxj";
    private final String KEY_REQUEST_NEWS_LIST_LAST_TIME="key_request_news_list_last_time";


    /**上一次请求时间*/
    private long mRequestNewsListLastTime;

    Disposable getNewsListDisposable;

    public NewsListPresenter(INewsList mView) {
        super(mView);
    }

    @Override
    protected void onUnSubscribe() {
        if(getNewsListDisposable!=null){
            getNewsListDisposable.dispose();/*解除订阅关系*/
        }
    }

    /***/
    public void getNewsList(String mCategoryCode){
        mRequestNewsListLastTime= PreferenceUtils.getLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,0);
        if(mRequestNewsListLastTime ==0){
            mRequestNewsListLastTime =System.currentTimeMillis()/1000;
        }
        final RequestParams params=new RequestParams(new HttpCycleContext(){

            @Override
            public String getHttpTaskKey() {
                return "HttpTaskKey_123" + hashCode();
            }
        });

        params.addFormDataPart("category",mCategoryCode);
        params.addFormDataPart("min_behot_time", mRequestNewsListLastTime);
        params.addFormDataPart("last_refresh_sub_entrance",System.currentTimeMillis());

        addSubscription(Observable.create(new ObservableOnSubscribe<JSONObject>() {
            @Override
            public void subscribe(final ObservableEmitter<JSONObject> emitter) throws Exception {
                HttpRequest.post(HttpConstant.GET_ARTICLE_LIST, params, new JsonHttpRequestCallback() {
                    @Override
                    protected void onSuccess(JSONObject jsonObject) {
                        super.onSuccess(jsonObject);
                        KLog.i(tag, jsonObject.toJSONString());
                        emitter.onNext(jsonObject);
                    }

                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        KLog.i(tag, msg);
                        emitter.onError(new Throwable("errorCode:" + errorCode + ",msg:" + msg));
                    }
                });
            }
        }), new Observer<JSONObject>() {
            @Override
            public void onSubscribe(Disposable d) {
                getNewsListDisposable=d;
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                KLog.i(tag,jsonObject.toJSONString());
                PreferenceUtils.setLong(KEY_REQUEST_NEWS_LIST_LAST_TIME,System.currentTimeMillis()/1000);
                mView.onGetNewsListSuccess(jsonObject);
            }

            @Override
            public void onError(Throwable e) {
                String errorMsg=e.getMessage();
                KLog.i(tag,errorMsg);
                mView.onGetNewsListFailed(errorMsg);
            }

            @Override
            public void onComplete() {
                KLog.i(tag,"getNewsList--Observer.onComplete");
            }
        });
    }

}
