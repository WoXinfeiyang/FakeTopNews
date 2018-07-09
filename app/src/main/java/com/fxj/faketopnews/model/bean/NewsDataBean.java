package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fuxianjin-hj on 2018/7/6.
 */

public class NewsDataBean implements Parcelable {
    public String content;
    public String code;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.code);
    }

    @Override
    public String toString() {
        return "NewsDataBean{" +
                "content='" + content + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public NewsDataBean() {
    }

    protected NewsDataBean(Parcel in) {
        this.content = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<NewsDataBean> CREATOR = new Parcelable.Creator<NewsDataBean>() {
        @Override
        public NewsDataBean createFromParcel(Parcel source) {
            return new NewsDataBean(source);
        }

        @Override
        public NewsDataBean[] newArray(int size) {
            return new NewsDataBean[size];
        }
    };


}
