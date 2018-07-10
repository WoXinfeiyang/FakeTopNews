package com.fxj.faketopnews.main.newsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fxj.faketopnews.Base.BaseFragment;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.model.bean.NewsContentBean;
import com.fxj.faketopnews.model.bean.NewsListBean;
import com.fxj.faketopnews.model.bean.NewsListTipsBean;
import com.fxj.faketopnews.presenter.BasePresenter;
import com.fxj.faketopnews.views.RefreshListView.RefreshListView;
import com.socks.library.KLog;

/**
 * Created by fuxianjin-hj on 2018/7/6.
 */

public class NewsListFragment extends BaseFragment {

    private String tag=NewsListFragment.class.getSimpleName()+"_fxj";

    private static String KEY_CATEGORY_NAME="key_category_name";
    private static String KEY_CATEGORY_CODE="key_category_code";
    private String mCategoryName;
    private String mCategoryCode;
    /**是否是视频页*/
    private boolean isVideoList;

    private View rootView;
    private RefreshListView<NewsContentBean,NewsContentBean,NewsListTipsBean> mRefreshListView;

//    private NewsListDataLoader mDataLoader;

    public static NewsListFragment newInstance(String mCategoryName,String mCategoryCode) {
        NewsListFragment fragment = new NewsListFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_NAME,mCategoryName);
        bundle.putString(KEY_CATEGORY_CODE,mCategoryCode);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        this.mCategoryName=getArguments().getString(KEY_CATEGORY_NAME);
        this.mCategoryCode=getArguments().getString(KEY_CATEGORY_CODE);

        if(mCategoryCode.equals("video")){
            isVideoList = true;
        }else{
            isVideoList=false;
        }

        KLog.i(tag,this.mCategoryName+","+this.mCategoryCode);
        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_news_list,container,false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)rootView.findViewById(R.id.tv_news_list_text)).setText(this.mCategoryName);
        this.mRefreshListView=rootView.findViewById(R.id.refresh_list_view);

//        this.mDataLoader=new NewsListDataLoader(this.mCategoryCode);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
