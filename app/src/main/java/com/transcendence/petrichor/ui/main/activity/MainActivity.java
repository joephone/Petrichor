package com.transcendence.petrichor.ui.main.activity;

import android.Manifest;

import androidx.viewpager.widget.ViewPager;

import com.tbruyelle.rxpermissions3.RxPermissions;
import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.core.permission.PermissionPool;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.main.fragment.MainFragment;
import com.transcendence.petrichor.ui.map.fragment.AmapFragment;

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
                AmapFragment.getInstance(),
                MainFragment.newInstance("主页面"));
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(1);

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


    public void slideToMap(){
        mVp.setCurrentItem(0);
    }
}
