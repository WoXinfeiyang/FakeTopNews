package com.fxj.faketopnews.main.newsList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fxj.faketopnews.R;
import com.fxj.faketopnews.utils.UiUtils;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/7/17.
 */

public class LinearDividerDecoration extends RecyclerView.ItemDecoration {
    private String tag=LinearDividerDecoration.class.getSimpleName()+"_fxj";
    private float dividerHeight;
    private Paint mPaint;


    public LinearDividerDecoration(Context context,float dividerHeightForDip,int colorResId) {
        dividerHeight=UiUtils.dp2px(dividerHeightForDip);
        mPaint=new Paint();
        mPaint.setColor(context.getResources().getColor(colorResId));
        KLog.i(tag,"dividerHeight="+dividerHeight+",color value="+context.getResources().getColor(colorResId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom= (int) dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childrenCount=parent.getChildCount();
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        for(int i=0;i<childrenCount-1;i++){
            View childView=parent.getChildAt(i);
            float top=childView.getBottom();
            float bottom=childView.getBottom()+dividerHeight;
            c.drawRect(left,top,right,bottom,mPaint);
        }
    }
}
