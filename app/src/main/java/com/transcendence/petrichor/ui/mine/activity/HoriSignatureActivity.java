package com.transcendence.petrichor.ui.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import com.hjq.toast.ToastUtils;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.ui.widget.signature.HoriPaintView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2017/12/11.
 * 横向签名
 */

public class HoriSignatureActivity extends PetrichorBaseActivity {

    HoriPaintView paintView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signature_hori;
    }

    @Override
    protected void initView() {
        paintView = findViewById(R.id.paint_view);
        findViewById(R.id.ibBack).setOnClickListener(this);
        findViewById(R.id.ibClean).setOnClickListener(this);
        findViewById(R.id.ibSend).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibBack:
                finish();
                break;
            case R.id.ibClean:
                paintView.clear();
                break;
            case R.id.ibSend:
                boolean paint = paintView.isPaint();
                if (paint) {
                    Bitmap cacheBitmap = paintView.getCacheBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                } else {
                    ToastUtils.show("请签名");
                }
                break;
        }
    }

}
