package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.model.bean.NewsContentBean;
import com.fxj.faketopnews.utils.FrescoUtils;
import com.fxj.faketopnews.utils.TimeUtils;

/**
 * Created by fuxianjin-hj on 2018/7/18.
 */

public class ItemCenterSinglePicNewsView extends LinearLayout {

    private Context mContext;
    /**新闻标题*/
    private TextView tvNewsTitle;
    /**新闻标签(置顶、热、广告、影视)*/
    private TextView tvTag;
    /**新闻作者*/
    private TextView tvNewsAuthor;
    /**新闻评论数*/
    private TextView tvCommentNum;
    /**新闻发布时间*/
    private TextView tvNewsPublishTime;

    private SimpleDraweeView centerPic;
    private SimpleDraweeView playBtn;
    private TextView tvBottomRight;

    public ItemCenterSinglePicNewsView(Context context) {
        super(context);
        initView(context);
    }

    public ItemCenterSinglePicNewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemCenterSinglePicNewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_view_center_single_pic_news,this);
        tvNewsTitle = findViewById(R.id.tv_news_title);
        tvTag = findViewById(R.id.tv_tag);
        tvNewsAuthor = findViewById(R.id.tv_news_author);
        tvCommentNum = findViewById(R.id.tv_comment_num);
        tvNewsPublishTime = findViewById(R.id.tv_news_publish_time);
        centerPic = findViewById(R.id.drawee_view_center_pic);
        playBtn = findViewById(R.id.drawee_view_play_btn);
        tvBottomRight = findViewById(R.id.tv_center_single_pic_news_bottom_right);
    }

    public void updateView(NewsContentBean newsContent){
        this.tvNewsTitle.setText(TextUtils.isEmpty(newsContent.title)?"":newsContent.title);
        this.tvTag.setText("");
        this.tvNewsAuthor.setText(TextUtils.isEmpty(newsContent.source)?"":newsContent.source);
        this.tvCommentNum.setText(newsContent.comment_count+"评论");
        this.tvNewsPublishTime.setText(TimeUtils.getShortTime(newsContent.behot_time*1000));

        if(newsContent.has_video){
            playBtn.setVisibility(VISIBLE);
            tvBottomRight.setVisibility(View.VISIBLE);
            tvBottomRight.setCompoundDrawables(null,null,null,null);
            tvBottomRight.setText(TimeUtils.secondToTime(newsContent.video_duration));

            FrescoUtils.getsInstance().displayImage(this.centerPic,newsContent.video_detail_info.detail_video_large_image.url);
        }else{
            playBtn.setVisibility(View.INVISIBLE);

            if(newsContent.gallary_image_count==1){
                tvBottomRight.setVisibility(View.INVISIBLE);
            }else{
                tvBottomRight.setVisibility(View.VISIBLE);
                tvBottomRight.setCompoundDrawables(this.mContext.getResources().getDrawable(R.drawable.icon_picture_group),null,null,null);
                tvBottomRight.setText(newsContent.gallary_image_count+"图");
            }

            FrescoUtils.getsInstance().displayImage(this.centerPic,newsContent.image_list.get(0).url.replace("list/300x196", "large"));
        }
    }
}
