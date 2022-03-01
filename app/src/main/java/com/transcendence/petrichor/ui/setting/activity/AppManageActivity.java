package com.transcendence.petrichor.ui.setting.activity;

import android.view.View;
import android.widget.TextView;

import com.transcendence.core.base.app.LibApplication;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

/**
 * @Author Joephone on 2022/3/1 0001 上午 11:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class AppManageActivity extends PetrichorBaseActivity implements View.OnClickListener {

    TextView mTvActClose,mTvExitApp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_appmanage;
    }

    @Override
    protected void initView() {
        mTvActClose = findViewById(R.id.tv_close_activity);
        mTvExitApp = findViewById(R.id.tv_exit_app);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_close_activity:
                LibApplication.killActivity(AppManageActivity.this);
                break;
            case R.id.tv_exit_app:
                LibApplication.exitApp();
                break;

        }
    }
}
