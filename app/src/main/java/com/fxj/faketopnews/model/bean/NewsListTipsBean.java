package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fuxianjin-hj on 2018/7/6.
 */

public class NewsListTipsBean implements Parcelable {

    /*
    * "type": "app",
		"display_duration": 2,
		"display_info": "今日头条推荐引擎有20条更新",
		"display_template": "今日头条推荐引擎有%s条更新",
		"open_url": "",
		"web_url": "",
		"download_url": "",
		"app_name": "今日头条",
		"package_name": ""
    * */

    public String type;
    /**Tips展示时长(单位:秒(s))*/
    public int display_duration;
    /**Tips展示信息文本*/
    public String display_info;
    /**Tips展示信息文本模板*/
    public String display_template;

    public String open_url;

    public String web_url;

    public String download_url;

    public String app_name;

    public String package_name;


    @Override
    public String toString() {
        return "NewsListTipsBean{" +
                "type='" + type + '\'' +
                ", display_duration=" + display_duration +
                ", display_info='" + display_info + '\'' +
                ", display_template='" + display_template + '\'' +
                ", open_url='" + open_url + '\'' +
                ", web_url='" + web_url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", app_name='" + app_name + '\'' +
                ", package_name='" + package_name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeInt(this.display_duration);
        dest.writeString(this.display_info);
        dest.writeString(this.display_template);
        dest.writeString(this.open_url);
        dest.writeString(this.web_url);
        dest.writeString(this.download_url);
        dest.writeString(this.app_name);
        dest.writeString(this.package_name);
    }

    public NewsListTipsBean() {
    }

    protected NewsListTipsBean(Parcel in) {
        this.type = in.readString();
        this.display_duration = in.readInt();
        this.display_info = in.readString();
        this.display_template = in.readString();
        this.open_url = in.readString();
        this.web_url = in.readString();
        this.download_url = in.readString();
        this.app_name = in.readString();
        this.package_name = in.readString();
    }

    public static final Parcelable.Creator<NewsListTipsBean> CREATOR = new Parcelable.Creator<NewsListTipsBean>() {
        @Override
        public NewsListTipsBean createFromParcel(Parcel source) {
            return new NewsListTipsBean(source);
        }

        @Override
        public NewsListTipsBean[] newArray(int size) {
            return new NewsListTipsBean[size];
        }
    };



}
