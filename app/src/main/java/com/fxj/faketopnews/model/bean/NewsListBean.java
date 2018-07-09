package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;



public class NewsListBean implements Parcelable {

    public String message;

    public List<NewsDataBean> data;

    public int total_number;

    public boolean has_more;

    public int login_status;

    public int show_et_status;

    public String post_content_hint;

    public boolean has_more_to_refresh;

    public int action_to_last_stick;

    public int feed_flag;

    public NewsListTipsBean tips;

    @Override
    public String toString() {
        return "NewsListBean{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", total_number=" + total_number +
                ", has_more=" + has_more +
                ", login_status=" + login_status +
                ", show_et_status=" + show_et_status +
                ", post_content_hint='" + post_content_hint + '\'' +
                ", has_more_to_refresh=" + has_more_to_refresh +
                ", action_to_last_stick=" + action_to_last_stick +
                ", feed_flag=" + feed_flag +
                ", tips=" + tips +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeTypedList(this.data);
        dest.writeInt(this.total_number);
        dest.writeByte(this.has_more ? (byte) 1 : (byte) 0);
        dest.writeInt(this.login_status);
        dest.writeInt(this.show_et_status);
        dest.writeString(this.post_content_hint);
        dest.writeByte(this.has_more_to_refresh ? (byte) 1 : (byte) 0);
        dest.writeInt(this.action_to_last_stick);
        dest.writeInt(this.feed_flag);
        dest.writeParcelable(this.tips, flags);
    }

    public NewsListBean() {
    }

    protected NewsListBean(Parcel in) {
        this.message = in.readString();
        this.data = in.createTypedArrayList(NewsDataBean.CREATOR);
        this.total_number = in.readInt();
        this.has_more = in.readByte() != 0;
        this.login_status = in.readInt();
        this.show_et_status = in.readInt();
        this.post_content_hint = in.readString();
        this.has_more_to_refresh = in.readByte() != 0;
        this.action_to_last_stick = in.readInt();
        this.feed_flag = in.readInt();
        this.tips = in.readParcelable(NewsListTipsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsListBean> CREATOR = new Parcelable.Creator<NewsListBean>() {
        @Override
        public NewsListBean createFromParcel(Parcel source) {
            return new NewsListBean(source);
        }

        @Override
        public NewsListBean[] newArray(int size) {
            return new NewsListBean[size];
        }
    };
}
