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

    /**
     * 将秒数转换成00:00的字符串，如 118秒 -> 01:58
     * @param secondTime
     * @return
     */
    public static String secondToTime(int secondTime){
        String timeStr=null;
        int hour=0;
        int minute=0;
        int second=0;
        if(secondTime<=0){
            return "00:00";
        }
        minute=secondTime/60;
        if(minute<60){/*当秒数时长小于一个小时*/
            second=secondTime%60;
            timeStr=unitFormat(minute)+":"+unitFormat(second);
        }else{
            hour=minute/60;
            minute=minute%60;
            second=hour*3600-minute*60;
            timeStr=unitFormat(hour)+":"+unitFormat(minute)+":"+unitFormat(second);
        }
        return timeStr;
    }

    private static String unitFormat(int time){
        String resultStr=null;
        if(time<=0){
            resultStr="00";
        }else if(time>0&&time<10){
            resultStr="0"+String.valueOf(time);
        }else{
            resultStr=String.valueOf(time);
        }
        return resultStr;
    }
}
