package com.fxj.faketopnews.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fuxianjin-hj on 2018/7/3.
 */
/**不能左右滑动的、不能处理Touch事件的ViewPager*/
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return false;
        return super.onInterceptTouchEvent(ev);
    }
}
