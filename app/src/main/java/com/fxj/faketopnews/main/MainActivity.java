package com.fxj.faketopnews.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.fxj.faketopnews.Base.BaseActivity;
import com.fxj.faketopnews.Base.BaseFragment;
import com.fxj.faketopnews.Base.BasePresenter;
import com.fxj.faketopnews.R;
import com.fxj.faketopnews.views.BottomBar.BottomBarContainer;
import com.fxj.faketopnews.views.BottomBar.BottomBarItem;
import com.fxj.faketopnews.views.NoScrollViewPager;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private final String tag=MainActivity.class.getSimpleName()+"_fxj";

    private NoScrollViewPager mViewPager;
    private BottomBarContainer mBottomBarContainer;
    private List<BaseFragment> mFragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=findViewById(R.id.view_pager);
        mBottomBarContainer = findViewById(R.id.bottom_bar_container);
        mFragList= initData();
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager(),mFragList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBarContainer.setSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomBarContainer.setOnItemClickListener(new BottomBarContainer.OnItemClickListener(){

            @Override
            public void onItemClick(BottomBarItem itemView, int position) {
                KLog.i(tag,"底部按钮被点击了,position="+position);
                mViewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private List<BaseFragment> initData(){
        List<BaseFragment> mFragmentList=new ArrayList<BaseFragment>();
        mFragmentList.add(HomeFragment.newInstance());
        mFragmentList.add(VideoFragment.newInstance());
        mFragmentList.add(MicroFragment.newInstance());
        mFragmentList.add(MeFragment.newInstance());
        return mFragmentList;
    }

    class MainAdapter extends FragmentStatePagerAdapter{

        List<BaseFragment> mFragmentList;

        public MainAdapter(FragmentManager fm, List<BaseFragment> mFragmentList) {
            super(fm);
            this.mFragmentList = mFragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }
    }
}
