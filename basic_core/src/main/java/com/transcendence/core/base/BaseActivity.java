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
import com.transcendence.core.base.action.ClickAction;
import com.transcendence.core.base.action.KeyboardAction;
import com.transcendence.core.statusbar.StatusBarUtil;

/**
 * @Author Joephone on 2021/12/1 0001 下午 5:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class BaseActivity extends AppCompatActivity implements KeyboardAction
    , ActivityAction, ClickAction {

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setStatusBarMode(this, false, R.color.colorGitHubBlack);
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


    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    @Override
    public Context getContext() {
        return this;
    }

    //记录用户首次点击返回键的时间
    private long mExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (System.currentTimeMillis() - mExitTime > 2000) {
                    Toast.makeText(mActivity,"再按一次返回键退出程序",Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
