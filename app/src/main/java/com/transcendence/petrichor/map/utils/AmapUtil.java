package com.transcendence.petrichor.map.utils;

import android.content.Context;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * @author Joephone on 2019/11/7 17:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AmapUtil extends AmapHelper {

    public AmapUtil(AMap paramAmap, MapView aMapView, Context context){
        this.aMap = paramAmap;
        this.mContext = context;
        this.aMapView = aMapView;
        initHelper();
    }




}
