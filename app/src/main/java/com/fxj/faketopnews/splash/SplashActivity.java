package com.fxj.faketopnews.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.fxj.faketopnews.Base.BaseActivity;
import com.fxj.faketopnews.Base.BasePresenter;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.main.MainActivity;
import com.socks.library.KLog;

public class SplashActivity extends BaseActivity implements View.OnClickListener{
    private final String tag=SplashActivity.class.getSimpleName();
    private TextView skipBtn;


    private static final int MSG_SHOW_SKIP_BTN=0;
    private static final int MSG_AUTO_SKIP =1;

    /**延迟显示跳转按钮的时间*/
    private long delayShowSkipBtnTime=2000;
    /**SplashActivity延迟自动跳转时间*/
    private long delayAutoSkipTime =delayShowSkipBtnTime+2000;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MSG_SHOW_SKIP_BTN:
                    skipBtn.setVisibility(View.VISIBLE);
                    break;
                case MSG_AUTO_SKIP:
                    skip();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        skipBtn=findViewById(R.id.tv_skip_btn);
        skipBtn.setOnClickListener(this);
        skipBtn.setVisibility(View.INVISIBLE);

        Message msgShowSkipBtn=new Message();
        msgShowSkipBtn.what=MSG_SHOW_SKIP_BTN;
        handler.sendMessageDelayed(msgShowSkipBtn,delayShowSkipBtnTime);

        Message msgSkip=new Message();
        msgSkip.what= MSG_AUTO_SKIP;
        handler.sendMessageDelayed(msgSkip, delayAutoSkipTime);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_skip_btn:
                handler.removeMessages(MSG_AUTO_SKIP);
                skip();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.i(tag,"onDestroy,handler="+handler);
        if(handler!=null){
            handler=null;
        }
    }

    private void skip(){
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

}
