package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fuxianjin-hj on 2018/7/4.
 */

public class CategoryBean implements Parcelable {
    public String mCategoryName;
    public String mCategoryCode;

    public CategoryBean(String mCategoryName, String mCategoryCode) {
        this.mCategoryName = mCategoryName;
        this.mCategoryCode = mCategoryCode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCategoryName);
        dest.writeString(this.mCategoryCode);
    }

    protected CategoryBean(Parcel in) {
        this.mCategoryName = in.readString();
        this.mCategoryCode = in.readString();
    }

    public static final Parcelable.Creator<CategoryBean> CREATOR = new Parcelable.Creator<CategoryBean>() {
        @Override
        public CategoryBean createFromParcel(Parcel source) {
            return new CategoryBean(source);
        }

        @Override
        public CategoryBean[] newArray(int size) {
            return new CategoryBean[size];
        }
    };

    @Override
    public String toString() {
        return "CategoryBean{" +
                "mCategoryName='" + mCategoryName + '\'' +
                ", mCategoryCode='" + mCategoryCode + '\'' +
                '}';
    }
}
