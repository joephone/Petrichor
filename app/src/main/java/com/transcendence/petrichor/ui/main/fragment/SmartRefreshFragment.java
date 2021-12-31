package com.transcendence.petrichor.ui.main.fragment;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

/**
 * @Author Joephone on 2021/12/20 0020 下午 2:56
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SmartRefreshFragment extends PetrichorBaseFragment<MainActivity> {

    RefreshLayout refreshLayout;
    Banner banner;

    public static SmartRefreshFragment newInstance(String title) {
        SmartRefreshFragment fragment = new SmartRefreshFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_smart_refresh;
    }

    @Override
    protected void initView() {
        banner = (Banner) findViewById(R.id.home_banner);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    protected void initData() {
        initBanner();
    }

    private void initBanner() {
        //设置banner的各种属性
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
//                .setImageLoader(new GlideImageLoader())
//                .setImages(presenter.getBannerImages())
//                .setBannerAnimation(Transformer.Default)
//                .isAutoPlay(true)
//                .setDelayTime(3000)
//                .setIndicatorGravity(BannerConfig.CENTER)
//                .start();
    }


    @Override
    public void onStart() {
        super.onStart();
        //增加banner的体验
        banner.startAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        //增加banner的体验
        banner.stopAutoPlay();
    }
}
