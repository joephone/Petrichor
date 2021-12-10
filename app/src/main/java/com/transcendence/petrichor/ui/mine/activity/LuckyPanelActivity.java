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

    private MediaPlayer mPlayer2;
    private SoundPool mSound2;
    private HashMap<Integer, Integer> soundPoolMap2;

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
//                    L.logE("delay----"+delay);
//        L.logE("duration----"+duration);

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
        // 设置播放音效
        mPlayer2 = MediaPlayer.create(this, R.raw.nine_grid_lottery);
        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        mSound2 = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap2 = new HashMap<>();
        soundPoolMap2.put(1, mSound2.load(this, R.raw.nine_grid_lottery, 1));
        //可以在后面继续put音效文件
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
        AudioManager mgr = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取系统声音的当前音量
        float currentVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统声音的最大音量
        float maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量的百分比
        float volume = currentVolume / maxVolume;

        // 第一个参数是声效ID,第二个是左声道音量，第三个是右声道音量，第四个是流的优先级，最低为0，第五个是是否循环播放，第六个播放速度(1.0 =正常播放,范围0.5 - 2.0)
        mSound2.play(soundPoolMap2.get(sound), volume, volume, 1, loop, 1f);
    }
}
