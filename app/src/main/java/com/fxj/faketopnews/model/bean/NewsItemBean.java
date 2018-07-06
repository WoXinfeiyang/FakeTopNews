package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fuxianjin-hj on 2018/7/6.
 */

public class NewsItemBean implements Parcelable {
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
        return "NewsItemBean{" +
                "content='" + content + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public NewsItemBean() {
    }

    protected NewsItemBean(Parcel in) {
        this.content = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<NewsItemBean> CREATOR = new Parcelable.Creator<NewsItemBean>() {
        @Override
        public NewsItemBean createFromParcel(Parcel source) {
            return new NewsItemBean(source);
        }

        @Override
        public NewsItemBean[] newArray(int size) {
            return new NewsItemBean[size];
        }
    };


}
