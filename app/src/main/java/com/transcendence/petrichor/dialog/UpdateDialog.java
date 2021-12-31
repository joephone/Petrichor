package com.transcendence.petrichor.dialog;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;


import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.updater.AppUpdater;
import com.transcendence.core.updater.UpdateConfig;
import com.transcendence.core.updater.callback.UpdateCallback;
import com.transcendence.core.updater.http.OkHttpManager;
import com.transcendence.petrichor.R;
import com.transcendence.ui.utils.L;

import java.io.File;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/03/20
 *    desc   : 升级对话框
 */
public final class UpdateDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        private final TextView mNameView;
        private final TextView mContentView;
        private final ProgressBar mProgressView;

        private final TextView mUpdateView;
        private final TextView mCloseView;

        /** Apk 文件 */
        private File mApkFile;
        /** 下载地址 */
        private String mDownloadUrl;
        /** 文件 MD5 */
        private String mFileMd5;
        /** 是否强制更新 */
        private boolean mForceUpdate;

        /** 当前是否下载中 */
        private boolean mDownloading;
        /** 当前是否下载完毕 */
        private boolean mDownloadComplete;

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.dialog_update);
            setAnimStyle(BaseDialog.ANIM_BOTTOM);
            setCancelable(false);

            mNameView = findViewById(R.id.tv_update_name);
            mContentView = findViewById(R.id.tv_update_content);
            mProgressView = findViewById(R.id.pb_update_progress);
            mUpdateView = findViewById(R.id.tv_update_update);
            mCloseView = findViewById(R.id.tv_update_close);
            setOnClickListener(mUpdateView, mCloseView);
        }

        /**
         * 设置版本名
         */
        public Builder setVersionName(CharSequence name) {
            mNameView.setText(name);
            return this;
        }

        /**
         * 设置更新日志
         */
        public Builder setUpdateLog(CharSequence text) {
            mContentView.setText(text);
            mContentView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }

        /**
         * 设置强制更新
         */
        public Builder setForceUpdate(boolean force) {
            mForceUpdate = force;
            mCloseView.setVisibility(force ? View.GONE : View.VISIBLE);
            setCancelable(!force);
            return this;
        }

        /**
         * 设置下载 url
         */
        public Builder setDownloadUrl(String url) {
            mDownloadUrl = url;
            return this;
        }

        /**
         * 设置文件 md5
         */
        public Builder setFileMd5(String md5) {
            mFileMd5 = md5;
            return this;
        }

  //      @SingleClick
        @Override
        public void onClick(View view) {
            if (view == mCloseView) {
                dismiss();
            } else if (view == mUpdateView) {
                L.d("mUpdateView");
                // 判断下载状态
                if (mDownloadComplete) {
                    if (mApkFile.isFile()) {
                        // 下载完毕，安装 Apk
                        Message msg = new Message();
                        msg.what = INSTALL_APK;
                        handler.sendMessageDelayed(msg,0);
                    } else {
                        // 下载失败，重新下载
                        downloadApk();
                    }
                } else if (!mDownloading) {
                    L.d("!mDownloading");
                    // 没有下载，开启下载
                    downloadApk();
                }
            }
        }

        /**
         * 下载 Apk
         */
 //       @CheckNet
 //       @Permissions({Permission.MANAGE_EXTERNAL_STORAGE})
        private void downloadApk() {
            // 设置对话框不能被取消
            setCancelable(false);

//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            int notificationId = getContext().getApplicationInfo().uid;
//            String channelId = "";
//            // 适配 Android 8.0 通知渠道新特性
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel = new NotificationChannel("update_notification_channel_id","update_notification_channel_name", NotificationManager.IMPORTANCE_LOW);
//                channel.enableLights(false);
//                channel.enableVibration(false);
//                channel.setVibrationPattern(new long[]{0});
//                channel.setSound(null, null);
//                notificationManager.createNotificationChannel(channel);
//                channelId = channel.getId();
//            }
//
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), channelId)
//                    // 设置通知时间
//                    .setWhen(System.currentTimeMillis())
//                    // 设置通知标题
//                    .setContentTitle(getString(R.string.app_name))
//                    // 设置通知小图标
//                    .setSmallIcon(R.mipmap.ic_petrichor)
//                    // 设置通知大图标
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_petrichor))
//                    // 设置通知静音
//                    .setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE)
//                    // 设置震动频率
//                    .setVibrate(new long[]{0})
//                    // 设置声音文件
//                    .setSound(null)
//                    // 设置通知的优先级
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // 创建要下载的文件对象
            mApkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    getString(R.string.app_name) + "_v" + mNameView.getText().toString() + ".apk");


            UpdateConfig config = new UpdateConfig();
            config.setUrl(mDownloadUrl);
            AppUpdater mAppUpdater = new AppUpdater(LibApplication.getAppContext(),config)
                    .setHttpManager(OkHttpManager.getInstance())
                    .setUpdateCallback(new UpdateCallback() {

                        @Override
                        public void onDownloading(boolean isDownloading) {
                            if(isDownloading){
                                L.d("已经在下载中,请勿重复下载。");
                            }
                        }

                        @Override
                        public void onStart(String url) {
                            L.d("Progress onStart");
                            mProgressView.setProgress(0);
                            Message msg = new Message();
                            msg.what = PRO_VISIBLE;
                            handler.sendMessage(msg);

                        }

                        @Override
                        public void onProgress(long progress, long total, boolean isChange) {
                            L.d("onProgress");
                            if(isChange){
                                int currProgress = Math.round(progress * 1.0f / total * 100.0f);
                                mProgressView.setProgress(currProgress);
                            }
                        }

                        @Override
                        public void onFinish(File file) {
                            L.d("onFinish");
                            Message msg = new Message();
                            msg.what = PRO_FINISH;
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onError(Exception e) {
                            L.d("onError");
                            Message msg = new Message();
                            msg.what = PRO_INVISIBLE;
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onCancel() {
                            L.d("onCancel");
                            Message msg = new Message();
                            msg.what = PRO_INVISIBLE;
                            handler.sendMessage(msg);
                        }
                    });
            mAppUpdater.start();

        }

        /**
         * 安装 Apk
         */
//        @Permissions({Permission.REQUEST_INSTALL_PACKAGES})
        private void installApk() {
            getContext().startActivity(getInstallIntent());
        }

        /**
         * 获取安装意图
         */
        private Intent getInstallIntent() {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(getContext(), LibApplication.getAppContext().getPackageName() + ".provider", mApkFile);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(mApkFile);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        }

        private final int PRO_VISIBLE = 1000;
        private final int PRO_INVISIBLE = 2000;
        private final int PRO_FINISH = 3000;
        private final int INSTALL_APK = 4000;

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case PRO_VISIBLE:
                        mProgressView.setVisibility(View.VISIBLE);
                        break;
                    case PRO_INVISIBLE:
                        mProgressView.setVisibility(View.INVISIBLE);
                        break;
                    case PRO_FINISH:
                        mProgressView.setVisibility(View.INVISIBLE);
                        dismiss();
                        break;
                    case INSTALL_APK:
                        dismiss();
                        installApk();
                        break;
                }
            }
        };
    }



}