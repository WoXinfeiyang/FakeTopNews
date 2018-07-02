package com.fxj.faketopnews.utils;


import android.os.Handler;
import android.os.Looper;

import com.fxj.faketopnews.Base.BaseApplication;

import static com.fxj.faketopnews.Base.BaseApplication.*;

/**
 * Created by fuxianjin-hj on 2018/6/29.
 */

public class UiUtils {

    private static Handler handler;

    public static Handler getMainHandler() {
        if(handler==null){
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    public static void runOnUiThread(Runnable runnable){
        if(runnable==null){
            return;
        }

        if(Thread.currentThread().getId()==Looper.getMainLooper().getThread().getId()){
            runnable.run();
        }else{
            getMainHandler().post(runnable);
        }
    }

    /**dp转px*/
    public static float dp2px(float dp){
       final float density=BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
       return (dp*density+0.5f);
    }

    /**px转dp*/
    public static float px2dp(float px){
        final float density=BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (px/density+0.5f);
    }


}
