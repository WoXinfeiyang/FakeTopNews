package com.fxj.faketopnews.Base;

import android.app.Application;

import com.fxj.faketopnews.BuildConfig;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/6/28.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
    }
}
