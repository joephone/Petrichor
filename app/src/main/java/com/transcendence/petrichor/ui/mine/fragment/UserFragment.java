package com.transcendence.petrichor.ui.mine.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;

/**
 * @Author Joephone on 2021/12/21 0021 下午 5:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class UserFragment extends PetrichorBaseFragment<MainActivity> {

    LinearLayout title01,title02;

    public static UserFragment newInstance(String title) {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_user;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
