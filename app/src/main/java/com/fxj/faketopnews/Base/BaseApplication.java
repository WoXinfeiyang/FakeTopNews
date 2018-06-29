package com.fxj.faketopnews.Base;

import android.app.Application;

import com.fxj.faketopnews.BuildConfig;
import com.socks.library.KLog;



public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
    }
}
