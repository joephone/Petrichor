package com.transcendence.petrichor.ui.setting.eventbus;

import java.util.Locale;

/**
 * @Author Joephone on 2021/12/10 0010 下午 12:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class LanguageEvent {

    private Locale mLocate;
    public LanguageEvent(Locale locate) {
        // TODO Auto-generated constructor stub
        mLocate = locate;
    }

    public Locale getLocate() {
        return mLocate;
    }
}
