package com.transcendence.petrichor.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.global.Global;
import com.transcendence.core.utils.SPUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.dialog.BaseDialog;
import com.transcendence.petrichor.dialog.DialogMessage;
import com.transcendence.petrichor.dialog.UpdateDialog;
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

    public static void start(Context context) {
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }

    private List<LanguageBean> getData() {
        List<LanguageBean> list = new ArrayList<>();
        list.add(new LanguageBean(Locale.SIMPLIFIED_CHINESE, getString(R.string.lan_chinese)));
        list.add(new LanguageBean(Locale.US, getString(R.string.lan_en)));
        return list;
    }

    @Override
    public void onLanItemClick(LanguageBean bean,int position,boolean isClick) {
        new DialogMessage.Builder(mActivity)
                .setTitle(R.string.tip)
                // 内容必须要填写
                .setMessage("确定更改吗？")
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
                        EventBus.getDefault().post(new LanguageEvent(bean.getLocale()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();



    }

}
