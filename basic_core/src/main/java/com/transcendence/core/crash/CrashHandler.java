//package com.transcendence.core.crash;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Looper;
//import android.os.Process;
//
//import com.transcendence.core.base.component.ActivityCollector;
//import com.transcendence.core.global.Global;
//
//public class CrashHandler implements Thread.UncaughtExceptionHandler {
//
//    private static volatile CrashHandler crashHandler;
//
//    private Context context;
//
//    private CrashHandler(){}
//
//    public void init(Context context){
//        this.context = context;
//        Thread.setDefaultUncaughtExceptionHandler(this);
//    }
//
//    public static CrashHandler getCrashHandler(){
//        if (crashHandler == null){
//            synchronized (CrashHandler.class){
//                if (crashHandler == null){
//                    crashHandler = new CrashHandler();
//                }
//            }
//        }
//        return crashHandler;
//    }
//
//    @Override
//    public void uncaughtException(Thread t, final Throwable e) {
//        // 提示信息
//        new Thread() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Intent intent = new Intent(context , CrashActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(Global.INTENT_KEY.EXCEPTION_CRASH , e.toString());
//                context.startActivity(intent);
//                Looper.loop();
//            }
//        }.start();
//        ActivityCollector.finishAll();
//        Process.killProcess(Process.myPid());
//        System.exit(1);
//    }
//}
