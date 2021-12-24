package com.transcendence.core.base.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * 一键退出App
 *
 * @author quchao
 * @date 2017/11/28
 */

public class ActivityCollector {

    private static ActivityCollector activityCollector;

    public synchronized static ActivityCollector getInstance() {
        if (activityCollector == null) {
            activityCollector = new ActivityCollector();
        }
        return activityCollector;
    }

    private static Set<Activity> activityList;

    public static void addActivity(Activity act) {
        if (activityList == null) {
            activityList = new HashSet<>();
        }
        activityList.add(act);
    }

    public static void removeActivity(Activity act) {
        if (activityList != null) {
            activityList.remove(act);
        }
    }

    public static void finishAll(){
        for (Activity activity : activityList) {
            if (activityList.contains(activity)){
                activityList.remove(activity);
            }
            activity.finish();
        }
    }

    public static void exitApp() {
        if (activityList != null) {
            synchronized (activityList) {
                for (Activity act : activityList) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
