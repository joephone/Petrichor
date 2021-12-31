package com.transcendence.petrichor.ui.main.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.trancesdence.utils.AndroidVersionCheckUtils;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.SPUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.dialog.BaseDialog;
import com.transcendence.petrichor.dialog.DialogMessage;
import com.transcendence.petrichor.ui.setting.eventbus.LanguageEvent;
import com.transcendence.ui.textview.CountDownTextView;
import com.transcendence.ui.utils.L;

import org.greenrobot.eventbus.EventBus;

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
    }


    @Override
    protected void initData() {
        if(AndroidVersionCheckUtils.isKitkatUnder()){
            showCheckEdition();
        } else {
            countDown();
            mTvSkip.start();
        }
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

    /**
     * Show Check Edition
     * Must run on UIThread
     */
    protected void showCheckEdition() {
        new DialogMessage.Builder(mActivity)
                .setTitle(R.string.tip)
                // 内容必须要填写
                .setMessage(R.string.privacy_check_android_edition)
                .setConfirm(getString(R.string.ok))
                // 设置 null 表示不显示取消按钮
                .setCancel(null)
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new DialogMessage.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        LibApplication.exitApp();
                    }

                })
                .show();
    }
}
