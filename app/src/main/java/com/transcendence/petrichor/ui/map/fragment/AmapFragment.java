package com.transcendence.petrichor.ui.map.fragment;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.ui.main.activity.MainActivity;

/**
 * @Author Joephone on 2021/12/8 0008 上午 10:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class AmapFragment extends PetrichorBaseFragment<MainActivity> {

    private static AmapFragment instance;

    public static AmapFragment getInstance() {
        if (instance == null) {
            synchronized (AmapFragment.class) {
                if (instance == null) {
                    instance = new AmapFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_amap;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
