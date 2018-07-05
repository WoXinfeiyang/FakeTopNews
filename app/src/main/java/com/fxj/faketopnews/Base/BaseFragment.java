package com.fxj.faketopnews.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.fxj.faketopnews.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment抽象基类
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

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
