package com.transcendence.core.global;

/**
 * @Author Joephone on 2021/10/30 0030 下午 4:23
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public enum MapType {
    GAODE(0),
    GOOGLE(1);

    private final int value;

    MapType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
