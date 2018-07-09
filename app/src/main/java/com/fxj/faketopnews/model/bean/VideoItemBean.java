package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author ChayChan
 * @description: 视频实体类
 * @date 2017/7/9  10:44
 */

public class VideoItemBean implements Parcelable {
    /**
     * group_flags : 32832
     * video_type : 0
     * video_preloading_flag : 1
     * video_url : []
     * direct_play : 1
     * detail_video_large_image : {"url":"http://p1.pstatp.com/video1609/2130000392cc3ddb8076","width":580,"url_list":[{"url":"http://p1.pstatp.com/video1609/2130000392cc3ddb8076"},{"url":"http://pb3.pstatp.com/video1609/2130000392cc3ddb8076"},{"url":"http://pb9.pstatp.com/video1609/2130000392cc3ddb8076"}],"uri":"video1609/2130000392cc3ddb8076","height":326}
     * show_pgc_subscribe : 1
     * video_third_monitor_url :
     * video_id : eb0eab0d76274b13a3fd0649ba1d0f74
     * video_watching_count : 0
     * video_watch_count : 657298
     */

    public int group_flags;
    public int video_type;
    public int video_preloading_flag;
    public int direct_play;
    public ImageItemBean detail_video_large_image;
    public int show_pgc_subscribe;
    public String video_third_monitor_url;
    public String video_id;
    public int video_watching_count;
    public int video_watch_count;
    public List<String> video_url;
    //自己新增的字段，记录视频播放的进度，用于同步视频列表也和详情页的进度
    public int progress;

    @Override
    public String toString() {
        return "VideoItemBean{" +
                "group_flags=" + group_flags +
                ", video_type=" + video_type +
                ", video_preloading_flag=" + video_preloading_flag +
                ", direct_play=" + direct_play +
                ", detail_video_large_image=" + detail_video_large_image +
                ", show_pgc_subscribe=" + show_pgc_subscribe +
                ", video_third_monitor_url='" + video_third_monitor_url + '\'' +
                ", video_id='" + video_id + '\'' +
                ", video_watching_count=" + video_watching_count +
                ", video_watch_count=" + video_watch_count +
                ", video_url=" + video_url +
                ", progress=" + progress +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.group_flags);
        dest.writeInt(this.video_type);
        dest.writeInt(this.video_preloading_flag);
        dest.writeInt(this.direct_play);
        dest.writeParcelable(this.detail_video_large_image, flags);
        dest.writeInt(this.show_pgc_subscribe);
        dest.writeString(this.video_third_monitor_url);
        dest.writeString(this.video_id);
        dest.writeInt(this.video_watching_count);
        dest.writeInt(this.video_watch_count);
        dest.writeStringList(this.video_url);
        dest.writeInt(this.progress);
    }

    public VideoItemBean() {
    }

    protected VideoItemBean(Parcel in) {
        this.group_flags = in.readInt();
        this.video_type = in.readInt();
        this.video_preloading_flag = in.readInt();
        this.direct_play = in.readInt();
        this.detail_video_large_image = in.readParcelable(ImageItemBean.class.getClassLoader());
        this.show_pgc_subscribe = in.readInt();
        this.video_third_monitor_url = in.readString();
        this.video_id = in.readString();
        this.video_watching_count = in.readInt();
        this.video_watch_count = in.readInt();
        this.video_url = in.createStringArrayList();
        this.progress = in.readInt();
    }

    public static final Parcelable.Creator<VideoItemBean> CREATOR = new Parcelable.Creator<VideoItemBean>() {
        @Override
        public VideoItemBean createFromParcel(Parcel source) {
            return new VideoItemBean(source);
        }

        @Override
        public VideoItemBean[] newArray(int size) {
            return new VideoItemBean[size];
        }
    };
}
