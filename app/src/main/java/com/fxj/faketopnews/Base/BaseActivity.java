package com.fxj.faketopnews.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.fxj.faketopnews.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by fuxianjin-hj on 2018/6/29.
 */

public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity {

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
