package com.transcendence.petrichor.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.hjq.language.MultiLanguages;
import com.hjq.language.OnLanguageListener;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.utils.L;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.manager.ActivityManager;
import com.transcendence.petrichor.crash.CrashHandler;

import org.xutils.x;

import java.util.Locale;

/**
 * @Author Joephone on 2021/12/1 0001 下午 4:26
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public final class PetrichorApp extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();


        init(this);
    }

    /**
     * 初始化一些第三方框架
     */
    public static void init(final Application application) {
        // 初始化吐司
        ToastUtils.init(application, new ToastBlackStyle(application) {

            @Override
            public int getCornerRadius() {
                return (int) application.getResources().getDimension(R.dimen.button_round_size);
            }
        });

        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor());

        // 注册网络状态变化监听
        ConnectivityManager connectivityManager = ContextCompat.getSystemService(application, ConnectivityManager.class);
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(@NonNull Network network) {
                    Activity topActivity = ActivityManager.getInstance().getTopActivity();
                    if (topActivity instanceof LifecycleOwner) {
                        LifecycleOwner lifecycleOwner = ((LifecycleOwner) topActivity);
                        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                            ToastUtils.show(R.string.common_network_error);
                        }
                    }
                }
            });
        }

        CrashHandler.register(application);

        // 初始化多语种框架 轮子哥
        MultiLanguages.init(application);
        // 设置语种变化监听器 轮子哥
        MultiLanguages.setOnLanguageListener(new OnLanguageListener() {

            @Override
            public void onAppLocaleChange(Locale oldLocale, Locale newLocale) {
                L.d( "监听到应用切换了语种，旧语种：" + oldLocale + "，新语种：" + newLocale);
            }

            @Override
            public void onSystemLocaleChange(Locale oldLocale, Locale newLocale) {
                L.d("监听到系统切换了语种，旧语种：" + oldLocale + "，新语种：" + newLocale + "，是否跟随系统：" + MultiLanguages.isSystemLanguage());
            }
        });

        //xutils初始化
        x.Ext.init(application);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 绑定语种  轮子哥
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }
}
