package com.fxj.faketopnews.model;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public class HttpConstant {
    /**接口根地址*/
    public static final String BASE_SERVER_URL = "http://is.snssdk.com/";

    /**获取新闻列表URL
     * 请求参数
     * category--String,分类码,比如"video"、"news_hot"、"news_society"、"news_entertainment"
     * min_behot_time--long,上次请求时间,单位:秒(s)
     * last_refresh_sub_entrance_interval--long,当前请求时间,单位:秒(s)
     * */
    public static final String GET_ARTICLE_LIST=BASE_SERVER_URL+"api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";

}
