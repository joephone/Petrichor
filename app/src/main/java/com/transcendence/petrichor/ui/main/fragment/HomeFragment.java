package com.transcendence.petrichor.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.trancesdence.utils.DensityUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.utils.BannerImageLoader;
import com.transcendence.petrichor.utils.WorkUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.Arrays;

/**
 * @Author Joephone on 2021/12/2 0002 下午 5:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class HomeFragment extends PetrichorBaseFragment<MainActivity> {

    private Banner banner;
    String[] urls = {"http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg"};

    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, title);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_home;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.home));
        initBanner();
    }

    @Override
    protected void initData() {

    }


    /**
     * 初始化轮播图
     */
    private void initBanner() {
        banner = (Banner) findViewById(R.id.banner);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtils.getScreenWidth(getActivity()), DensityUtils.getScreenWidth(getActivity()));
//        banner.setLayoutParams(lp);

        int screenWidth = DensityUtils.getScreenWidth(getActivity());
        int screenHeight = DensityUtils.getScreenHeight(getActivity());
        int height = (int) (Math.min(screenWidth, screenHeight) * (9F / 16F));
        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));

        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                WorkUtils.imageBrower(getActivity(), position - 1, urls);
            }
        });
        banner.setImages(Arrays.asList(urls)).setImageLoader(new BannerImageLoader()).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            banner.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
