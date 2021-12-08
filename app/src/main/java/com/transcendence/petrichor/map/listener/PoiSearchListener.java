package com.transcendence.petrichor.map.listener;


import com.amap.api.services.poisearch.PoiResult;

/**
 * @author Joephone on 2019/11/22 14:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface PoiSearchListener {

    /**
     * 得到地址列表
     * @param result
     */
    void onPoiSearchList(PoiResult result);

}
