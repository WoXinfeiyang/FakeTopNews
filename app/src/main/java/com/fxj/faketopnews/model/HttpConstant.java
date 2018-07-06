package com.fxj.faketopnews.model;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public class HttpConstant {

    /*
    *获取新闻列表URL
    *http://is.snssdk.com/api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752&category=&min_behot_time=1530789303&last_refresh_sub_entrance_interval=1530789409
    *
    * 获取阅读文章所需流量信息
    * http://lf.snssdk.com/activity/carrier_flow/query_flow/?carrier=&iid=37197418638&device_id=43447012500&ac=wifi&channel=update&aid=13&app_name=news_article&version_code=678&version_name=6.7.8&device_platform=android&ab_version=261579%2C397704%2C400385%2C398545%2C271178%2C357704%2C377637%2C326524%2C326532%2C397258%2C398606%2C295827%2C392668%2C385982%2C239098%2C170988%2C397675%2C400078%2C398176%2C374117%2C392845%2C391118%2C330631%2C297059%2C382634%2C276206%2C396376%2C393403%2C379691%2C367078%2C277718%2C395306%2C377575%2C392125%2C381407%2C385744%2C369501%2C369167%2C392461%2C385832%2C385724%2C376444%2C385964%2C378451%2C399258%2C392716%2C384082%2C393340%2C385700%2C323233%2C392654%2C346556%2C351090%2C399470%2C400378%2C345191%2C377634%2C398074%2C400100%2C387875%2C387371%2C396169%2C397386%2C361327%2C318456%2C207254%2C390892%2C388526%2C280449%2C281296%2C399365%2C386667%2C325611%2C400246%2C399742%2C397515%2C357401%2C400224%2C386895%2C398435%2C396156%2C400301&ab_client=a1%2Cc4%2Ce1%2Cf1%2Cg2%2Cf7&ab_group=100170&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=8692-A00&device_brand=QiKU&language=zh&os_api=22&os_version=5.1.1&uuid=99000563923098&openudid=3a9b832ac8e3af00&manifest_version_code=678&resolution=1080*1920&dpi=460&update_version_code=67807&_rticket=1530789488808&plugin=10607&fp=JlTqcS5MFYctFlTMJ2U1Flx7JlK1&pos=5r_-9Onkv6e_eBEKeScxeCUfv7G_8fLz-vTp6Pn4v6esrKuzqaWppKursb_x_On06ej5-L-nrqSzpKWsq6Sxv_zw_O3e9Onkv6e_eBEKeScxeCUfv7G__PD87dHy8_r06ej5-L-nrKyrs6mkrK6tpbG__PD87dH86fTp6Pn4v6eupLOkpa-qpKjg&rom_version=22&ts=1530789488&as=a2d56f43b0575bfeed7959&mas=00f2884e1db51dc53cefde9730058c1889c8c444865a6d5ed2
    * */

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
