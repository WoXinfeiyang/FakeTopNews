package com.fxj.faketopnews.splash;

import android.os.Bundle;
import android.app.Activity;

import com.fxj.faketopnews.Base.BaseActivity;
import com.fxj.faketopnews.Base.BasePresenter;
import com.fxj.faketopnews.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


}
