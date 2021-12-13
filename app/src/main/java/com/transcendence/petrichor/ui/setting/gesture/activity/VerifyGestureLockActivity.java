package com.transcendence.petrichor.ui.setting.gesture.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.ui.gesture.utils.GestureLockHelper;
import com.transcendence.ui.gesture.view.CustomGestureLockPainter;
import com.transcendence.ui.gesture.view.GestureLockView;
import com.transcendence.ui.gesture.view.OnGestureLockListener;

/**
 * @Author Joephone on 2021/12/13 0013 下午 1:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class VerifyGestureLockActivity extends PetrichorBaseActivity implements OnGestureLockListener {
    GestureLockView gestureLockView;
    GestureLockHelper gestureLockHelper;
    TextView tvMsg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_gesture_lock;
    }

    @Override
    protected void initView() {
        tvMsg = findViewById(R.id.tv_msg);
        tvMsg.setVisibility(View.GONE);
        gestureLockView = findViewById(R.id.glv);
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
        gestureLockHelper.validateForChecking(result, VerifyGestureLockActivity.this);
        String message = gestureLockHelper.getMessage();
        Toast.makeText(VerifyGestureLockActivity.this, message, Toast.LENGTH_SHORT).show();
        boolean isOk = gestureLockHelper.isOk();
        if (isOk) {
            gestureLockView.clearView();
        } else {
            gestureLockView.showErrorStatus(1000);
        }
        if (gestureLockHelper.isFinish()) {
            finish();
        }
    }
}
