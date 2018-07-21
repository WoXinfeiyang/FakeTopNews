package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/1/10.
 */

public class RefreshListViewHeader extends TextView implements SwipeRefreshTrigger,SwipeTrigger {
    private final String tag=RefreshListViewHeader.class.getSimpleName()+"_fxj";
    public RefreshListViewHeader(Context context) {
        super(context);
    }

    public RefreshListViewHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPrepare() {
        setText("==onPrepare");
        KLog.i(tag,"==onPrepare");
    }

    @Override
    public void onRefresh() {
        setText("正在刷新中……==onRefresh");
        KLog.i(tag,"正在刷新中……==onRefresh");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        setText("==onMove");
        KLog.i(tag,"==onMove");
    }

    @Override
    public void onRelease() {
        setText("==onRelease");
        KLog.i(tag,"==onRelease");
    }

    @Override
    public void onComplete() {
        setText("==onComplete");
        KLog.i(tag,"==onComplete");
    }

    @Override
    public void onReset() {
        setText("==onReset");
        KLog.i(tag,"==onReset");
    }
}
