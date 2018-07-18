package com.fxj.faketopnews.utils;

import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/18.
 */

public  class ListUtils {

    public static boolean isEmpty(List list){
        if(list==null){
            return true;
        }
        return list.size()==0;
    }
}
