package com.fxj.faketopnews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/9.
 */

public class NewsContentBean implements Parcelable {

    /*
     * log_pb : {"impr_id":"201707061547030100080440135802B6"}
     * read_count : 628838
     * media_name : 电竞手游君
     * ban_comment : 0
     * abstract : 王者荣耀赛季结束的段位奖励一直以来都还是很给力的，除了免费的赛季限定皮肤之外（注：本次的赛季奖励皮肤获取方式为获得10场胜利游戏后自动获取）还有就是大把大把的钻石奖励了。那么这个钻石到底怎么用合适呢？
     * image_list : [{"url":"http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp","width":700,"url_list":[{"url":"http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb9.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb1.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"}],"uri":"list/2c23000095ae9f56b15f","height":393},{"url":"http://p3.pstatp.com/list/300x196/2c2c0004688def37bb3c.webp","width":699,"url_list":[{"url":"http://p3.pstatp.com/list/300x196/2c2c0004688def37bb3c.webp"},{"url":"http://pb9.pstatp.com/list/300x196/2c2c0004688def37bb3c.webp"},{"url":"http://pb1.pstatp.com/list/300x196/2c2c0004688def37bb3c.webp"}],"uri":"list/2c2c0004688def37bb3c","height":393},{"url":"http://p1.pstatp.com/list/300x196/2c2a000049bb1a95374c.webp","width":699,"url_list":[{"url":"http://p1.pstatp.com/list/300x196/2c2a000049bb1a95374c.webp"},{"url":"http://pb3.pstatp.com/list/300x196/2c2a000049bb1a95374c.webp"},{"url":"http://pb9.pstatp.com/list/300x196/2c2a000049bb1a95374c.webp"}],"uri":"list/2c2a000049bb1a95374c","height":393}]
     * ugc_recommend : {"reason":"","activity":""}
     * article_type : 0
     * tag : news_game
     * forward_info : {"forward_count":0}
     * has_m3u8_video : 0
     * keywords : 韩信,王者荣耀,王者水晶,游戏,胜利游戏
     * rid : 201707061547030100080440135802B6
     * show_portrait_article : false
     * user_verified : 0
     * aggr_type : 1
     * cell_type : 0
     * article_sub_type : 0
     * bury_count : 3
     * title : 王者荣耀用段位奖励的钻石抽到王者水晶之后千万别急着换韩信
     * ignore_web_transform : 1
     * source_icon_style : 5
     * tip : 0
     * hot : 0
     * share_url : http://toutiao.com/a6436927152504258818/?iid=0&app=news_article
     * has_mp4_video : 0
     * source : 电竞手游君
     * comment_count : 902
     * article_url : http://toutiao.com/group/6436927152504258818/
     * filter_words : [{"id":"8:0","name":"看过了","is_selected":false},{"id":"9:1","name":"内容太水","is_selected":false},{"id":"5:1189115162","name":"拉黑来源:电竞手游君","is_selected":false},{"id":"3:306461185","name":"不想看:钻石","is_selected":false},{"id":"6:153758450","name":"不想看:王者荣耀","is_selected":false},{"id":"6:236362","name":"不想看:韩信","is_selected":false}]
     * share_count : 211
     * publish_time : 1498714396
     * action_list : [{"action":1,"extra":{},"desc":""},{"action":3,"extra":{},"desc":""},{"action":7,"extra":{},"desc":""},{"action":9,"extra":{},"desc":""}]
     * gallary_image_count : 4
     * cell_layout_style : 1
     * tag_id : 6436927152504259000
     * video_style : 0
     * verified_content :
     * display_url : http://toutiao.com/group/6436927152504258818/
     * large_image_list : []
     * media_info : {"user_id":59834611934,"verified_content":"","avatar_url":"http://p3.pstatp.com/large/216b000e0abb3ee9cb91","media_id":1567608882475010,"name":"电竞手游君","recommend_type":0,"follow":false,"recommend_reason":"","is_star_user":false,"user_verified":false}
     * item_id : 6436929317376099000
     * is_subject : false
     * show_portrait : false
     * repin_count : 1110
     * cell_flag : 11
     * user_info : {"verified_content":"","avatar_url":"http://p3.pstatp.com/thumb/216b000e0abb3ee9cb91","user_id":59834611934,"name":"电竞手游君","follower_count":0,"follow":false,"user_auth_info":"","user_verified":false,"description":"游戏 资讯 游戏攻略 你要的这里都有，来这里就对了。"}
     * source_open_url : sslocal://profile?uid=59834611934
     * level : 0
     * like_count : 19
     * digg_count : 19
     * behot_time : 1499325873
     * cursor : 1499325873999
     * url : http://toutiao.com/group/6436927152504258818/
     * preload_web : 0
     * user_repin : 0
     * has_image : true
     * item_version : 0
     * has_video : false
     * group_id : 6436927152504259000
     * video_duration: 68,
     * middle_image : {"url":"http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp","width":700,"url_list":[{"url":"http://p3.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb9.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"},{"url":"http://pb1.pstatp.com/list/300x196/2c23000095ae9f56b15f.webp"}],"uri":"list/2c23000095ae9f56b15f","height":393}
     */

