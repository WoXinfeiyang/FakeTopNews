package com.fxj.faketopnews.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

public class TimeUtils {

    private static long ONE_MINUTE_MILLIS=60*1000;
    private static long ONE_HOUR_MILLIS=60*ONE_MINUTE_MILLIS;
    private static long ONE_DAY_MILLIS=24*ONE_HOUR_MILLIS;

    public static String getShortTime(long pastTimeMillis){
        String resultStr="";
        Date pastDate=new Date(pastTimeMillis);
        Date currentDate=new Date(System.currentTimeMillis());
        long durationTime=currentDate.getTime()-pastDate.getTime();
        if(durationTime<10*ONE_MINUTE_MILLIS){
            resultStr="刚刚";
        }else if(durationTime<ONE_HOUR_MILLIS){
            resultStr=durationTime/ONE_MINUTE_MILLIS+"分钟前";
        }else if(durationTime<ONE_DAY_MILLIS){
            resultStr=durationTime/ONE_HOUR_MILLIS+"小时前";
        }else{
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:MM");
            resultStr=dateFormat.format(pastDate);
        }
        return resultStr;
    }
}
