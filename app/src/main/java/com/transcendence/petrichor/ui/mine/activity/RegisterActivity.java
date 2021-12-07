package com.transcendence.petrichor.ui.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.base.manager.InputTextManager;
import com.transcendence.ui.widget.view.CountdownView;
import com.transcendence.ui.widget.view.SubmitButton;

/**
 * @Author Joephone on 2021/12/6 0006 下午 2:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class RegisterActivity extends PetrichorBaseActivity
        implements TextView.OnEditorActionListener{



    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
//        if (context instanceof Activity) {
//            Activity activity = (Activity) context;
//            activity.overridePendingTransition(R.anim.swipeback_activity_open_bottom_in,
//                    R.anim.swipeback_activity_open_top_out);
//        }
    }


    private EditText mPhoneView;
    private CountdownView mCountdownView;

    private EditText mCodeView;

    private EditText mFirstPassword;
    private EditText mSecondPassword;

    private SubmitButton mCommitView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mPhoneView = findViewById(R.id.et_register_phone);
        mCountdownView = findViewById(R.id.cv_register_countdown);
        mCodeView = findViewById(R.id.et_register_code);
        mFirstPassword = findViewById(R.id.et_register_password1);
        mSecondPassword = findViewById(R.id.et_register_password2);
        mCommitView = findViewById(R.id.btn_register_commit);

        setOnClickListener(mCountdownView, mCommitView);

        mSecondPassword.setOnEditorActionListener(this);

//        // 给这个 View 设置沉浸式，避免状态栏遮挡
//        ImmersionBar.setTitleBar(this, findViewById(R.id.tv_register_title));
//
//        InputTextManager.with(this)
//                .addView(mPhoneView)
//                .addView(mCodeView)
//                .addView(mFirstPassword)
//                .addView(mSecondPassword)
//                .setMain(mCommitView)
//                .build();
    }

    @Override
    protected void initData() {

    }

    /**
     * {@link TextView.OnEditorActionListener}
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE && mCommitView.isEnabled()) {
            // 模拟点击注册按钮
            onClick(mCommitView);
            return true;
        }
        return false;
    }

}
