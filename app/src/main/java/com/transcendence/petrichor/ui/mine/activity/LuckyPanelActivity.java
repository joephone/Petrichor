package com.transcendence.petrichor.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.ui.widget.luckpanel.LuckyPanelView;

import java.util.HashMap;
import java.util.Random;

/**
 * @Author Joephone on 2021/12/10 0010 下午 4:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class LuckyPanelActivity extends PetrichorBaseActivity {

    private LuckyPanelView lucky_panel;
    private Button btn_action;

    private SoundPool mSound2;
    private HashMap<Integer, Integer> soundPoolMap2;

    private SoundPool mSoundPool = null;
    private HashMap<Integer, Integer> soundID = new HashMap<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, LuckyPanelActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lucky_panel;
    }

    @Override
    protected void initView() {
        setBackVisibility();
        lucky_panel = (LuckyPanelView) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stayIndex = new Random().nextInt(8);
                if (!lucky_panel.isGameRunning()) {
                    lucky_panel.startGame();

                    long delay = 2000;

                    playSound2(1,0);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (LuckyPanelActivity.this.isFinishing()) {
                                return;
                            }

//                            int pos = MARK_LUCKY-1;
                            lucky_panel.tryToStop(stayIndex);
                        }
                    }, delay);

                }
//                else {
//                    int stayIndex = new Random().nextInt(8);
//                    Log.e("LuckyMonkeyPanelView", "====stay===" + stayIndex);
//                    lucky_panel.tryToStop(stayIndex);
//                }
            }
        });
    }

    @Override
    protected void initData() {
        initSounds2();
    }



    /**
     * 初始化声音
     */
    private void initSounds2() {


        mSoundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 100);
        soundID.put(1, mSoundPool.load(this, R.raw.duang, 1));
    }

    /**
     * soundPool播放
     *
     * @param sound
     *            播放第一个
     * @param loop
     *            是否循环
     */
    private void playSound2(int sound, int loop) {
        mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);
    }
}
