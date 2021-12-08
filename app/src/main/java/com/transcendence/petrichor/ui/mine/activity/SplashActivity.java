package com.transcendence.petrichor.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.transcendence.core.global.Global;
import com.transcendence.core.permission.PermissionPool;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.ui.textview.CountDownTextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author Joephone on 2021/12/1 0001 下午 5:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SplashActivity extends PetrichorBaseActivity implements View.OnClickListener{

    private final int count = 5;
    private ImageView ivLauncher;
    private RelativeLayout clContainer;
    private CountDownTextView mTvSkip;
    private Timer mTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ivLauncher = findViewById(R.id.iv_logo);
        clContainer = findViewById(R.id.rl_container);
        mTvSkip = findViewById(R.id.tv_skip);
        mTvSkip.setOnClickListener(this);
        mTvSkip.setDuration(Global.ANIM_DURATION_TIME);
//        int[] ids = Global.mLauncherIds;
//        int index = new Random().nextInt(ids.length);
//        GlideUtils.getInstance().loadImageFromLocal(ids[index], ivLauncher);
//        GlideUtils.showImageView(mActivity,R.drawable.img_default_book,ids[index],ivLauncher);
//        initStartAnim();
        countDown();
        mTvSkip.start();
    }


    @Override
    protected void initData() {
        rxPermissions
                .request(PermissionPool.GROUP.PETRICHOR)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        startMain();
                    } else {
                        // Oups permission denied
                    }
                });
    }

    public void countDown() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                startMain();
            }
        }, Global.ANIM_DURATION_TIME);
    }

    private void startMain() {
        start(MainActivity.class);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                mTvSkip.stop();
                mTimer.cancel();
                startMain();
                break;
        }
    }
}
