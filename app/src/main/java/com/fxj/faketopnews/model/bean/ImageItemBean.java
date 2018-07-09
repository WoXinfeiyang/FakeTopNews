package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author ChayChan
 * @description: 图片实体类
 * @date 2017/7/9  10:39
 */

public class ImageItemBean implements Parcelable {
    /**
     * url : http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp
     * width : 700
     * url_list : [{"url":"http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb9.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb1.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"}]
     * uri : list/2c23000095ae9f56b15f
     * height : 393
     */

    public String url;
    public int width;
    public String uri;
    public int height;
    public List<UrlListBeanX> url_list;

    @Override
    public String toString() {
        return "ImageItemBean{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", uri='" + uri + '\'' +
                ", height=" + height +
                ", url_list=" + url_list +
                '}';
    }



    public static class UrlListBeanX implements Parcelable {
        /**
         * url : http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp
         */

        public String url;

        @Override
        public String toString() {
            return "UrlListBeanX{" +
                    "url='" + url + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
        }

        public UrlListBeanX() {
        }

        protected UrlListBeanX(Parcel in) {
            this.url = in.readString();
        }

        public static final Parcelable.Creator<UrlListBeanX> CREATOR = new Parcelable.Creator<UrlListBeanX>() {
            @Override
            public UrlListBeanX createFromParcel(Parcel source) {
                return new UrlListBeanX(source);
            }

            @Override
            public UrlListBeanX[] newArray(int size) {
                return new UrlListBeanX[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.width);
        dest.writeString(this.uri);
        dest.writeInt(this.height);
        dest.writeTypedList(this.url_list);
    }

    public ImageItemBean() {
    }

    protected ImageItemBean(Parcel in) {
        this.url = in.readString();
        this.width = in.readInt();
        this.uri = in.readString();
        this.height = in.readInt();
        this.url_list = in.createTypedArrayList(UrlListBeanX.CREATOR);
    }

    public static final Parcelable.Creator<ImageItemBean> CREATOR = new Parcelable.Creator<ImageItemBean>() {
        @Override
        public ImageItemBean createFromParcel(Parcel source) {
            return new ImageItemBean(source);
        }

        @Override
        public ImageItemBean[] newArray(int size) {
            return new ImageItemBean[size];
        }
    };
}
