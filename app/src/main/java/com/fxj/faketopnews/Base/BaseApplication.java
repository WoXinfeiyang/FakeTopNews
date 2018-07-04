package com.fxj.faketopnews.Base;

import android.app.Application;
import android.content.Context;

import com.fxj.faketopnews.BuildConfig;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.Part;
import okhttp3.Headers;
import okhttp3.Interceptor;


public class BaseApplication extends Application {
    private static final String tag=BaseApplication.class.getSimpleName()+"_fxj";
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
        appContext=getApplicationContext();
        KLog.i(tag,"**BaseApplication.onCreate**appContext="+appContext);
        initOkHttpFinal();
    }

    public static Context getAppContext(){
        KLog.i(tag,"**BaseApplication#onCreate**getAppContext="+appContext);
        return appContext;
    }

    private void initOkHttpFinal() {

        List<Part> commomParams = new ArrayList<>();
        Headers commonHeaders = new Headers.Builder().build();
        List<Interceptor> interceptorList = new ArrayList<>();

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder()
                .setCommenParams(commomParams)
                .setCommenHeaders(commonHeaders)
                .setTimeout(3500)
                .setInterceptors(interceptorList)
                //.setCookieJar(CookieJar.NO_COOKIES)
                //.setCertificates(...)
                //.setHostnameVerifier(new SkirtHttpsHostnameVerifier())
                .setDebug(true);

        OkHttpFinal.getInstance().init(builder.build());
    }
}
