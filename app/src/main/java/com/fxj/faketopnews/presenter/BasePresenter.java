package com.fxj.faketopnews.presenter;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter基类,泛型V表示View，通常为一个接口
 * @param <V> View
 * */
public abstract class BasePresenter<V> {
    /**View*/
    protected V mView;

    private ArrayCompositeSubscription mCompositionSubscription;

    public BasePresenter(V mView) {
        attachView(mView);
    }

    public void attachView(V mView){
        this.mView=mView;
    }

    public void detachView(){
        this.mView=null;
        onUnSubscribe();
    }

    /**取消订阅,抽象方法,供子类实现*/
    protected abstract void onUnSubscribe();


    public void addSubscription(Observable observable, Observer observer){
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
