package com.transcendence.petrichor.ui.mine.activity;

import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.main.fragment.MainFragment;

public class MainActivity extends PetrichorBaseActivity {


    private ViewPager mVp;
    /**
     * Goweii的vpAdapter
     */
    private GoweiiFragmentPagerAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mVp = findViewById(R.id.vp);
        initVP();
    }

    @Override
    protected void initData() {

    }

    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setTitles("主页面");
        adapter.setFragments(
                MainFragment.newInstance("主页面"));
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(0);

//        showPrivacyPolicyDialog();
//
//        Permissions.with(mActivity)
//                .permission(PermissionPool.GROUP.STORAGE)
//                .request(new OnPermissionCallback() {
//                    @Override
//                    public void onGranted(List<String> permissions, boolean all) {
//                        L.d("download enable");
//                    }
//                });
    }
}
