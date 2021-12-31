package com.transcendence.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.global.Global;

import java.util.Locale;

/**
 * Created by liuf on 16/3/16.
 */
public class LanguageUtils {
    static LanguageUtils instance;
    static Context context;
    /*
	 * 单例模式中获取唯一的CursorUtility实例
	 */
    public static LanguageUtils getInstance() {
        if (null == instance) {
            context= LibApplication.getAppContext();
            instance = new LanguageUtils();
        }
        return instance;
    }

    /**
     * 判断是否是日语
     *
     * @return
     */
    public  boolean isJaSetting() {
        String language = getConfigLanguage();
        if (language != null && (language.equals(Language.JA)))
            return true;
        else
            return false;
    }

    /**
     * 判断是否是英语
     *
     * @return
     */
    public  boolean isEnSetting() {
        String language = getConfigLanguage();
        if (language != null && (language.equals(Language.EN)))
            return true;
        else
            return false;
    }

    /**
     * 判断是否是中文
     *
     *
     * @return
     */
    public  boolean isChina() {
        String language = getConfigLanguage();
        if (language != null && (language.equals(Language.ZH_HANT)
        ||language.equals(Language.ZH_HANS)))
            return true;
        else
            return false;
    }
    /**
     * 判断是否是中文
     *
     *
     * @return
     */
    public  boolean isZhHans() {
        String language = getConfigLanguage();
        if (language != null && language.equals(Language.ZH_HANS))
            return true;
        else
            return false;
    }

    public String getCounty(){
        Resources resource = context.getResources();
        Configuration config = resource.getConfiguration();
        Locale locale=  config.locale;
        return locale.getCountry();
    }
    /***
     * 得到app语言
     */
    public String getConfigLanguage(){
//       String str= CatchesPreferences.getLanguageApp();
       String str = SPUtils.getInstance().getString(Global.SP_KEY.LOCALE_LANGUAGE);
        if (!TextUtils.isEmpty(str)){
            if (str.equals(Language.EN)||str.equals(Language.JA)
                    ||str.equals(Language.ZH_HANT)||str.equals(Language.ZH_HANS)){
                return str;
            }
        }
        String ret= Language.EN;
        Resources resource = context.getResources();
        Configuration config = resource.getConfiguration();
        Locale locale=  config.locale;
        L.d("config.locale:");
        if (locale.getLanguage().equals("zh")){
            if (locale.getCountry().equals("CN")){
                ret= Language.ZH_HANS;
            }else {
                ret= Language.ZH_HANT;
            }
        }else if(locale.getLanguage().equals("ja")){
            ret= Language.JA;
        }
       return ret;
    }

    /***
     * 得到app语言
     */
    public String getWebViewLanguage(){
        String str=getConfigLanguage();
        String retStr="en";
         switch (str){
             case Language.ZH_HANS:
                 retStr="cn";
                 break;
             case Language.ZH_HANT:
                 retStr="zh_hant";
                 break;
             case Language.JA:
                 retStr="ja";
                 break;
             default:

         }

        return retStr;
    }

//    public void setCountryName(TextView textView, CountryCode countryCode){
//
//        String language = LanguageUtil.getInstance().getConfigLanguage();
//        if(language.equals(Language.EN)){
//            textView.setText(countryCode.getEn());
//        }else if(language.equals(Language.ZH_HANS)){
//            textView.setText(countryCode.getZh_CN());
//        }else if(language.equals(Language.ZH_HANT)){
//            textView.setText(countryCode.getZh_TW());
//        }else if(language.equals(Language.JA)){
//            textView.setText(countryCode.getJa());
//        }
//    }
//    public String getDBLanguage(){
//        String language=LanguageUtil.getInstance().getConfigLanguage();
//        if (language.equals(LanguageUtil.Language.ZH_HANS)){
//            language="zh_Hans";
//        }else if(language.equals(LanguageUtil.Language.ZH_HANT)){
//            language="zh_Hant";
//        }else {
//            language= Language.EN;
//        }
//        return language;
//    }
    /***
     * 设置app语言
     * @param str
     */
    public void setConfigLanguage(Context context, String str, boolean isRest){
        Resources resource = context.getResources();
        Configuration config = resource.getConfiguration();
        SPUtils.getInstance().put(Global.SP_KEY.LOCALE_LANGUAGE,str);
        switch (str) {
            case Language.EN:
                config.locale = Locale.ENGLISH;
//                CatchesPreferences.setLanguageApp(str);
                break;
            case Language.JA:
                config.locale = Locale.JAPANESE;
//                CatchesPreferences.setLanguageApp(str);
                break;
            case Language.ZH_HANT:
                config.locale = Locale.TAIWAN;
//                CatchesPreferences.setLanguageApp(str);
                break;
            case Language.ZH_HANS:
                config.locale = Locale.CHINA;
//                CatchesPreferences.setLanguageApp(str);
                break;
            default:
//                CatchesPreferences.setLanguageApp(Language.EN);
                SPUtils.getInstance().put(Global.SP_KEY.LOCALE_LANGUAGE,Language.EN);
                config.locale = Locale.getDefault();
                break;

        }
        context.getResources().updateConfiguration(config, null);
        if (isRest){
            //TODO 添加重启应用
            Intent mIntent = new Intent(Global.BROADCAST_KEY.EXIT_APP);
            context.sendBroadcast(mIntent);
            Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(context.getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    /**
     *
     * @Description: 是否是中文或者日文 @param @return @return boolean @throws
     */
    public  boolean isChineseCalendar() {
        String language = Locale.getDefault().getLanguage();
        if (language.endsWith("zh") || language.endsWith("ja"))
            return true;
        else
            return false;
    }


    public interface Language {
        String EN="en";
        String JA="ja";
        String ZH_HANT="zh_Hant";//繁体中文
        String ZH_HANS="zh_Hans";//简体中文
    }
}
