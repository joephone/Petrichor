package com.transcendence.petrichor.base.activity;

import com.transcendence.core.base.BaseActivity;
import com.transcendence.petrichor.R;

/**
 * @Author Joephone on 2021/12/2 0002 上午 9:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class PetrichorBaseActivity extends BaseActivity {


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }


}
