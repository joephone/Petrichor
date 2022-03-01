package com.transcendence.petrichor.pic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.dialog.BaseDialog;
import com.transcendence.petrichor.dialog.DialogMessage;

import java.util.List;

public class ImagePagerActivity extends PetrichorBaseActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private ImageViewPager mPager;
    private int pagerPosition;
    private TextView app_delete;
    private TextView app_title_back;
    ImagePagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail_pager);
        app_delete = (TextView) findViewById(R.id.app_delete);
        app_title_back = (TextView) findViewById(R.id.app_title_back);

        app_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent().getBooleanExtra("showDelete", false)) {
            app_delete.setVisibility(View.VISIBLE);
        }
        app_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogMessage.Builder(mActivity)
                        .setTitle(R.string.tip)
                        // 内容必须要填写
                        .setMessage("您确定要删除此图片吗？")
                        .setConfirm(getString(R.string.delete))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.cancel))
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new DialogMessage.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                Intent intent = getIntent();
                                try {
                                    intent.putExtra("path", mAdapter.fileList[mPager.getCurrentItem()]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                setResult(RESULT_OK, intent);
                                dialog.dismiss();
                                finish();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .show();


            }
        });
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        String[] urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);

        mPager = findViewById(R.id.pager);
        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        // 更新下标
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }



    @Override
    public void initData() {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public String[] fileList;

        public ImagePagerAdapter(FragmentManager fm, String[] fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.length;
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList[position];
            ImageDetailFragment frag = ImageDetailFragment.newInstance(url, position, fileList.length);
            return frag;
        }

    }
}