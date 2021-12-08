package com.transcendence.petrichor.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;

/**
 * @Author Joephone on 2021/12/2 0002 下午 5:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class HomeFragment extends PetrichorBaseFragment<MainActivity> {

    private FrameLayout fl_back;

    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, title);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_home;
    }

    @Override
    protected void initView() {
        fl_back = (FrameLayout) findViewById(R.id.fl_back);
        fl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if(activity instanceof MainActivity){
                    MainActivity mainActivity = (MainActivity) activity;
                    mainActivity.slideToMap();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
