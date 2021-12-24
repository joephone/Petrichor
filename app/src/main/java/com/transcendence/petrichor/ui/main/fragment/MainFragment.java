package com.transcendence.petrichor.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.ui.mine.fragment.MineFragment;
import com.transcendence.petrichor.ui.mine.fragment.UserFragment;
import com.transcendence.ui.widget.custom.TabView;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Joephone on 2021/12/2 0002 上午 11:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MainFragment extends PetrichorBaseFragment<MainActivity> implements View.OnClickListener{

    public static MainFragment newInstance(String title) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, title);
//        fragment.setArguments(args);
        return fragment;
    }

    TabView mTabOne;
    TabView mTabTwo;
    TabView mTabThree;
    TabView mTabFour;
    TabView mTabFive;


    private List<TabView> mTabs = new ArrayList<>();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private ViewPager mVpMain;
    private GoweiiFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_main;
    }

    @Override
    protected void initView() {
        mVpMain = (ViewPager) findViewById(R.id.vp);
        mTabOne = (TabView) findViewById(R.id.tabOne);
        mTabTwo = (TabView) findViewById(R.id.tabTwo);
        mTabThree = (TabView)findViewById(R.id.tabThree);
//        mTabFour = rootView.findViewById(R.id.tabFour);
//        mTabFive = rootView.findViewById(R.id.tabFive);
        mTabOne.setOnClickListener(this);
        mTabTwo.setOnClickListener(this);
        mTabThree.setOnClickListener(this);
//        mTabFour.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initVP();
        initTabs();
    }

    private void initVP() {
        //预加载   , ScrollFragment.newInstance("项目")   //公众号
        mVpMain.setOffscreenPageLimit(4);
        adapter = new GoweiiFragmentPagerAdapter(getChildFragmentManager());
        adapter.setFragments(
                HomeFragment.newInstance("主页面"),
                SmartRefreshFragment.newInstance("刷新"),
//                , BeautyFragment.newInstance("福利社")
//                , NewWxArticleListFragment.newInstance("")
                MineFragment.newInstance("我的"));
//                UserFragment.newInstance("我的"));
        mVpMain.setAdapter(adapter);

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * @param position
             * @param positionOffset  当前页面偏移的百分比
             * @param positionOffsetPixel 当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
//                L.d("onPageScrolled pos=" + position + ",positionOffset=" + positionOffset);
                if(positionOffset>0 && position< mTabs.size()){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position+1);
                    right.setProgress(positionOffset);
                    left.setProgress(1-positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
//                L.d("onPageSelected pos=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {
//                L.d("onPageScrollStateChanged pos=" + position);
            }
        });
    }

    private void initTabs() {
        mTabOne.setIconAndText(R.drawable.ic_tab_home_white_24dp,R.drawable.ic_tab_home_github_green_24dp,getContext().getString(R.string.tab_home));
        mTabTwo.setIconAndText(R.drawable.ic_tab_wx_public_white_24dp,R.drawable.ic_tab_wx_public_githubgreen_24dp,getContext().getString(R.string.tab_hierarchy));
//        mTabThree.setIconAndText(R.drawable.ic_tab_wx_public_white_24dp,R.drawable.ic_tab_wx_public_githubgreen_24dp,getContext().getString(R.string.wan_tab_three));
        mTabThree.setIconAndText(R.drawable.ic_tab_person_white_24dp, R.drawable.ic_tab_person_githubgreen_24dp, getContext().getString(R.string.tab_me));
//        mTabFour.setIconAndText(R.drawable.ic_tab_person_white_24dp,R.drawable.ic_tab_person_githubgreen_24dp,getContext().getString(R.string.wan_tab_five));
        mTabs.add(mTabOne);mTabs.add(mTabTwo);mTabs.add(mTabThree);
//        mTabs.add(mTabFour);  mTabs.add(mTabFive);
        setCurrentTabs(0);
    }

    private void setCurrentTabs(int position) {
        for (int i = 0; i < mTabs.size(); i++) {
            if(i==position){
                mTabs.get(i).setProgress(1);
            }else {
                mTabs.get(i).setProgress(0);
            }
        }
        mVpMain.setCurrentItem(position,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tabOne:
                setCurrentTabs(0);
                break;
            case R.id.tabTwo:
                setCurrentTabs(1);
                break;
            case R.id.tabThree:
                setCurrentTabs(2);
                break;
        }
    }
}
