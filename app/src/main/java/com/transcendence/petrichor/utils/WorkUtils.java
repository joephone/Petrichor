package com.transcendence.petrichor.utils;

import android.app.Activity;
import android.content.Intent;

import com.transcendence.petrichor.pic.activity.ImagePagerActivity;

/**
 * @Author Joephone on 2022/2/28 0028 下午 5:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class WorkUtils {

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    public static void imageBrower(Activity myActivity, int position, String[] urls2) {
        Intent intent = new Intent(myActivity, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        myActivity.startActivity(intent);
    }
}
