package com.transcendence.petrichor.ui.setting.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.mine.activity.PaySubmitActivity;
import com.transcendence.petrichor.ui.setting.fingerprint.fragment.FingerprintAuthenticationDialogFragment;
import com.transcendence.petrichor.ui.setting.gesture.activity.SettingGestureLockActivity;
import com.transcendence.petrichor.ui.setting.gesture.activity.VerifyGestureLockActivity;
import com.transcendence.ui.gesture.utils.GestureLockHelper;

/**
 * @Author Joephone on 2021/12/13 0013 上午 11:30
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MultiSettingActivity extends PetrichorBaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_multi;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.multi_setting));
        setBackVisibility();
        findViewById(R.id.ll_finger_print).setOnClickListener(this);
        findViewById(R.id.ll_set_gesture).setOnClickListener(this);
        findViewById(R.id.ll_verify_gesture).setOnClickListener(this);
        findViewById(R.id.ll_pay).setOnClickListener(this);
        findViewById(R.id.ll_app_setting).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_finger_print:
                FingerprintAuthenticationDialogFragment dialogFragment = new FingerprintAuthenticationDialogFragment();
                dialogFragment.show(getFragmentManager(), "FingerprintAuthenticationDialogFragment");
                break;
            case R.id.ll_set_gesture:
                SettingGestureLockActivity.start(getActivity());
                break;
            case R.id.ll_verify_gesture:
                if (GestureLockHelper.isHasGesturePassword(mActivity)) {
                    Intent intent = new Intent(mActivity, VerifyGestureLockActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mActivity, "没有设置手势密码，请先去设置", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_pay:
                PaySubmitActivity.start(getActivity());
                break;
            case R.id.ll_app_setting:
                appSetting(this);
                break;
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MultiSettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用详情界面
     */
    public static void appSetting(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

}
