package com.fxj.faketopnews.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fxj.faketopnews.Base.BaseFragment;
import com.fxj.faketopnews.model.bean.CategoryBean;
import com.fxj.faketopnews.presenter.BasePresenter;
import com.socks.library.KLog;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by fuxianjin-hj on 2018/7/6.
 */

public class NewsListFragment extends BaseFragment {

    private String tag=NewsListFragment.class.getSimpleName();

    private static String KEY_CATEGORY_NAME="key_category_name";
    private static String KEY_CATEGORY_CODE="key_category_code";
    private String mCategoryName;
    private String mCategoryCode;

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

        KLog.i(tag,this.mCategoryName+","+this.mCategoryCode);

        RelativeLayout rootView=new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams rlParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(rlParams);

        TextView tv=new TextView(getActivity());
        tv.setText(this.mCategoryName);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(COMPLEX_UNIT_DIP,20);
        rootView.addView(tv);
        RelativeLayout.LayoutParams tvLayoutParams= (RelativeLayout.LayoutParams) tv.getLayoutParams();
        tvLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setLayoutParams(tvLayoutParams);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
