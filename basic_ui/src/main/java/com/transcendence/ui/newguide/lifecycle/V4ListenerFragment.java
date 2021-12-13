//package com.transcendence.ui.newguide.lifecycle;
//
//import android.support.v4.app.Fragment;
//
//import com.dj.userguide.util.LogUtil;
//
//
///**
// * Created by hubert
// * <p>
// * Created on 2017/9/13.
// */
//
//public class V4ListenerFragment extends Fragment {
//
//    FragmentLifecycle mFragmentLifecycle;
//
//    public void setFragmentLifecycle(FragmentLifecycle lifecycle) {
//        mFragmentLifecycle = lifecycle;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        LogUtil.d("onStart: ");
//        mFragmentLifecycle.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mFragmentLifecycle.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        mFragmentLifecycle.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        LogUtil.d("onDestroy: ");
//        mFragmentLifecycle.onDestroy();
//    }
//}
