package com.transcendence.petrichor.ui.mine.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.transcendence.core.global.Global;
import com.transcendence.core.utils.SPUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.fragment.PetrichorBaseFragment;
import com.transcendence.petrichor.pic.activity.DragImageActivity;
import com.transcendence.petrichor.ui.main.activity.WebViewActivity;
import com.transcendence.petrichor.ui.mine.activity.AboutMeActivity;
import com.transcendence.petrichor.ui.mine.activity.LoginActivity;
import com.transcendence.petrichor.ui.main.activity.MainActivity;
import com.transcendence.petrichor.ui.mine.activity.LuckyPanelActivity;
import com.transcendence.petrichor.ui.setting.activity.SettingActivity;
import com.transcendence.ui.newguide.NewbieGuide;
import com.transcendence.ui.newguide.core.Controller;
import com.transcendence.ui.newguide.listener.OnLayoutInflatedListener;
import com.transcendence.ui.newguide.listener.OnPageChangedListener;
import com.transcendence.ui.newguide.model.GuidePage;
import com.transcendence.ui.newguide.model.HighLight;
import com.transcendence.ui.newguide.model.RelativeGuide;
import com.transcendence.ui.scroll.HeaderZoomLayout;
import com.transcendence.ui.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/12/3 0003 下午 4:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MineFragment extends PetrichorBaseFragment<MainActivity> implements View.OnClickListener, HeaderZoomLayout.OnScrollListener {

    private HeaderZoomLayout mScroll;
    boolean isFirstGo = SPUtils.getInstance().getBoolean(Global.SP_KEY.APP_FIRST_START, true);
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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
        mScroll = (HeaderZoomLayout) findViewById(R.id.scrollView);
        mScroll.setOnScrollListener(this);

        findViewById(R.id.ll_open_project).setOnClickListener(this);
        findViewById(R.id.ll_about_author).setOnClickListener(this);
        findViewById(R.id.ll_setting).setOnClickListener(this);
        findViewById(R.id.ll_top).setOnClickListener(this);
        findViewById(R.id.iv_avatar).setOnClickListener(this);
        findViewById(R.id.tv_sign_in).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isFirstGo) {
//            //相当于onResume initData();
//            NewbieGuide.with(getActivity())
//                    .setLabel("page")
//                    .setOnPageChangedListener(new OnPageChangedListener() {
//                        @Override
//                        public void onPageChanged(int page) {
//                            Toast.makeText(getActivity(), "当前page" + page, Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addGuidePage(GuidePage.newInstance()
//                                    .addHighLight(findViewById(R.id.fl_mine), HighLight.Shape.RECTANGLE,
//                                            new RelativeGuide(R.layout.newguide_relative, Gravity.TOP, 0))
//                                    .setLayoutRes(R.layout.newguide_simple)
//                                    .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
//                                        @Override
//                                        public void onLayoutInflated(View view, final Controller controller) {
//                                            Button button = view.findViewById(R.id.btn_next);
//                                            button.setText("下一步");
//                                            button.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    controller.showPage(1);
//                                                }
//                                            });
//                                        }
//                                    })
//                        /*.setEnterAnimation(enterAnimation)//进入动画
//                        .setExitAnimation(exitAnimation)//退出动画*/
//                    )
//                    .addGuidePage(GuidePage.newInstance()
//                            .addHighLight(findViewById(R.id.iv_avatar), HighLight.Shape.RECTANGLE,
//                                    new RelativeGuide(R.layout.newguide_relative, Gravity.RIGHT))
//                            .setLayoutRes(R.layout.newguide_simple)
//                            .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
//                                @Override
//                                public void onLayoutInflated(View view, final Controller controller) {
//                                    Button button = view.findViewById(R.id.btn_next);
//                                    button.setText("下一步");
//                                    button.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            controller.showPage(2);
//                                        }
//                                    });
//                                }
//                            })
//                    )
//                    .addGuidePage(GuidePage.newInstance()
//                            .addHighLight(findViewById(R.id.tv_name), HighLight.Shape.RECTANGLE,
//                                    new RelativeGuide(R.layout.newguide_relative, Gravity.LEFT))
//                            .setLayoutRes(R.layout.newguide_simple)
//                            .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
//                                @Override
//                                public void onLayoutInflated(View view, final Controller controller) {
//                                    Button button = view.findViewById(R.id.btn_next);
//                                    button.setText("下一步");
//                                    button.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            controller.showPage(3);
//                                        }
//                                    });
//                                }
//                            })
//                    )
//                    .addGuidePage(GuidePage.newInstance()
//                            .addHighLight(findViewById(R.id.tv_sign_in), HighLight.Shape.RECTANGLE,
//                                    new RelativeGuide(R.layout.newguide_relative, Gravity.LEFT))
//                    )
//                    .alwaysShow(true)
//                    .show();
//            SPUtils.getInstance().save(Global.SP_KEY.APP_FIRST_START, false);
//        } else {
//            //相当于onPause
//        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_open_project:
                WebViewActivity.start(getContext(), Global.GITHUB_AUTHOR_MAIN_PROJECT,"开源项目");
                break;
            case R.id.ll_about_author:
                AboutMeActivity.start(getActivity());
                break;
            case R.id.ll_setting:
                SettingActivity.start(getActivity());
                break;
            case R.id.ll_top:
//                LoginActivity.start(getActivity());
                break;
            case R.id.tv_sign_in:
                LuckyPanelActivity.start(getActivity());
                break;
            case R.id.iv_avatar:
                DragImageActivity.start(getActivity());
                break;
        }
    }

    private List list;
    @Override
    public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (Math.abs(scrollY - oldScrollY) > 50) {
            L.d(""+list.size());
        }
    }

}
