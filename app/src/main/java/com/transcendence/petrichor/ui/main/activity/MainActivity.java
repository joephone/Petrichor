package com.transcendence.petrichor.ui.main.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.core.permission.PermissionPool;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.loc.fragment.WeixinLocFragment;
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
        rxPermissions
                .request(PermissionPool.GROUP.PETRICHOR)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now

                    } else {
                        // Oups permission denied
                    }
                });
    }

    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setTitles("主页面");
        adapter.setFragments(
                WeixinLocFragment.newInstance(),  //AmapFragment.getInstance(),
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

    //记录用户首次点击返回键的时间
    private long mExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (System.currentTimeMillis() - mExitTime > 2000) {
                    Toast.makeText(mActivity,"再按一次返回键退出程序",Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
