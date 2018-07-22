package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.fxj.faketopnews.model.bean.NewsContentBean;
import com.fxj.faketopnews.utils.ListUtils;
import com.fxj.faketopnews.views.RefreshListView.RefreshListAdapter;
import com.fxj.faketopnews.views.RefreshListView.RefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/10.
 */

public class NewsListAdapter extends RefreshListAdapter<NewsContentBean,NewsContentBean> {
    /**纯文字布局(文章,广告)*/
    private static final int ITEM_VIEW_TYPE_TEXT_NEWS=0;
    /**居中大图布局(1.单图文章；2.单图广告；3.视频，中间显示播放图标，右侧显示时长)*/
    private static final int ITEM_VIEW_TYPE_CENTER_SINGLE_PIC_NEWS=1;
    /**右侧小图布局(1.小图新闻；2.视频类型，右下角显示视频时长)*/
    private static final int ITEM_VIEW_TYPE_RIGHT_PIC_NEWS_NEWS =2;
    /**三张图片布局(文章、广告)*/
    private static final int ITEM_VIEW_TYPE_THREE_PICS_NEWS=3;

    private List<NewsContentBean> mNewsConteList=new ArrayList<NewsContentBean>();

    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getDataItemCount() {
        return mNewsConteList.size();
    }

    @Override
    protected int getDataItemViewType(int position) {
        NewsContentBean newsContentItem=mNewsConteList.get(position);
        if(newsContentItem.has_video){/*视频新闻*/
            if(newsContentItem.video_style==0){
                if(newsContentItem.middle_image==null|| TextUtils.isEmpty(newsContentItem.middle_image.url)){
                    return ITEM_VIEW_TYPE_TEXT_NEWS;
                }
                return ITEM_VIEW_TYPE_RIGHT_PIC_NEWS_NEWS;
            }else if(newsContentItem.video_style==2){
                return ITEM_VIEW_TYPE_CENTER_SINGLE_PIC_NEWS;
            }
        }else{/*非视频新闻*/
            if(!newsContentItem.has_image){/*纯文字新闻*/
                return ITEM_VIEW_TYPE_TEXT_NEWS;
            }else{/**图片新闻*/
                if(ListUtils.isEmpty(newsContentItem.image_list)){/*图片列表为空，则是右侧图片*/
                    return ITEM_VIEW_TYPE_RIGHT_PIC_NEWS_NEWS;
                }

                if(newsContentItem.gallary_image_count==3){
                    return ITEM_VIEW_TYPE_THREE_PICS_NEWS;
                }

                return ITEM_VIEW_TYPE_CENTER_SINGLE_PIC_NEWS;
            }
        }
        return ITEM_VIEW_TYPE_TEXT_NEWS;
    }

    @Override
    protected RefreshViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View itemView=null;
        switch (viewType){
            case ITEM_VIEW_TYPE_TEXT_NEWS:
                ItemTextNewsView mItemTextNewsView=new ItemTextNewsView(this.mContext);
                itemView=mItemTextNewsView;
                break;
            case ITEM_VIEW_TYPE_THREE_PICS_NEWS:
                ItemThreePicNewsView mItemThreePicNewsView=new ItemThreePicNewsView(this.mContext);
                itemView=mItemThreePicNewsView;
                break;
            case ITEM_VIEW_TYPE_CENTER_SINGLE_PIC_NEWS:
                ItemCenterSinglePicNewsView mItemCenterSinglePicNewsView=new ItemCenterSinglePicNewsView(this.mContext);
                itemView=mItemCenterSinglePicNewsView;
                break;
            case ITEM_VIEW_TYPE_RIGHT_PIC_NEWS_NEWS:
                ItemRightPicNewsView mItemRightPicNewsView=new ItemRightPicNewsView(this.mContext);
                itemView=mItemRightPicNewsView;
                break;
        }
        return new RefreshViewHolder(itemView);
    }

    @Override
    protected void onBindDataViewHolder(RefreshViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        NewsContentBean itemNewsContent=this.mNewsConteList.get(position);
        switch (viewType){
            case ITEM_VIEW_TYPE_TEXT_NEWS:
                ((ItemTextNewsView)holder.itemView).updateView(itemNewsContent);
                break;
            case ITEM_VIEW_TYPE_THREE_PICS_NEWS:
                ((ItemThreePicNewsView)holder.itemView).updateView(itemNewsContent);
                break;
            case ITEM_VIEW_TYPE_CENTER_SINGLE_PIC_NEWS:
                ((ItemCenterSinglePicNewsView)holder.itemView).updateView(itemNewsContent);
                break;
            case ITEM_VIEW_TYPE_RIGHT_PIC_NEWS_NEWS:
                ((ItemRightPicNewsView)holder.itemView).updateView(itemNewsContent);
                break;
        }
    }

    @Override
    protected void processDataRefresh(List<NewsContentBean> headerData) {
        mNewsConteList.clear();
        mNewsConteList.addAll(headerData);
        notifyDataSetChanged();
    }

    @Override
    protected void processDataAdded(List<NewsContentBean> footerData) {
        this.mNewsConteList.addAll(footerData);
    }




}
