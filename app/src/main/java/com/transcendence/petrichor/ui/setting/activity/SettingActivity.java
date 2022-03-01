package com.transcendence.petrichor.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trancesdence.utils.AppUtils;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.permission.PermissionPool;
import com.transcendence.core.utils.CacheUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.dialog.BaseDialog;
import com.transcendence.petrichor.dialog.DialogMessage;
import com.transcendence.petrichor.dialog.UpdateDialog;

/**
 * @Author Joephone on 2021/12/9 0009 下午 3:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SettingActivity extends PetrichorBaseActivity {

    TextView tvCache;
    private LinearLayout llLogout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.setting));
        setBackVisibility();
        tvCache = findViewById(R.id.tv_cache);
        findViewById(R.id.ll_language).setOnClickListener(this);
        findViewById(R.id.ll_text_size_set).setOnClickListener(this);
        findViewById(R.id.ll_check_update).setOnClickListener(this);
        findViewById(R.id.ll_cache).setOnClickListener(this);
        findViewById(R.id.ll_multi).setOnClickListener(this);
        llLogout = findViewById(R.id.ll_logout);
        llLogout.setOnClickListener(this);
//        if(!UserUtils.getInstance().isLogin()){
            llLogout.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    protected void initData() {
        getCacheSize();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_language:
                start(LanguageActivity.class);
                break;
            case R.id.ll_text_size_set:
                start(TextSizeSetActivity.class);
                break;
            case R.id.ll_check_update:
                rxPermissions
                        .request(PermissionPool.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                // I can control the camera now
                                // 升级对话框
                                new UpdateDialog.Builder(mActivity)
                                        // 版本名
                                        .setVersionName(AppUtils.getVersionName(mActivity))
                                        // 是否强制更新
                                        .setForceUpdate(false)
                                        // 更新日志
                                        .setUpdateLog("到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥")
                                        // 下载 URL
                                        .setDownloadUrl("https://dldir1.qq.com/weixin/android/weixin7014android1660.apk")
                                        // 文件 MD5
//                        .setFileMd5(Global.MD5_MAP)
                                        .show();
                            } else {
                                // Oups permission denied
                            }
                        });

                break;
            case R.id.ll_cache:
                new DialogMessage.Builder(mActivity)
                        .setTitle(R.string.tip)
                        // 内容必须要填写
                        .setMessage(R.string.confirm_to_clean_cache)
                        .setConfirm(getString(R.string.ok))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.cancel))
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new DialogMessage.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                CacheUtils.clearAllCache();
                                getCacheSize();
                            }

                        })
                        .show();
                break;
            case R.id.ll_multi:
                MultiSettingActivity.start(getContext());
                break;
            case R.id.ll_logout:

                break;
        }
    }

    public void getCacheSize() {
        String size = CacheUtils.getTotalCacheSize();
        tvCache.setText(TextUtils.isEmpty(size)?"":size);
    }
}
