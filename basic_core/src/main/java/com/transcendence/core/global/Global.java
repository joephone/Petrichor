package com.transcendence.core.global;

/**
 * @Author Joephone on 2021/12/2 0002 上午 10:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class Global {
    public static final String TAG = "Petrichor";
    public static boolean DEBUG = false;
    public final static int ANIM_DURATION_TIME = 10000;


    public static final class Bugly {
        public static final String APP_ID = "79ac952bd8";
        public static final String APP_KEY = "fdc5280a-0806-4763-9469-ed650273132f";
    }

    public final class MAP {
        //比例尺 100
        public static final  int SMALL_ZOOM =18;
        public static final  int MID_ZOOM =13;
        public static final  int BIG_ZOOM =15;
        public static final  String DEFAULT_LAT ="defaultLan";
        public static final  String DEFAULT_LON ="defaultLon";
    }

    public static int standardZoom(){
        return MAP.SMALL_ZOOM;
    }
}
