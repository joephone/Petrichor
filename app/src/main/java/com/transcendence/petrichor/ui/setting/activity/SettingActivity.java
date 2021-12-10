package com.transcendence.petrichor.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

/**
 * @Author Joephone on 2021/12/9 0009 下午 3:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SettingActivity extends PetrichorBaseActivity {


    TextView tvCache;
    private LinearLayout llLanguage,llCheckUpdate,llCache,llAlipay,llLogout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.setting));

        tvCache = findViewById(R.id.tv_cache);
        llLanguage = findViewById(R.id.ll_language);
        llLanguage.setOnClickListener(this);
        llCheckUpdate = findViewById(R.id.ll_check_update);
        llCheckUpdate.setOnClickListener(this);
        llCache = findViewById(R.id.ll_cache);
        llCache.setOnClickListener(this);
        llAlipay = findViewById(R.id.ll_alipay);
        llAlipay.setOnClickListener(this);
        llLogout = findViewById(R.id.ll_logout);
        llLogout.setOnClickListener(this);
//        if(!UserUtils.getInstance().isLogin()){
            llLogout.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    protected void initData() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.fl_right:
//
//                break;
            case R.id.ll_language:
                LanguageActivity.start(getContext());
                break;
            case R.id.ll_check_update:
//                new UpdateDialog.Builder(mActivity)
//                        // 版本名
//                        .setVersionName("1.0.0")
//                        // 是否强制更新
//                        .setForceUpdate(false)
//                        // 更新日志
//                        .setUpdateLog("到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥")
//                        // 下载 URL
//                        .setDownloadUrl("https://dldir1.qq.com/weixin/android/weixin7014android1660.apk")
//                        // 文件 MD5
//                        .setFileMd5(Global.MD5_MAP)
//                        .show();
                break;
            case R.id.ll_cache:
//                presenter.clearCache();
                break;
            case R.id.ll_alipay:
//                AliPayUtils.showDonateDialog(this);
                break;
            case R.id.ll_logout:
//                if(UserUtils.getInstance().toDoIfLogin(getContext())){
//                    presenter.logout();
//                }else {
//                    L.d("未登录");
//                }
                break;
        }
    }
}
