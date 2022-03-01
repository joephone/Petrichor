package com.transcendence.petrichor.ui.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.ui.widget.signature.PaintView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2017/8/28.
 * 医生签名
 */

public class SignatureActivity extends PetrichorBaseActivity implements View.OnClickListener{


    PaintView paintView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_signature;
    }

    @Override
    protected void initView() {
        findViewById(R.id.tvHori).setOnClickListener(this);
        findViewById(R.id.tvClean).setOnClickListener(this);
        paintView = findViewById(R.id.paint_view);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int top = paintView.getTop();
        int bottom = paintView.getBottom();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvClean:
                paintView.clear();
                break;
            case R.id.tvHori:
                SignatureActivity.start(HoriSignatureActivity.class);
                break;
            case R.id.tvSend:
                boolean paint = paintView.isPaint();
                if (paint) {
                    Bitmap cacheBitmap = paintView.getCachebBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bitmapByte = baos.toByteArray();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode== Global.REQUEST_CODE.HORI_SIGNATURE &&resultCode==Global.RESULT_CODE.MESSION_FINISH){
//            byte[] bitmapByte = data.getByteArrayExtra(Global.PUBLIC_INTENT_KEY.DATA);
//            upSignature(bitmapByte);
//        }
    }




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
