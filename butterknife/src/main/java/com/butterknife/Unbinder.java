package com.butterknife;

import android.support.annotation.UiThread;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
public interface Unbinder {
    @UiThread
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override public void unbind() { }
    };
}
