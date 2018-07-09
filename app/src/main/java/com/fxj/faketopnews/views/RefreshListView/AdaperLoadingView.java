package com.fxj.faketopnews.views.RefreshListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fxj.faketopnews.R;

/**
 * Created by fuxianjin-hj on 2018/7/9.
 */

public class AdaperLoadingView extends RelativeLayout {

    private TextView tvLoadingText;

    public AdaperLoadingView(Context context) {
        super(context);
    }

    public AdaperLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaperLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context ct){
        LayoutInflater.from(ct).inflate(R.layout.adapter_loading_view_layout,this);
        tvLoadingText = findViewById(R.id.tv_loading_text);
    }

    public void update(){}
}
