package com.fxj.faketopnews.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.astuetz.PagerSlidingTabStrip;
import com.fxj.faketopnews.Base.BaseFragment;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.model.HttpConstant;
import com.fxj.faketopnews.model.bean.CategoryBean;
import com.fxj.faketopnews.model.bean.NewsListBean;
import com.fxj.faketopnews.presenter.NewsListPresenter;
import com.fxj.faketopnews.utils.UiUtils;
import com.fxj.faketopnews.view_inface.INewsList;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by fuxianjin-hj on 2018/7/3.
 */

public class HomeFragment extends BaseFragment<NewsListPresenter> implements INewsList{

    private final String tag=HomeFragment.class.getSimpleName()+"_fxj";
    private Context mContext;

    private View rootView;
    private PagerSlidingTabStrip mTabStrip;
    private ImageView mAddOperation;
    private ViewPager mViewPager;

    private List<CategoryBean> mSelectedCategoryList =new ArrayList<CategoryBean>();
    private List<String> mSelectedCategoryNameList=new ArrayList<String>();

    private List<NewsListFragment> mNewsListFragments=new ArrayList<NewsListFragment>();
    private HomeAdapter adapter;


    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
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
                mSelectedCategoryNameList.add(categoryName[i]);
                mSelectedCategoryList.add(new CategoryBean(categoryName[i],categoryCode[i]));
            }
            KLog.i(tag,"mSelectedCategoryList="+ mSelectedCategoryList.toString());
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

        mAddOperation = rootView.findViewById(R.id.iv__add_operation);
        mAddOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                mPresenter.getNewsList(mSelectedCategoryList.get(i).mCategoryCode);
            }
        });

        this.mTabStrip=rootView.findViewById(R.id.tab_strip);
        this.mViewPager=rootView.findViewById(R.id.home_view_pager);

        mNewsListFragments=initNewsListFragment();

        adapter = new HomeAdapter(getChildFragmentManager(),mNewsListFragments,mSelectedCategoryNameList);
        this.mViewPager.setAdapter(adapter);

        this.mTabStrip.setTextSize((int) UiUtils.dp2px(16));
        this.mTabStrip.setTextColorResource(R.color.color_black_ff222222);
        this.mTabStrip.setViewPager(this.mViewPager);
    }

    private List<NewsListFragment> initNewsListFragment() {
        List<NewsListFragment> list=new ArrayList<NewsListFragment>();
        for(CategoryBean mCategory:mSelectedCategoryList){
            list.add(NewsListFragment.newInstance(mCategory.mCategoryName,mCategory.mCategoryCode));
        }
        return  list;
    }

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


    @Override
    public void onGetNewsListSuccess(NewsListBean jsonObject) {
        KLog.i(tag,jsonObject.toString());
    }

    @Override
    public void onGetNewsListFailed(String errorMsg) {
        KLog.i(tag,errorMsg);
    }


    class HomeAdapter extends FragmentStatePagerAdapter{
        List<NewsListFragment> mFragList;
        List<String> mCategoryNameList;

        public HomeAdapter(FragmentManager fm, List<NewsListFragment> mFragList, List<String> mCategoryNameList) {
            super(fm);
            this.mFragList = mFragList;
            this.mCategoryNameList = mCategoryNameList;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragList.get(position);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mCategoryNameList.get(position);
        }
    }
}
