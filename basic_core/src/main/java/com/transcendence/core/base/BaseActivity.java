package com.transcendence.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.R;
import com.transcendence.core.base.action.ActivityAction;
import com.transcendence.core.base.action.BundleAction;
import com.transcendence.core.base.action.ClickAction;
import com.transcendence.core.base.action.KeyboardAction;
import com.transcendence.core.base.component.ActivityCollector;
import com.transcendence.core.statusbar.StatusBarUtil;
import com.transcendence.core.utils.StatusBarUtils;

/**
 * @Author Joephone on 2021/12/1 0001 下午 5:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class BaseActivity extends AppCompatActivity implements KeyboardAction
    , ActivityAction, BundleAction,ClickAction {

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setStatusBarMode(this, false, R.color.colorGitHubBlack);
        ActivityCollector.addActivity(this);
        mActivity = this;
        StatusBarUtils.with(mActivity).init();
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

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }

    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }



    @Override
    public Context getContext() {
        return this;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
