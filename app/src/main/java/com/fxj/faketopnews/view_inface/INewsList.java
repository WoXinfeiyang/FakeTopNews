package com.fxj.faketopnews.view_inface;

import com.alibaba.fastjson.JSONObject;
import com.fxj.faketopnews.model.bean.NewsListBean;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public interface INewsList {
    void onGetNewsListSuccess(NewsListBean object);
    void onGetNewsListFailed(String errorMsg);
}
