package com.fxj.faketopnews.utils;


import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import com.fxj.faketopnews.Base.BaseApplication;
import com.socks.library.KLog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogCollectUtils {

    private final String TAG=LogCollectUtils.class.getSimpleName()+"_fxj";

    private static volatile LogCollectUtils sInstance;

    protected String appDirPath;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private final static String DEFAULT_LOG_FOLDER_NAME="LogFolder";

    private Date mDate;
    private SimpleDateFormat mDayDateFormat=new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat mTimeDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void LogCollectUtils() {
        initHandlerThread();
    }
    /*初始化HandlerThread和Handler,将对文件的读写操作放在子线程中*/
    public void initHandlerThread(){
        if(mHandlerThread==null){
            mHandlerThread=new HandlerThread("LogCollect");
        }
        mHandlerThread.start();

        if(mHandler==null){
            mHandler=new Handler(mHandlerThread.getLooper()){
                @Override
                public void handleMessage(Message msg)
                {
                    if(msg!=null){
                        Bundle bundle=msg.getData();
                        String log=bundle.getString("log");
                        String filePath=bundle.getString("filePath");
                        KLog.i(TAG,"Message:"+msg+",log:"+log+",filePath:"+filePath);
                        FileUtils.writeDataToFile(filePath,"["+getTimeDateString()+"]:"+log+"\n");
                    }
                }
            };
        }
    }

    public static LogCollectUtils getInstance(){
        if(sInstance==null){
            synchronized (LogCollectUtils.class){
                if(sInstance==null){
                    sInstance=new LogCollectUtils();
                }
            }
        }
        return sInstance;
    }

    public String getAppDirPath(){
        if(appDirPath==null){
            appDirPath=FileUtils.getAppDir(BaseApplication.getAppContext(),"FakeTopNews");
        }
        KLog.i(TAG,"appDirPath="+appDirPath);
        return appDirPath;
    }

    public String getLogFolderPath(String mLogFolderName){
        String log_folder_path=getAppDirPath()+mLogFolderName+ File.separator;
        FileUtils.makeDir(log_folder_path);
        return log_folder_path;
    }

    public String getLogFolderPath(){
        return getLogFolderPath(DEFAULT_LOG_FOLDER_NAME);
    }

    public void collectLog(String log){
        if(TextUtils.isEmpty(log)){
            return;
        }
        Message msg=Message.obtain();
        Bundle bundle=new Bundle();
        bundle.putString("log",log);
        bundle.putString("filePath",getLogFolderPath()+getDayDateString()+".txt");
        msg.setData(bundle);
        if(mHandler==null||mHandlerThread==null){/*每次使用Handler发送消息之前对Handder进行判空检查*/
            initHandlerThread();
        }
        mHandler.sendMessage(msg);
    }

    /**获取以日为单位的时间字符串*/
    private String getDayDateString(){
        if(mDate==null){
            this.mDate=new Date(System.currentTimeMillis());
        }else{
            this.mDate.setTime(System.currentTimeMillis());
        }
        return this.mDayDateFormat.format(this.mDate);
    }

    /**获取以秒为单位的时间字符串*/
    private String getTimeDateString(){
        if(mDate==null){
            mDate=new Date(System.currentTimeMillis());
        }else{
            mDate.setTime(System.currentTimeMillis());
        }
        return mTimeDateFormat.format(mDate);
    }
}
