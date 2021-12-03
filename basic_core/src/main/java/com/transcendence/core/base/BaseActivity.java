package com.transcendence.core.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.R;
import com.transcendence.core.base.action.KeyboardAction;
import com.transcendence.core.statusbar.StatusBarUtil;

/**
 * @Author Joephone on 2021/12/1 0001 下午 5:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class BaseActivity extends AppCompatActivity implements KeyboardAction {

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);
        mActivity = this;
        init();
    }

    protected void init() {
        initLayout();
        initView();
        initData();
    }


    /**
     * 初始化布局
     */
    protected void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
    }

    /**
     * 获取布局 ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    protected void start(Class<?> target) {
        Intent intent = new Intent(mActivity, target);
        startActivity(intent);
        overridePendingTransition(R.anim.wan_zoom_small_in, R.anim.wan_zoom_small_out);
    }



}
