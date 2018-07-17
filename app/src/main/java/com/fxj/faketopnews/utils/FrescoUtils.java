package com.fxj.faketopnews.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static java.lang.Runtime.*;

/**
 * Created by fuxianjin-hj on 2018/7/17.
 */

public class FrescoUtils {
    private String tag=FrescoUtils.class.getSimpleName()+"_fxj";

    private static volatile FrescoUtils sInstance;

    public static FrescoUtils getsInstance(){
        if(sInstance==null){
            synchronized (FrescoUtils.class){
                if(sInstance==null){
                    sInstance=new FrescoUtils();
                }
            }
        }
        return sInstance;
    }

    private FrescoUtils() {
    }

    /**Fresco初始化*/
    public void  initialize(Context context){
        Fresco.initialize(context,getConfig(context));
    }

    private static long TIME_OUT=10*1000;
    private OkHttpClient newOkHttpClient(){
        OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder();
        clientBuilder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        clientBuilder.readTimeout(TIME_OUT,TimeUnit.MILLISECONDS);
        clientBuilder.writeTimeout(TIME_OUT,TimeUnit.MILLISECONDS);
        clientBuilder.retryOnConnectionFailure(false);
        return clientBuilder.build();
    }


    private static long RUN_TIME_MAX_MEMORY=Runtime.getRuntime().maxMemory();
    private int DEFAULT_MAX_CACHE_SIZE=150*1024*1024;
    private int DEFAULT_MAX_DISK_CACHE_SIZE=2*DEFAULT_MAX_CACHE_SIZE;
    private ImagePipelineConfig getConfig(Context mContext){
        int maxCacheSize= (int) Math.min(RUN_TIME_MAX_MEMORY/8,DEFAULT_MAX_CACHE_SIZE);

        KLog.i(tag,"Runtime.getRuntime().maxMemory()="+RUN_TIME_MAX_MEMORY+",default_max_cache_size="+DEFAULT_MAX_CACHE_SIZE);

        final MemoryCacheParams memoryCacheParams=new MemoryCacheParams(maxCacheSize,/*内存缓存总的大小,以字节为单位*/
            Integer.MAX_VALUE,/*内存缓存中缓存文件的最大数目*/
                DEFAULT_MAX_CACHE_SIZE/5,/*内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位*/
        Integer.MAX_VALUE,/*缓存队列中缓存文件的最大数目*/
                Integer.MAX_VALUE/*缓存中单个缓存文件的最大大小*/
        );

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams=new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return memoryCacheParams;
            }
        };

        DiskCacheConfig mainDiskCacheConfig=DiskCacheConfig.newBuilder(mContext)
                .setBaseDirectoryName(FileUtils.getCacheDir())
                .setMaxCacheSize(DEFAULT_MAX_DISK_CACHE_SIZE)/*设置磁盘缓存的最大值*/
                .setMaxCacheSizeOnLowDiskSpace(DEFAULT_MAX_DISK_CACHE_SIZE/5)/*设置当手机的磁盘空间比较小的时候磁盘缓存的最大值*/
                .setMaxCacheSizeOnVeryLowDiskSpace(DEFAULT_MAX_DISK_CACHE_SIZE/30)/*设置当手机的磁盘空间极小的时候磁盘缓存的最大值*/
                .build();

        DiskCacheConfig smallImageDiskCacheConfig=DiskCacheConfig.newBuilder(mContext)
                .setBaseDirectoryName(FileUtils.getSmallCacheDir())
                .setMaxCacheSize(DEFAULT_MAX_DISK_CACHE_SIZE)/*设置小图磁盘缓存的最大值*/
                .setMaxCacheSizeOnLowDiskSpace(DEFAULT_MAX_DISK_CACHE_SIZE/30)/*设置当手机的磁盘空间比较小的时候小图磁盘缓存的最大值*/
                .setMaxCacheSizeOnVeryLowDiskSpace(DEFAULT_MAX_DISK_CACHE_SIZE/60)/*设置当手机的磁盘空间极小的时候小图磁盘缓存的最大值*/
                .build();

        ImagePipelineConfig imagePipelineConfig= OkHttpImagePipelineConfigFactory.newBuilder(mContext,newOkHttpClient())
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)/*已解码图片的内存缓存,一级缓存*/
                .setEncodedMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)/*未解码图片的内存缓存,二级缓存*/
                .setMainDiskCacheConfig(mainDiskCacheConfig)/*磁盘缓存,三级缓存*/
                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)/*小图磁盘缓存*/
                .setDownsampleEnabled(true)/*向下采样*/
                .build();

        return imagePipelineConfig;
    }

    public void displayImage(SimpleDraweeView draweeView, Uri uri,boolean autoPlayAnimationsEnable,boolean tapToRetryEnable,ControllerListener listener){
        if(uri==null){
            return;
        }
        ImageRequest imageRequest= ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .setAutoRotateEnabled(true)
                .build();
        DraweeController draweeController=Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(imageRequest)
                .setControllerListener(listener)
                .setAutoPlayAnimations(autoPlayAnimationsEnable)
                .setTapToRetryEnabled(tapToRetryEnable)
                .build();
        draweeView.setController(draweeController);
    }

    public void displayImage(SimpleDraweeView draweeView, String uri,boolean autoPlayAnimationsEnable,boolean tapToRetryEnable,ControllerListener listener){
        if(TextUtils.isEmpty(uri)){
            return;
        }
        displayImage(draweeView, Uri.parse(uri),autoPlayAnimationsEnable,tapToRetryEnable,listener);
    }

    public void displayImage(SimpleDraweeView draweeView, String uri){
        displayImage(draweeView,uri,true,true,null);
    }

    public void displayImage(SimpleDraweeView draweeView, Uri uri){
        displayImage(draweeView,uri,true,true,null);
    }
}
