package com.fxj.faketopnews;

import android.app.Activity;
import android.os.Bundle;

import com.fxj.faketopnews.Base.BaseActivity;
import com.fxj.faketopnews.Base.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
