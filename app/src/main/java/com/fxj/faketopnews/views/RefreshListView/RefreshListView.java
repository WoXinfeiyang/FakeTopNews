package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;



public class RefreshListView extends RelativeLayout {




    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**网络请求回调接口*/
    public interface OnDataLoaderCallback<H,F>{
        public void onHeaderDataLoaderCallback(H data);
        public void onFooterDataLoaderCallback(F data);
    }
}
