package com.transcendence.ui.utils;import android.util.Log;/** * Created by Administrator on 2017/9/7. * 如果打包  就将isTest设置为false */public class L {    private static String UI_TAG ="";    private static boolean UI_DEBUG = true;    public static void logI(String content) {        Log.i(UI_TAG, content);    }    public static void logE(String content) {        Log.e(UI_TAG, content);    }    /**     * 调试通用日志     * @param content     */    public static void d(String content) {//        if(Global.DEBUG){            Log.d(UI_TAG, content);//        }    }    public static void w(String content) {        if(UI_DEBUG){            Log.w(UI_TAG, content);        }    }    public static void w(Throwable content) {        if(UI_DEBUG){            Log.w(UI_TAG, content);        }    }    public static void e(String content) {        if(UI_DEBUG){            Log.e(UI_TAG, content);        }    }}