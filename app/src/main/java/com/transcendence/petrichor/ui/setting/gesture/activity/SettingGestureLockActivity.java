package com.transcendence.petrichor.ui.setting.gesture.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.ui.gesture.utils.GestureLockHelper;
import com.transcendence.ui.gesture.view.CustomGestureLockPainter;
import com.transcendence.ui.gesture.view.GestureLockView;
import com.transcendence.ui.gesture.view.OnGestureLockListener;

/**
 * 手势密码设置
 * Created by MJ on 2018/8/30.
 */

public class SettingGestureLockActivity extends PetrichorBaseActivity implements OnGestureLockListener {
    GestureLockView gestureLockView;
    TextView tvMsg;
    GestureLockHelper gestureLockHelper;

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingGestureLockActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_gesture_lock;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.set_gesture));
        setBackVisibility();
        gestureLockView = findViewById(R.id.glv);
        tvMsg = findViewById(R.id.tv_msg);
        gestureLockView.setPainter(new CustomGestureLockPainter());
        gestureLockView.setGestureLockListener(this);

        gestureLockHelper = new GestureLockHelper();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(String progress) {

    }

    @Override
    public void onComplete(String result) {
        gestureLockHelper.validateForSetting(SettingGestureLockActivity.this, result);
        boolean isOk = gestureLockHelper.isOk();
        tvMsg.setText(gestureLockHelper.getMessage());
        if (isOk) {
            gestureLockView.showCorrectStatus(1000);
            if (gestureLockHelper.isFinish()) {
                finish();
            }
        } else {
            gestureLockView.showErrorStatus(1000);
        }
    }
}
