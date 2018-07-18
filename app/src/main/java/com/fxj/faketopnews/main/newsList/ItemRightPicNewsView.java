package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.model.bean.NewsContentBean;
import com.fxj.faketopnews.utils.FrescoUtils;
import com.fxj.faketopnews.utils.TimeUtils;

/**
 * Created by fuxianjin-hj on 2018/7/18.
 */

public class ItemRightPicNewsView extends RelativeLayout {

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
    private SimpleDraweeView rightPic;
    private View durationGroup;
    private TextView tvDuration;

    public ItemRightPicNewsView(Context context) {
        super(context);
        initView(context);
    }

    public ItemRightPicNewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemRightPicNewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_view_right_pic_news,this);
        tvNewsTitle = findViewById(R.id.tv_news_title);
        tvTag = findViewById(R.id.tv_tag);
        tvNewsAuthor = findViewById(R.id.tv_news_author);
        tvCommentNum = findViewById(R.id.tv_comment_num);
        tvNewsPublishTime = findViewById(R.id.tv_news_publish_time);
        rightPic = findViewById(R.id.drawee_view_right_pic);
        durationGroup = findViewById(R.id.ll_duration);
        tvDuration = findViewById(R.id.tv_duration);
    }

    public void updateView(NewsContentBean newsContent){
        this.tvNewsTitle.setText(TextUtils.isEmpty(newsContent.title)?"":newsContent.title);
        this.tvTag.setText("");
        this.tvNewsAuthor.setText(TextUtils.isEmpty(newsContent.source)?"":newsContent.source);
        this.tvCommentNum.setText(newsContent.comment_count+"评论");
        this.tvNewsPublishTime.setText(TimeUtils.getShortTime(newsContent.behot_time*1000));
        if(newsContent.has_video){
            durationGroup.setVisibility(View.VISIBLE);
            tvDuration.setText("");
        }else{
            if(durationGroup!=null){
                durationGroup.setVisibility(View.INVISIBLE);
            }
        }
        FrescoUtils.getsInstance().displayImage(rightPic,newsContent.middle_image.url);
    }
}
