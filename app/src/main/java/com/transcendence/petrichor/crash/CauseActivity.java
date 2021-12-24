package com.transcendence.petrichor.crash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;

import java.util.List;

public class CauseActivity extends PetrichorBaseActivity {

    private List<String> list;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_cause;
    }

    @Override
    protected void initView() {
        Button mBtn = findViewById(R.id.mBtn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = list.size();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
