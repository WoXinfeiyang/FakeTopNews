package com.fxj.faketopnews.view_inface;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public interface INewsList {
    void onGetNewsListSuccess(JSONObject jsonObject);
    void onGetNewsListFailed(String errorMsg);
}
