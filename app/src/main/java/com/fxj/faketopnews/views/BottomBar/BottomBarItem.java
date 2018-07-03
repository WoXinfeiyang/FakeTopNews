package com.fxj.faketopnews.views.BottomBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fxj.faketopnews.R;
import com.fxj.faketopnews.utils.UiUtils;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/7/2.
 */

public class BottomBarItem extends RelativeLayout {
    private final String tag=BottomBarItem.class.getSimpleName()+"_fxj";

    private ImageView ivIcon;
    private TextView tvText;

    private int mIconNormalResId;
    private int mIconSelectedResId;
    private String mItemText;
    private float mItemTextSize;
    private int mItemTextColorNormal;
    private int mItemTextColorSelected;

    private float mItemMarginTop;
    /**是否开启触摸效果*/
    private boolean mIsOpenTouchBg;
    /**触摸效果Drawable*/
    private Drawable mTouchDrawable;

    boolean isSelected;

    public BottomBarItem(Context context) {
        this(context,null);
    }

    public BottomBarItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomBarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*给文本颜色设置默认值*/
        this.mItemTextColorNormal=0xFF999999;
        this.mItemTextColorSelected =0xFF46C01B;

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.BottomBarItem);
        initAttribute(ta);
        ta.recycle();
        checkValue();
        KLog.i(tag,toString());
        initView(context);
    }

    /**初始化属性*/
    private void initAttribute(TypedArray ta){
        this.mIconNormalResId=ta.getResourceId(R.styleable.BottomBarItem_icon_normal,-1);
        this.mIconSelectedResId=ta.getResourceId(R.styleable.BottomBarItem_icon_selected,-1);
        this.mItemText=ta.getString(R.styleable.BottomBarItem_item_text);
        this.mItemTextSize= ta.getDimension(R.styleable.BottomBarItem_item_text_size,UiUtils.dp2px(30));
        this.mItemTextColorNormal=ta.getColor(R.styleable.BottomBarItem_item_text_color_normal,mItemTextColorNormal);
        this.mItemTextColorSelected=ta.getColor(R.styleable.BottomBarItem_item_text_color_selected,mItemTextColorSelected);
        this.mItemMarginTop=ta.getDimension(R.styleable.BottomBarItem_item_margin_top,0);

        this.mIsOpenTouchBg=ta.getBoolean(R.styleable.BottomBarItem_open_touch_bg,false);
        this.mTouchDrawable=ta.getDrawable(R.styleable.BottomBarItem_touch_drawable);
    }

    private void initView(Context context) {
        View rootView=View.inflate(context,R.layout.item_bottom_bar,null);
        this.ivIcon=rootView.findViewById(R.id.iv_item_bottom_bar_icon);
        this.tvText =rootView.findViewById(R.id.tv_item_bootom_bar_text);

        this.ivIcon.setImageResource(this.mIconNormalResId);
        this.tvText.setText(mItemText);
        this.tvText.setTextSize(this.mItemTextSize);
        this.tvText.setTextColor(this.mItemTextColorNormal);

        LayoutParams tvTextLayoutParams = (LayoutParams) this.tvText.getLayoutParams();
        if(tvTextLayoutParams!=null){
            tvTextLayoutParams.topMargin= (int) mItemMarginTop;
            this.tvText.setLayoutParams(tvTextLayoutParams);
        }

        if(mIsOpenTouchBg){
            rootView.setBackground(mTouchDrawable);
        }

        addView(rootView);
    }

    private void checkValue() {
        if(this.mIconNormalResId==-1){
            throw  new IllegalArgumentException("还没有设置默认状态下的图标,请设置mIconNormalResId");
        }
        if(this.mItemTextColorSelected==-1){
            throw  new IllegalArgumentException("还没有设置选择状态下的图标,请设置mItemTextColorSelected");
        }
        if(this.mIsOpenTouchBg&&mTouchDrawable==null){
            throw new IllegalArgumentException("开启了触摸效果却没有设置触摸效果Drawable对象");
        }
    }

    public void setSelectedStatus(boolean isSelectedStatus){
        isSelected = isSelectedStatus;
        this.ivIcon.setImageResource(isSelectedStatus?mIconSelectedResId:mIconNormalResId);
        this.tvText.setTextColor(isSelectedStatus?mItemTextColorSelected:mItemTextColorNormal);
    }

    public boolean getSelectedStatus(){
        return this.isSelected;
    }

    @Override
    public String toString() {
        return "BottomBarItem{" +
                "mIconNormalResId=" + mIconNormalResId +
                ", mIconSelectedResId=" + mIconSelectedResId +
                ", mItemText='" + mItemText + '\'' +
                ", mItemTextSize=" + mItemTextSize +
                ", mItemTextColorNormal=" + mItemTextColorNormal +
                ", mItemTextColorSelected=" + mItemTextColorSelected +
                ", isSelected=" + isSelected +
                '}';
    }
}
