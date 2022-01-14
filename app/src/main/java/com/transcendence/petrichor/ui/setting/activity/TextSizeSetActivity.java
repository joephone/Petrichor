package com.transcendence.petrichor.ui.setting.activity;

import android.content.Intent;
import android.widget.SeekBar;
import android.widget.TextView;

import com.trancesdence.utils.DensityUtils;
import com.transcendence.core.utils.L;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

/**
 * @Author Joephone on 2022/1/13 0013 下午 5:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class TextSizeSetActivity extends PetrichorBaseActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar mSeekBar;
    TextView mNormalText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_text_size_set;
    }

    @Override
    protected void initView() {
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mNormalText = findViewById(R.id.normalText);
    }

    @Override
    protected void initData() {
        setTitle(getString(R.string.Settings_General_FontSize));
        setBackVisibility();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int seekProgress = mSeekBar.getProgress();
        if (seekProgress < 10) {
            mSeekBar.setProgress(0);
        } else if (seekProgress >= 10 && seekProgress < 30) {
            mSeekBar.setProgress(20);
        } else if (seekProgress >= 30 && seekProgress < 50) {
            mSeekBar.setProgress(40);
        } else if (seekProgress >= 50 && seekProgress < 70) {
            mSeekBar.setProgress(60);
        } else if (seekProgress >= 60 && seekProgress < 90) {
            mSeekBar.setProgress(80);
        } else if (seekProgress >= 90) {
            mSeekBar.setProgress(100);
        }
        setText();
    }


    private void setText() {
        int seekProgress = mSeekBar.getProgress();
        float ratio = 1.0f;
        if (seekProgress < 10) {
            ratio = 0.9f;
        } else if (seekProgress >= 10 && seekProgress < 30) {
            ratio = 1.0f;
        } else if (seekProgress >= 30 && seekProgress < 50) {
            ratio = 1.1f;
        } else if (seekProgress >= 50 && seekProgress < 70) {
            ratio = 1.2f;
        } else if (seekProgress >= 60 && seekProgress < 90) {
            ratio = 1.3f;
        } else if (seekProgress >= 90) {
            ratio = 1.4f;
        }
        float btn_w = mNormalText.getTextSize();
        L.d("btn_w:" + btn_w);
        float textSize = btn_w * ratio * 1 / DensityUtils.dip2px(getApplication(), 1);

        L.d("textSize:" + textSize);
//        tvChangeForRead2.setTextSize(textSize);
//        tvChangeForRead.setTextSize(textSize);
//        SharedPreferenceUtil.getInstance().setTextSize(ratio);
//        Intent intent = new Intent(BroadcastKey.CHANGE_TEXT_SIZE);
//        sendBroadcast(intent);
    }
}
