package com.fxj.faketopnews.views.BottomBar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxianjin-hj on 2018/7/2.
 */

public class BottomBarContainer extends LinearLayout {
    private final String tag=BottomBarContainer.class.getSimpleName()+"fxj";

    private List<BottomBarItem> mItemViews;
    private int mCurrentPosition;
    private int childenCount;

    public BottomBarContainer(Context context) {
        this(context,null);
    }

    public BottomBarContainer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomBarContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mItemViews=new ArrayList<BottomBarItem>();
        setOrientation(HORIZONTAL);

    }


    /*该方法将在xml布局文件中所有的子View都加载到ViewGroup后回调改方法*/
    @Override
    protected void onFinishInflate() {
        KLog.i(tag,"onFinishInflate");
        super.onFinishInflate();
        init();
    }



    private void init(){
        childenCount = getChildCount();
        if(childenCount<1){
            throw new IllegalArgumentException("BottomBarContainer容器内目前有"+childenCount+"个BottomBarItem,请至少添加一个BottomBarItem");
        }
        for(int i=0;i<childenCount;i++){
            View child=getChildAt(i);
            if(!(child instanceof BottomBarItem)){
                throw new IllegalArgumentException("BottomBarContainer容器内子View必须为BottomBarItem!");
            }
            BottomBarItem item= (BottomBarItem) child;
            item.setOnClickListener(new BottomBarContainerClickListener(i));/*给每个子View绑定View.OnClickListener监听器*/
            mItemViews.add(item);
        }
        KLog.i(tag,"BottomBarContainer容器内当前共有"+childenCount+"个BottomBarItem子View");
        resetSelectStatus();
        ((BottomBarItem)mItemViews.get(mCurrentPosition)).setSelectedStatus(true);
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }

    public void resetSelectStatus(){
        if(mItemViews==null||mItemViews.size()<1){
            return;
        }
        for(BottomBarItem item:mItemViews){
            item.setSelectedStatus(false);
        }
    }

    public interface OnItemClickListener{
        /**
         * BootomBarContainer容器中点击事件回调方法
         * @param itemView BottomBarItem对象
         * @param position BootomBarContainer容器中点击的位置
         * */
        public void onItemClick(BottomBarItem itemView,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**可以记录点击位置的View.OnClickListener的实现子类*/
    private class BottomBarContainerClickListener implements View.OnClickListener{
        int mCurrentIndex;

        public BottomBarContainerClickListener(int mCurrentPosition) {
            this.mCurrentIndex = mCurrentPosition;
        }
        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick((BottomBarItem) v,this.mCurrentIndex);
            }
            resetSelectStatus();
            ((BottomBarItem)mItemViews.get(mCurrentIndex)).setSelectedStatus(true);
            BottomBarContainer.this.mCurrentPosition=this.mCurrentIndex;
        }
    }
}
