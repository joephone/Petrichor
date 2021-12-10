package com.transcendence.petrichor.ui.mine.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.mine.activity.LoginActivity;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.ui.mine.activity.LuckyPanelActivity;
import com.transcendence.petrichor.ui.setting.activity.SettingActivity;
import com.transcendence.ui.scroll.HeaderZoomLayout;

/**
 * @Author Joephone on 2021/12/3 0003 下午 4:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MineFragment extends PetrichorBaseFragment<MainActivity> implements View.OnClickListener, HeaderZoomLayout.OnScrollListener {

    private LinearLayout ll_setting,ll_top;
    private HeaderZoomLayout mScroll;
    private FrameLayout fl_mine;
    private AppCompatImageView mIvAvatar;
    private TextView mTvSign;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static MineFragment newInstance(String title) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_mine;
    }

    @Override
    protected void initView() {
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
        ll_setting.setOnClickListener(this);
        mScroll = (HeaderZoomLayout) findViewById(R.id.scrollView);
        mScroll.setOnScrollListener(this);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        ll_top.setOnClickListener(this);
        mIvAvatar = (AppCompatImageView) findViewById(R.id.iv_avatar);
        mIvAvatar.setOnClickListener(this);
        mTvSign = (TextView) findViewById(R.id.tv_sign_in);
        mTvSign.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting:
                SettingActivity.start(getActivity());
                break;
            case R.id.ll_top:
                LoginActivity.start(getActivity());
                break;
            case R.id.tv_sign_in:
                LuckyPanelActivity.start(getActivity());
                break;
        }
    }

    @Override
    public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (Math.abs(scrollY - oldScrollY) > 50) {

        }
    }

}
