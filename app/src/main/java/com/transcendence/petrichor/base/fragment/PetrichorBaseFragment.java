package com.transcendence.petrichor.base.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.transcendence.core.base.BaseFragment;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.action.ToastAction;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author Joephone on 2021/12/2 0002 下午 1:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class PetrichorBaseFragment<A extends PetrichorBaseActivity> extends BaseFragment
        implements ToastAction {

    protected Disposable disposable;
    protected boolean isBackVisible = true;


    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    protected void setBackVisibility() {
        FrameLayout fl_back = (FrameLayout) findViewById(R.id.fl_back);
        if (isBackVisible) {
            fl_back.setVisibility(View.VISIBLE);
            fl_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBack(view);
                }
            });
        } else {
            fl_back.setVisibility(View.INVISIBLE);
        }

    }

    private void clickBack(View view) {
        finish();
    }

}
