package com.fxj.faketopnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fxj.faketopnews.Base.BaseApplication;

/**
 * Created by fuxianjin-hj on 2018/7/5.
 */

public class PreferenceUtils {
    private static String SHARED_PREFERENCES_NAME="FakeTopNewsSharedPreferences";
    private static SharedPreferences mSharedPreference= BaseApplication.getAppContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_MULTI_PROCESS|Context.MODE_PRIVATE);

    public static boolean getBoolean(String key,boolean defValue){
        return mSharedPreference.getBoolean(key,defValue);
    }

    public static void setBoolean(String key,boolean value){
        SharedPreferences.Editor editor= mSharedPreference.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static int getInt(String key,int defValue){
        return mSharedPreference.getInt(key,defValue);
    }

    public static void setInt(String key,int value){
        SharedPreferences.Editor editor= mSharedPreference.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static long getLong(String key,long defValue){
        return mSharedPreference.getLong(key,defValue);
    }

    public static void setLong(String key,long value){
        SharedPreferences.Editor editor= mSharedPreference.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    public static float getFloat(String key,float defValue){
        return mSharedPreference.getFloat(key,defValue);
    }

    public static void setFloat(String key,float value){
        SharedPreferences.Editor editor= mSharedPreference.edit();
        editor.putFloat(key,value);
        editor.commit();
    }

    public static String getString(String key,String defValue){
        return mSharedPreference.getString(key,defValue);
    }

    public static void setString(String key,String value){
        SharedPreferences.Editor editor= mSharedPreference.edit();
        editor.putString(key,value);
        editor.commit();
    }
}
