package com.transcendence.petrichor.ui.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.transcendence.core.global.Global;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

import timber.log.Timber;

/**
 * @Author Joephone on 2021/12/2 0002 下午 3:56
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class LoginActivity extends PetrichorBaseActivity {



    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.overridePendingTransition(R.anim.swipeback_activity_open_bottom_in,
                    R.anim.swipeback_activity_open_top_out);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View view) {
        Log.d(Global.TAG,"onRightClick");
        // 跳转到注册界面
        RegisterActivity.start(LoginActivity.this);
    }
}
