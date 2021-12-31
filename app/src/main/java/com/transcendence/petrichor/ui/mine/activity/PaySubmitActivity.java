package com.transcendence.petrichor.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.ui.setting.activity.MultiSettingActivity;
import com.transcendence.ui.checkbox.SmoothCheckBox;
import com.transcendence.ui.textview.TimeCountDownView;

import java.util.ArrayList;

/**
 * @Author Joephone on 2021/12/31 0031 下午 3:04
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class PaySubmitActivity extends PetrichorBaseActivity implements SmoothCheckBox.OnSmoothCheckedChangeListener, TimeCountDownView.OnTimeCompleteListener
        , View.OnClickListener {

//    @BindView(R2.id.ll_submit_zhifubao)
    LinearLayout mLlSubmitZhifubao;
//    @BindView(R2.id.ll_submit_showzhifubao)
    LinearLayout mLlSubmitShowzhifubao;

    SmoothCheckBox mCbMeituanPay;
    SmoothCheckBox mCbWeixinPay;
    SmoothCheckBox mCbQqPay;
    SmoothCheckBox mCbAlipay;
    TimeCountDownView mTvPayLeftTime;
    TextView mTvPayMoney;

    private ArrayList<SmoothCheckBox> payList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_submit;
    }

    @Override
    protected void initView() {
        mTvPayMoney = findViewById(R.id.tv_submit_money);
        mTvPayLeftTime = findViewById(R.id.tv_submit_shengyu_show);
        mCbMeituanPay = findViewById(R.id.cb_submit_meituan);
        mCbWeixinPay = findViewById(R.id.cb_submit_weixin);
        mCbQqPay = findViewById(R.id.cb_submit_qq);
        mCbAlipay = findViewById(R.id.cb_submit_alipay);
        mLlSubmitZhifubao = findViewById(R.id.ll_submit_zhifubao);
        mLlSubmitZhifubao.setOnClickListener(this);
        mLlSubmitShowzhifubao = findViewById(R.id.ll_submit_showzhifubao);
        mLlSubmitShowzhifubao.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(getString(R.string.pay));
        setBackVisibility();
        //支付方式
        initPayCheckBox();

        //订单支付剩余时间15min开始倒计时
        initPayLeftTime();

        //支付金额
        final SpannableString paymoney = new SpannableString("￥24.8");
        paymoney.setSpan(new AbsoluteSizeSpan(20, true), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvPayMoney.setText(paymoney);
    }

    private void initPayLeftTime() {
        mTvPayLeftTime.initTime(15, 0);
        mTvPayLeftTime.start();
        mTvPayLeftTime.setOnTimeCompleteListener(this);
    }

    private void initPayCheckBox() {
        payList.add(mCbMeituanPay);
        payList.add(mCbWeixinPay);
        payList.add(mCbQqPay);
        payList.add(mCbAlipay);

        for (SmoothCheckBox tempBox : payList) {
            tempBox.setOnSmoothCheckedChangeListener(this);
        }
    }

    @Override
    public void onSmoothCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        if (isChecked) {
            for (SmoothCheckBox box : payList) {
                if (box.getId() == checkBox.getId()) {
                    box.setChecked(true);
                } else {
                    box.setChecked(false);
                }
            }
        }
    }


    @Override
    public void onTimeComplete() {
//        getSupportDelegate().pop();
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, PaySubmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_submit_showzhifubao:
                mLlSubmitZhifubao.setVisibility(View.VISIBLE);
                mLlSubmitShowzhifubao.setVisibility(View.GONE);
            break;
        }
    }
}
