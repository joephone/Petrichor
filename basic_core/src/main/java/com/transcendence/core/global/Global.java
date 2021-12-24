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
    public static boolean DEBUG = true;
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




    public final class SP_KEY {
        public static final String APP_FIRST_START = "appFirstStart";
        public static final String APP_BADGE = "appBadge";
        public static final String LOCALE_LANGUAGE = "locale_language";
        public static final String LOCALE_COUNTRY = "locale_country";
        /**
         * 记录定位信息
         */
        public static final String LOCATION_INFO = "locationInfo";

        /**
         * 记录搜索信息
         */
        public static final String SEARCH_INFO = "searchInfo";
    }

    public final class EVENT_BUS {
        public static final String LANGUAGE_CONFIG_CHANGE = "language_config_change";
    }



    public final class INTENT_KEY {
        // 常用相关
        /** id */
        public static final String ID = "id";
        /** token */
        public static final String TOKEN = "token";
        /** 标题 */
        public static final String TITLE = "title";
        /** 索引 */
        public static final String INDEX = "index";
        /** 位置 */
        public static final String POSITION = "position";
        /** 状态 */
        public static final String STATUS = "status";
        /** 类型 */
        public static final String TYPE = "type";
        /** 订单 */
        public static final String ORDER = "order";
        /** 余额 */
        public static final String BALANCE = "balance";
        /** 时间 */
        public static final String TIME = "time";
        /** 代码 */
        public static final String CODE = "code";
        /** URL */
        public static final String URL = "url";
        /** 路径 */
        public static final String PATH = "path";
        /** 数量 */
        public static final String AMOUNT = "amount";
        /** 总数 */
        public static final String COUNT = "count";
        /** 标记 */
        public static final String FLAG = "flag";
        /** 其他 */
        public static final String OTHER = "other";

        // 个人信息

        /** 姓名 */
        public static final String NAME = "name";
        /** 年龄 */
        public static final String AGE = "age";
        /** 性别 */
        public static final String SEX = "sex";
        /** 手机 */
        public static final String PHONE = "phone";
        /** 密码 */
        public static final String PASSWORD = "password";
        /** 会员 */
        public static final String VIP = "vip";
        /** 描述 */
        public static final String DESCRIBE = "describe";
        /** 备注 */
        public static final String REMARK = "remark";
        /** 星座 */
        public static final String CONSTELLATION = "constellation";

        // 地方

        /** 地址 */
        public static final String ADDRESS = "address";
        /** 省 */
        public static final String PROVINCE = "province";
        /** 市 */
        public static final String CITY = "city";
        /** 区 */
        public static final String AREA = "area";

        // 文件类型相关

        /** 文件 */
        public static final String FILE = "file";
        /** 文本 */
        public static final String TEXT = "text";
        /** 图片 */
        public static final String IMAGE = "picture";
        /** 音频 */
        public static final String VOICE = "voice";
        /** 视频 */
        public static final String VIDEO = "video";

        // 支付相关

        /** 余额支付 */
        public static final String BALANCE_PAY = "balance_pay";
        /** 微信支付 */
        public static final String WECHAT_PAY = "wechat_pay";
        /** 支付宝支付 */
        public static final String ALI_PAY = "ali_pay";
        /** 银联支付 */
        public static final String UNION_PAY = "union_pay";
        /** CrashHandler */
        public static final String EXCEPTION_CRASH = "exceptionOfCrash";

    }

}
