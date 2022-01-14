package com.transcendence.petrichor.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.language.MultiLanguages;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.SPUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.dialog.BaseDialog;
import com.transcendence.petrichor.dialog.DialogMessage;
import com.transcendence.petrichor.dialog.UpdateDialog;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.ui.main.activity.SplashActivity;
import com.transcendence.petrichor.ui.setting.adapter.LanguageSetAdapter;
import com.transcendence.petrichor.ui.setting.bean.LanguageBean;
import com.transcendence.petrichor.ui.setting.eventbus.LanguageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends PetrichorBaseActivity implements LanguageSetAdapter.LanguageSetAdapterOnClick{

    private RecyclerView mRv;
    private TextView tvLanguage;

    private LanguageSetAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting_language;
    }


    @Override
    protected void initView() {
        setTitle(getString(R.string.setting_language));
        setBackVisibility();
        mRv = findViewById(R.id.rv);
        tvLanguage = findViewById(R.id.tv_language);
    }

    @Override
    protected void initData() {
        mAdapter = new LanguageSetAdapter(getContext(),getData());
        mAdapter.setListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);
    }



    private List<LanguageBean> getData() {
        List<LanguageBean> list = new ArrayList<>();
        list.add(new LanguageBean(Locale.getDefault(), getString(R.string.lan_system)));
        list.add(new LanguageBean(Locale.SIMPLIFIED_CHINESE, getString(R.string.lan_chinese)));
        list.add(new LanguageBean(Locale.US, getString(R.string.lan_en)));
        return list;
    }

    @Override
    public void onLanItemClick(LanguageBean bean,int position,boolean isClick) {
        new DialogMessage.Builder(mActivity)
                .setTitle(R.string.tip)
                // 内容必须要填写
                .setMessage(R.string.lan_change_confirm)
                .setConfirm(getString(R.string.ok))
                // 设置 null 表示不显示取消按钮
                .setCancel(getString(R.string.cancel))
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new DialogMessage.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        mAdapter.setSelectPos(position,isClick);   //Global.EVENT_BUS.LANGUAGE_CONFIG_CHANGE
                        SPUtils.getInstance().put(Global.SP_KEY.LOCALE_LANGUAGE,bean.getLocale().getLanguage());
//                        EventBus.getDefault().post(new LanguageEvent(bean.getLocale()));

                        // 是否需要重启
                        boolean restart = false;

                        if (bean.getLocale().getLanguage().equals(getString(R.string.lan_system))) {
                            // 跟随系统
                            restart = MultiLanguages.clearAppLanguage(mActivity);
                        } else if (bean.getLocale().getLanguage().equals(Locale.CHINA.getLanguage())) {
                            // 简体中文
                            restart = MultiLanguages.setAppLanguage(mActivity, Locale.CHINA);
                        } else if (bean.getLocale().getLanguage().equals(Locale.TAIWAN.getLanguage())) {
                            // 繁体中文
                            restart = MultiLanguages.setAppLanguage(mActivity, Locale.TAIWAN);
                        } else if (bean.getLocale().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                            // 英语
                            restart = MultiLanguages.setAppLanguage(mActivity, Locale.ENGLISH);
                        }

                        if (restart) {
                            // 1.使用recreate来重启Activity的效果差，有闪屏的缺陷
                            // recreate();

                            // 2.使用常规startActivity来重启Activity，有从左向右的切换动画，稍微比recreate的效果好一点，但是这种并不是最佳的效果
                            // startActivity(new Intent(this, LanguageActivity.class));
                            // finish();

                            // 3.我们可以充分运用 Activity 跳转动画，在跳转的时候设置一个渐变的效果，相比前面的两种带来的体验是最佳的
                            startActivity(new Intent(mActivity, LanguageActivity.class));
                            overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
//                            LibApplication.finishAllActivity();
                            finish();
                        }
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
