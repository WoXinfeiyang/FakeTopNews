package com.fxj.faketopnews.main;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.utils.FileUtils;
import com.fxj.faketopnews.utils.FrescoUtils;
import com.socks.library.KLog;

public class TestActivity extends Activity {
    private String tag=TestActivity.class.getSimpleName()+"_fxj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        setContentView(R.layout.item_view_right_pic_news);
//        SimpleDraweeView testDraweeView=findViewById(R.id.drawee_view_test);
//        Uri uri=Uri.parse("http://n.sinaimg.cn/fashion/transform/116/w550h366/20180604/KTbk-hcmurvh3788668.jpg");
////        testDraweeView.setImageURI(uri);
//        FrescoUtils.getsInstance().displayImage(testDraweeView, uri, true, true, new ControllerListener<ImageInfo>() {
//            @Override
//            public void onSubmit(String id, Object callerContext) {
//
//            }
//
//            @Override
//            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                KLog.i(tag,"Image Width="+imageInfo.getWidth()+",Image Height="+imageInfo.getHeight());
//            }
//
//            @Override
//            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
//
//            }
//
//            @Override
//            public void onIntermediateImageFailed(String id, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onRelease(String id) {
//
//            }
//        });
    }

}
