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
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/7/18.
 */

public class ItemThreePicNewsView extends LinearLayout {
    private final String tag=ItemThreePicNewsView.class.getSimpleName()+"_fxj";
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

    private SimpleDraweeView pic01;
    private SimpleDraweeView pic02;
    private SimpleDraweeView pic03;

    public ItemThreePicNewsView(Context context) {
        super(context);
        initView(context);
    }

    public ItemThreePicNewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemThreePicNewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_view_three_pic_news,this);
        tvNewsTitle = findViewById(R.id.tv_news_title);
        tvTag = findViewById(R.id.tv_tag);
        tvNewsAuthor = findViewById(R.id.tv_news_author);
        tvCommentNum = findViewById(R.id.tv_comment_num);
        tvNewsPublishTime = findViewById(R.id.tv_news_publish_time);
        pic01=findViewById(R.id.drawee_view_pic01);
        pic02=findViewById(R.id.drawee_view_pic02);
        pic03=findViewById(R.id.drawee_view_pic03);
    }

    public void updateView(NewsContentBean newsContent){
        KLog.i(tag,"title="+newsContent.title+",source="+newsContent.source+"image_list="+newsContent.image_list);
        this.tvNewsTitle.setText(TextUtils.isEmpty(newsContent.title)?" ":newsContent.title);
        this.tvTag.setText("");
        this.tvNewsAuthor.setText(TextUtils.isEmpty(newsContent.source)?" ":newsContent.source);
        this.tvCommentNum.setText(newsContent.comment_count+"评论");
        this.tvNewsPublishTime.setText(TimeUtils.getShortTime(newsContent.behot_time*1000));

        if(newsContent.image_list!=null){
            if(newsContent.image_list.get(0)!=null&&!TextUtils.isEmpty(newsContent.image_list.get(0).url)){
                FrescoUtils.getsInstance().displayImage(pic01,newsContent.image_list.get(0).url);
            }else{
                pic01.setVisibility(View.GONE);
            }

            if(newsContent.image_list.get(1)!=null&&!TextUtils.isEmpty(newsContent.image_list.get(1).url)){
                FrescoUtils.getsInstance().displayImage(pic02,newsContent.image_list.get(1).url);
            }else{
                pic02.setVisibility(View.GONE);
            }

            if(newsContent.image_list.get(2)!=null&&!TextUtils.isEmpty(newsContent.image_list.get(2).url)){
                FrescoUtils.getsInstance().displayImage(pic03,newsContent.image_list.get(2).url);
            }else{
                pic03.setVisibility(View.GONE);
            }
        }
    }
}
