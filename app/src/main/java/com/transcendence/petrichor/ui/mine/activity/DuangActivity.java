package com.transcendence.petrichor.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.Button;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

import java.util.HashMap;

public class DuangActivity extends PetrichorBaseActivity implements View.OnClickListener {

    Button mBtnPlay1;
    Button mBtnPlay2;
    Button mBtnPlay3;
    Button mBtnPlay4;
    Button mBtnPlay5;
    Button mBtnRelease;

    private AssetManager aManager;
    private SoundPool mSoundPool = null;
    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();


    public static void start(Context context) {
        Intent intent = new Intent(context, DuangActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_duang;
    }

    @Override
    protected void initView() {
        setBackVisibility();
        mBtnPlay1 = findViewById(R.id.btn_play1);
        mBtnPlay2 = findViewById(R.id.btn_play2);
        mBtnPlay3 = findViewById(R.id.btn_play3);
        mBtnPlay4 = findViewById(R.id.btn_play4);
        mBtnPlay5 = findViewById(R.id.btn_play5);
        mBtnRelease = findViewById(R.id.btn_release);
        mBtnPlay1.setOnClickListener(this);
        mBtnPlay2.setOnClickListener(this);
        mBtnPlay3.setOnClickListener(this);
        mBtnPlay4.setOnClickListener(this);
        mBtnPlay5.setOnClickListener(this);
        mBtnRelease.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        aManager = getAssets();
        try {
            initSP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSP() throws Exception {
        //设置最多可容纳5个音频流，音频的品质为5
        mSoundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);
        soundID.put(1, mSoundPool.load(this, R.raw.duang, 1));
        soundID.put(2 , mSoundPool.load(getAssets().openFd("biaobiao.mp3") , 1));  //需要捕获IO异常
        soundID.put(3, mSoundPool.load(this, R.raw.duang, 1));
        soundID.put(4, mSoundPool.load(this, R.raw.duang, 1));
        soundID.put(5, mSoundPool.load(this, R.raw.duang, 1));

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play1:
                mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);
                break;
            case R.id.btn_play2:
                mSoundPool.play(soundID.get(2), 1, 1, 0, 0, 1);
                break;
            case R.id.btn_play3:
                mSoundPool.play(soundID.get(3), 1, 1, 0, 0, 1);
                break;
            case R.id.btn_play4:
                mSoundPool.play(soundID.get(4), 1, 1, 0, 0, 1);
                break;
            case R.id.btn_play5:
                mSoundPool.play(soundID.get(5), 1, 1, 0, 0, 1);
                break;
            case R.id.btn_release:
                mSoundPool.release();   //回收SoundPool资源
                break;
        }
    }
}
