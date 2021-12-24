package com.transcendence.petrichor.pic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;

import com.trancesdence.utils.DensityUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.mine.activity.LuckyPanelActivity;
import com.transcendence.ui.image.dragimage.util.PicDragImageBitmapUtil;
import com.transcendence.ui.image.dragimage.view.PicDragImageView;

import java.io.InputStream;

public class DragImageActivity extends PetrichorBaseActivity {
	// 控件宽度
    private int window_width, window_height;
	// 自定义控件
    private PicDragImageView ivDrag;
	// 状态栏的高度
    private int state_height;

    private ViewTreeObserver viewTreeObserver;

    public static void start(Context context) {
        Intent intent = new Intent(context, DragImageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_dragimage;
    }

    @Override
    protected void initView() {
		/** 获取可見区域高度 **/
        window_width = DensityUtils.getScreenW(mActivity);
        window_height = DensityUtils.getScreenH(mActivity);

        ivDrag = (PicDragImageView) findViewById(R.id.ivDrag);
        Bitmap bmp = PicDragImageBitmapUtil.ReadBitmapById(this, R.mipmap.tudor_rose_one,          //pic_dragimage_huoying
                window_width, window_height);
        // 设置图片
        ivDrag.setImageBitmap(bmp);
        ivDrag.setmActivity(this);//注入Activity.
        /** 测量状态栏高度 **/
        viewTreeObserver = ivDrag.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (state_height == 0) {
                            // 获取状况栏高度
                            Rect frame = new Rect();
                            getWindow().getDecorView()
                                    .getWindowVisibleDisplayFrame(frame);
                            state_height = frame.top;
                            ivDrag.setScreen_H(window_height - state_height);
                            ivDrag.setScreen_W(window_width);
                        }

                    }
                });
    }

    @Override
    protected void initData() {
        setTitle("DragImage");
        setBackVisibility();
    }


    /**
     * 读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmapById(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}