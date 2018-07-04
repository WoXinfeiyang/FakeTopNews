package com.fxj.faketopnews.main;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fxj.faketopnews.Base.BaseFragment;
import com.fxj.faketopnews.Base.BasePresenter;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.model.HttpConstant;
import com.fxj.faketopnews.model.bean.CategoryBean;
import com.socks.library.KLog;

import java.util.ArrayList;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by fuxianjin-hj on 2018/7/3.
 */

public class HomeFragment extends BaseFragment {

    private final String tag=HomeFragment.class.getSimpleName()+"_fxj";
    private Context mContext;
    private View rootView;

    private ArrayList<CategoryBean> mCategoryList=new ArrayList<CategoryBean>();

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /*当Fragment被添加到Activity时回调该方法,该方法只被回调一次*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KLog.i(tag,"HomeFragment.onAttach");
        mContext = context;
    }

    /*创建Fragment时回调该方法,改方法只被回调一次*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(tag,"HomeFragment.onCreate");

        String[]categoryName=getResources().getStringArray(R.array.category_name);
        String[]categoryCode=getResources().getStringArray(R.array.category_code);
        if(categoryName!=null&&categoryCode!=null&&categoryName.length==categoryCode.length){
            for(int i=0;i<categoryName.length;i++){
                mCategoryList.add(new CategoryBean(categoryName[i],categoryCode[i]));
            }
            KLog.i(tag,"mCategoryList="+mCategoryList.toString());
        }
    }

    /*每次创建、绘制Fragment的View组件时回调该方法,Fragment将显示该方法所返回的View*/
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        KLog.i(tag,"HomeFragment.onCreateView");
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_home,container,false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KLog.i(tag,"HomeFragment.onViewCreated");
        rootView.findViewById(R.id.tv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.i(tag,"首页按钮被点击了!");
                int i=0;
                getNewsList(mCategoryList.get(i).mCategoryCode,System.currentTimeMillis()/1000-10,System.currentTimeMillis()/1000);
            }
        });
    }
    long mLastTime;
    private void getNewsList(String categoryCode,long lastTime,long currentTime){
        RequestParams requestParams=new RequestParams(new HttpCycleContext(){

            @Override
            public String getHttpTaskKey() {
                return "HttpTaskKey_123" + hashCode();
            }
        });

        requestParams.addFormDataPart("category",categoryCode);
        requestParams.addFormDataPart("min_behot_time",lastTime);
        requestParams.addFormDataPart("last_refresh_sub_entrance",currentTime);
        HttpRequest.post(HttpConstant.GET_ARTICLE_LIST,requestParams,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                KLog.i(tag,jsonObject.toJSONString());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                KLog.i(tag,msg);
            }
        });
    }

    /*当Fragment所在的Activity被启动完成之后回调该方法*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.i(tag,"HomeFragment.onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        KLog.i(tag,"com.fxj.faketopnews.main.HomeFragment.onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.i(tag,"HomeFragment.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.i(tag,"HomeFragment.onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.i(tag,"HomeFragment.onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KLog.i(tag,"HomeFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.i(tag,"HomeFragment.onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KLog.i(tag,"HomeFragment.onDetach");
    }
}
