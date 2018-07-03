package com.fxj.faketopnews.Base;

import android.app.Application;
import android.content.Context;

import com.fxj.faketopnews.BuildConfig;
import com.socks.library.KLog;



public class BaseApplication extends Application {
    private static final String tag=BaseApplication.class.getSimpleName()+"_fxj";
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
        appContext=getApplicationContext();
        KLog.i(tag,"**BaseApplication.onCreate**appContext="+appContext);
    }

    public static Context getAppContext(){
        KLog.i(tag,"**BaseApplication#onCreate**getAppContext="+appContext);
        return appContext;
    }
}
