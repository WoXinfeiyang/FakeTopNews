package com.fxj.faketopnews.Base;

import android.app.Application;
import android.content.Context;

import com.fxj.faketopnews.BuildConfig;
import com.socks.library.KLog;



public class BaseApplication extends Application {

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
        this.appContext=getApplicationContext();
    }

    public static Context getAppContext(){
     return appContext;
    }
}
