package com.fxj.faketopnews.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/7/3.
 */
/**不能左右滑动的、不能处理Touch事件的ViewPager*/
public class NoScrollViewPager extends ViewPager {
    private final String tag=NoScrollViewPager.class.getSimpleName()+"_fxj";
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KLog.i(tag,"NoScrollViewPager.dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        KLog.i(tag,"NoScrollViewPager.onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
     KLog.i(tag,"com.fxj.faketopnews.views.NoScrollViewPager.onTouchEvent");
        return false;
    }
}
