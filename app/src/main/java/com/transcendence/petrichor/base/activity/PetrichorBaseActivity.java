package com.transcendence.petrichor.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions3.RxPermissions;
import com.transcendence.core.base.BaseActivity;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.SPUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.action.ToastAction;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.ui.main.activity.SplashActivity;
import com.transcendence.petrichor.ui.setting.eventbus.LanguageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

/**
 * @Author Joephone on 2021/12/2 0002 上午 9:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class PetrichorBaseActivity extends BaseActivity
        implements ToastAction {

    protected final RxPermissions rxPermissions = new RxPermissions(this);
    protected boolean isBackVisible = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        changeAppLanguage();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LanguageEvent event) {
        changeAppLanguage();
        recreate();//刷新界面
//        reStart();
    }

    public void changeAppLanguage() {
        String strLan = SPUtils.getInstance().getString(Global.SP_KEY.LOCALE_LANGUAGE);
        L.d(strLan);
        if(!TextUtils.isEmpty(strLan)){
            // 本地语言设置
            Locale myLocale = new Locale(strLan);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }
    }

    private void reStart(){
        //重启app,这一步一定要加上，如果不重启app，可能打开新的页面显示的语言会不正确
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void clickBack(View view) {
        finish();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        setBackVisibility();
    }

    protected void setBackVisibility() {
        FrameLayout fl_back = findViewById(R.id.fl_back);
        ImageView iv_left = findViewById(R.id.iv_left);
        if (isBackVisible) {
            fl_back.setVisibility(View.VISIBLE);
            iv_left.setVisibility(View.VISIBLE);
            fl_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBack(view);
                }
            });
        } else {
            fl_back.setVisibility(View.INVISIBLE);
        }
    }
}
