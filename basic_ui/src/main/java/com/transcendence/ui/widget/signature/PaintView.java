package com.transcendence.ui.widget.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2017/8/29.
 *  签名画布
 */

public class PaintView extends View {
    private Paint paint;
    private Canvas cacheCanvas;
    private Bitmap cachebBitmap;
    private Path path;
    private int pHeight,pWidth;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Bitmap getCachebBitmap() {
        return cachebBitmap;
    }



    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        path = new Path();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;


        cachebBitmap = Bitmap.createBitmap(displayWidth-30, 600, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(cachebBitmap);
        cacheCanvas.drawColor(Color.WHITE);

    }

    public void clear() {
        if (cacheCanvas != null) {
            cur_x = 0;
            cur_y = 0;
            cacheCanvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            cacheCanvas.drawColor(Color.WHITE);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawColor(BRUSH_COLOR);
        canvas.drawBitmap(cachebBitmap, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
        int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
        if (curW >= w && curH >= h) {
            return;
        }

        if (curW < w){
            curW = w;}
        if (curH < h){
            curH = h;}

        Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas();
        newCanvas.setBitmap(newBitmap);
        if (cachebBitmap != null) {
            newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
        }
        cachebBitmap = newBitmap;
        cacheCanvas = newCanvas;
    }

    private float cur_x, cur_y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                cur_x = x;
                cur_y = y;
                //超出部分  去掉
//                if (y >= 0 && y <= 600) {
                    path.moveTo(cur_x, cur_y);
//                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
//                if (y >= 0 && y <= 600) {
                    path.quadTo(cur_x, cur_y, x, y);
//                }
                cur_x = x;
                cur_y = y;
                break;
            }
            case MotionEvent.ACTION_UP: {
//                if (y >= 0 && y <= 600) {
                    cacheCanvas.drawPath(path, paint);
//                }
                path.reset();
                break;
            }
        }
//        Logs.logI("x:"+x);
//        Logs.logI("y:"+y);
//        Logs.logI("cur_x:"+cur_x);
//        Logs.logI("cur_y:"+cur_y);
        invalidate();
        return true;
    }

    /**
     * 判断是否签名
     *
     * @return
     */
    public boolean isPaint() {
        if (0 == cur_x || 0 == cur_y) {
            return false;
        } else {
            return true;
        }
    }

    public void setHeight(int height) {
        this.pHeight = height;
    }

    public void setWidth(int width) {
        this.pWidth = width;
    }
}
