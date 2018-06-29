package com.fxj.faketopnews.utils;


import android.os.Handler;
import android.os.Looper;

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


}
