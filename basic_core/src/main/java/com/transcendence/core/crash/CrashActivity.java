//package com.transcendence.core.crash;
//
//import android.content.Intent;
//import android.os.CountDownTimer;
//import android.view.KeyEvent;
//import android.widget.TextView;
//
//import com.transcendence.core.R;
//import com.transcendence.core.base.BaseActivity;
//import com.transcendence.core.base.component.ActivityCollector;
//import com.transcendence.core.global.Global;
//
//import java.util.Locale;
//
//public class CrashActivity extends BaseActivity {
//
//    private TextView prompt , crashMessage;
//    private String exceptionOfCrash;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_crash_back;
//    }
//
//    @Override
//    protected void initView() {
//        initIntent();
//        initViews();
//        countDownTimer.start();
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    private void initIntent() {
//        Intent intent = getIntent();
//        if (intent == null)return;
//        exceptionOfCrash = intent.getStringExtra(Global.INTENT_KEY.EXCEPTION_CRASH);
//    }
//
//    private void initViews() {
//        prompt = findViewById(R.id.prompt);
//        crashMessage = findViewById(R.id.crashMessage);
//        crashMessage.setText(exceptionOfCrash);
//    }
//
//    private CountDownTimer countDownTimer = new CountDownTimer(5000 , 1000) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//            prompt.setText(String.format(Locale.getDefault() , "Warning!\n  Application will be restart in %1s second" , millisUntilFinished/1000));
//        }
//
//        @Override
//        public void onFinish() {
////            ActivityCollector.finishAll();
////            Process.killProcess(Process.myPid());
////            System.exit(1);
//            ActivityCollector.exitApp();
////            restart(mActivity);
//        }
//    };
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
////    public static void restart(Context context) {
////        Intent intent;
////        if (true) {
////            // 如果是未登录的情况下跳转到闪屏页
////            intent = new Intent(context, SplashActivity.class);
////        } else {
////            // 如果是已登录的情况下跳转到首页
////            intent = new Intent(context, MainActivity.class);
////        }
////
////        if (!(context instanceof Activity)) {
////            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        }
////        context.startActivity(intent);
////    }
//}
