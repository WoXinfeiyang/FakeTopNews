package com.fxj.faketopnews.main;

import android.app.Activity;
import android.os.Bundle;

import com.fxj.faketopnews.Base.BaseActivity;
import com.fxj.faketopnews.Base.BasePresenter;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.views.BottomBar.BottomBarContainer;
import com.fxj.faketopnews.views.BottomBar.BottomBarItem;
import com.socks.library.KLog;

public class MainActivity extends BaseActivity {
    private final String tag=MainActivity.class.getSimpleName()+"fxj";
    private BottomBarContainer mBottomBarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBarContainer = findViewById(R.id.bottom_bar_container);
        mBottomBarContainer.setOnItemClickListener(new BottomBarContainer.OnItemClickListener(){

            @Override
            public void onItemClick(BottomBarItem itemView, int position) {
                KLog.i(tag,"底部按钮被点击了,position="+position);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
