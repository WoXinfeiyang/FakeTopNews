package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.fxj.faketopnews.R;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/1/10.
 */

public class RefreshListViewHeader extends LinearLayout implements SwipeRefreshTrigger,SwipeTrigger {
    private final String tag=RefreshListViewHeader.class.getSimpleName()+"_fxj";

    /**刷新状态*/
    private RefreshState mState=RefreshState.IDLE;
    private ImageView mRefreshArrow;
    private ImageView mRefreshLoading;
    private AnimationDrawable mRefreshLoadingDrawable;
    private TextView mTvRefreshTipsContent;

    private RotateAnimation mUpAnimation;
    private RotateAnimation mDownAnimation;

    private String mPullToRefreshTips="下拉刷新";
    private String mReleaseToRefreshTips="释放刷新";
    private String mRefreshingTips="正在刷新中……";
    private String mRefreshFinishedTips="刷新完成";

    public RefreshListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public RefreshListViewHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshListViewHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.refresh_header,this);
        mRefreshArrow = findViewById(R.id.iv_refresh_head_arrow);
        mRefreshLoading = findViewById(R.id.iv_refresh_loading);
        mRefreshLoadingDrawable = (AnimationDrawable) mRefreshLoading.getDrawable();
        mTvRefreshTipsContent = findViewById(R.id.tv_refresh_tips_content);
        initAnimation();
    }

    private void initAnimation() {
        mUpAnimation = new RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mUpAnimation.setDuration(150);/*设置动画持续时间*/
        mUpAnimation.setFillAfter(true);/*动画完成后保持状态*/

        mDownAnimation = new RotateAnimation(-180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mDownAnimation.setDuration(150);
        mDownAnimation.setFillAfter(true);
    }

    public void setPullToRefreshTips(String text){
        this.mPullToRefreshTips=text;
    }

    public void setReleaseToRefreshTips(String text){
        this.mReleaseToRefreshTips=text;
    }

    public void setRefreshingTips(String text){
        this.mRefreshingTips=text;
    }

    public void setRefreshFinishedTips(String text){
        this.mRefreshFinishedTips=text;
    }

    private void updateState(RefreshState state){
        if(this.mState==state){
            return;
        }
        this.mState=state;
        switch (this.mState){
            case IDLE:
                this.mRefreshArrow.clearAnimation();
                this.mRefreshArrow.setVisibility(View.INVISIBLE);

                this.mRefreshLoading.setVisibility(INVISIBLE);

                this.mTvRefreshTipsContent.setText("");

                break;
            case PULL_TO_REFRESH:
                if(this.mRefreshArrow.getVisibility()!=View.VISIBLE){
                    this.mRefreshArrow.setVisibility(VISIBLE);
                }
                this.mRefreshArrow.startAnimation(this.mDownAnimation);

                if(this.mRefreshLoading.getVisibility()!=View.INVISIBLE){
                    this.mRefreshLoading.setVisibility(INVISIBLE);
                }

                this.mTvRefreshTipsContent.setText(this.mPullToRefreshTips);
                break;
            case RELEASE_TO_REFRESH:
                if(this.mRefreshArrow.getVisibility()!=VISIBLE){
                    this.mRefreshArrow.setVisibility(VISIBLE);
                }
                if(this.mRefreshLoading.getVisibility()!=INVISIBLE){
                    this.mRefreshLoading.setVisibility(INVISIBLE);
                }
                this.mTvRefreshTipsContent.setText(this.mReleaseToRefreshTips);
                break;
            case REFRESHING:
                this.mRefreshArrow.clearAnimation();
                this.mRefreshArrow.setVisibility(INVISIBLE);
                this.mRefreshLoading.setVisibility(View.VISIBLE);
                this.mRefreshLoadingDrawable.start();
                this.mTvRefreshTipsContent.setText(this.mRefreshingTips);
                break;
            case REFRESH_FINISHED:
                this.mRefreshArrow.setVisibility(VISIBLE);
                this.mRefreshArrow.startAnimation(this.mUpAnimation);
                this.mRefreshLoading.setVisibility(INVISIBLE);
                this.mRefreshLoadingDrawable.stop();
                this.mTvRefreshTipsContent.setText(this.mRefreshFinishedTips);
                break;
        }
    }

    @Override
    public void onPrepare() {
        KLog.i(tag,"==onPrepare");
        updateState(RefreshState.IDLE);
    }

    @Override
    public void onRefresh() {
        KLog.i(tag,"正在刷新中……==onRefresh");
        updateState(RefreshState.REFRESHING);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        KLog.i(tag,"==onMove,y="+y+",isComplete="+isComplete+",automatic="+automatic);
        if(!isComplete){/*下拉刷新*/
            updateState(RefreshState.PULL_TO_REFRESH);
        }else{
            updateState(RefreshState.REFRESH_FINISHED);
        }
    }

    @Override
    public void onRelease() {
        KLog.i(tag,"==onRelease");
        updateState(RefreshState.RELEASE_TO_REFRESH);
    }

    @Override
    public void onComplete() {
        KLog.i(tag,"==onComplete");
        updateState(RefreshState.REFRESH_FINISHED);
    }

    @Override
    public void onReset() {
        KLog.i(tag,"==onReset");
        updateState(RefreshState.IDLE);
    }

    enum RefreshState{
        /**空闲状态*/
        IDLE,
        /**下拉刷新*/
        PULL_TO_REFRESH,
        /**释放刷新*/
        RELEASE_TO_REFRESH,
        /**正在刷新*/
        REFRESHING,
        /**刷新结束状态*/
        REFRESH_FINISHED
    }
}
