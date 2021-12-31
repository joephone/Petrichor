package com.transcendence.petrichor.ui.mine.activity;

import android.content.Context;
import android.content.Intent;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.setting.activity.SettingActivity;

/**
 * @Author Joephone on 2021/12/29 0029 下午 12:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class AboutMeActivity extends PetrichorBaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.about_me));
        setBackVisibility();
    }

    @Override
    protected void initData() {

    }
}