    public int article_type;
    public String tag;
    public String title;
    public int hot;
    public String source;
    public int comment_count;
    public String article_url;
    public int gallary_image_count;
    public int video_style;
    public String item_id;
    public UserBean user_info;
    public long behot_time;
    public String url;
    public boolean has_image;
    public boolean has_video;
    public int video_duration;
    public VideoItemBean video_detail_info;
    public String group_id;
    public ImageItemBean middle_image;
    public List<ImageItemBean> image_list;

    @Override
    public String toString() {
        return "NewsContentBean{" +
                "article_type=" + article_type +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", hot=" + hot +
                ", source='" + source + '\'' +
                ", comment_count=" + comment_count +
                ", article_url='" + article_url + '\'' +
                ", gallary_image_count=" + gallary_image_count +
                ", video_style=" + video_style +
                ", item_id='" + item_id + '\'' +
                ", user_info=" + user_info +
                ", behot_time=" + behot_time +
                ", url='" + url + '\'' +
                ", has_image=" + has_image +
                ", has_video=" + has_video +
                ", video_duration=" + video_duration +
                ", video_detail_info=" + video_detail_info +
                ", group_id='" + group_id + '\'' +
                ", middle_image=" + middle_image +
                ", image_list=" + image_list +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.article_type);
        dest.writeString(this.tag);
        dest.writeString(this.title);
        dest.writeInt(this.hot);
        dest.writeString(this.source);
        dest.writeInt(this.comment_count);
        dest.writeString(this.article_url);
        dest.writeInt(this.gallary_image_count);
        dest.writeInt(this.video_style);
        dest.writeString(this.item_id);
        dest.writeParcelable(this.user_info, flags);
        dest.writeLong(this.behot_time);
        dest.writeString(this.url);
        dest.writeByte(this.has_image ? (byte) 1 : (byte) 0);
        dest.writeByte(this.has_video ? (byte) 1 : (byte) 0);
        dest.writeInt(this.video_duration);
        dest.writeParcelable(this.video_detail_info, flags);
        dest.writeString(this.group_id);
        dest.writeParcelable(this.middle_image, flags);
        dest.writeTypedList(this.image_list);
    }

    public NewsContentBean() {
    }

    protected NewsContentBean(Parcel in) {
        this.article_type = in.readInt();
        this.tag = in.readString();
        this.title = in.readString();
        this.hot = in.readInt();
        this.source = in.readString();
        this.comment_count = in.readInt();
        this.article_url = in.readString();
        this.gallary_image_count = in.readInt();
        this.video_style = in.readInt();
        this.item_id = in.readString();
        this.user_info = in.readParcelable(UserBean.class.getClassLoader());
        this.behot_time = in.readLong();
        this.url = in.readString();
        this.has_image = in.readByte() != 0;
        this.has_video = in.readByte() != 0;
        this.video_duration = in.readInt();
        this.video_detail_info = in.readParcelable(VideoItemBean.class.getClassLoader());
        this.group_id = in.readString();
        this.middle_image = in.readParcelable(ImageItemBean.class.getClassLoader());
        this.image_list = in.createTypedArrayList(ImageItemBean.CREATOR);
    }

    public static final Parcelable.Creator<NewsContentBean> CREATOR = new Parcelable.Creator<NewsContentBean>() {
        @Override
        public NewsContentBean createFromParcel(Parcel source) {
            return new NewsContentBean(source);
        }

        @Override
        public NewsContentBean[] newArray(int size) {
            return new NewsContentBean[size];
        }
    };
}
