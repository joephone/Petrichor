package com.transcendence.ui.newguide.lifecycle;


import androidx.fragment.app.Fragment;

import com.transcendence.ui.newguide.util.LogUtil;

public class ListenerFragment extends Fragment {

    FragmentLifecycle mFragmentLifecycle;

    public void setFragmentLifecycle(FragmentLifecycle lifecycle) {
        mFragmentLifecycle = lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("onStart: ");
        mFragmentLifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFragmentLifecycle.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentLifecycle.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy: ");
        mFragmentLifecycle.onDestroy();
    }
}
