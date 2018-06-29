package com.fxj.faketopnews.Base;

import android.app.Activity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by fuxianjin-hj on 2018/6/29.
 */

public abstract class BaseActivity<P extends BasePresenter> extends Activity {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();/*Presenter释放对Activity的引用,避免产生内存泄露*/
        }
    }

    /**创建Presenter,抽象方法,供子类实现*/
    protected abstract P createPresenter();

    public boolean isEventBusRegistered(Object subscriber){
        return EventBus.getDefault().isRegistered(subscriber);
    }

    public void registerEventBus(Object subscriber){
        EventBus.getDefault().register(subscriber);
    }

    public void unregisterEventBus(Object subscriber){
        EventBus.getDefault().unregister(subscriber);
    }
}
