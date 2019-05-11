package com.kylodw.bitmap.testhttp;

import android.app.Application;

import com.kylodw.annotains.WXPayEntry;
import com.kylodw.kylodw.pay.wxapi.BaseWXPayActivity;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/11
 */
@WXPayEntry(packageName = "com.kylodw.bitmap.testhttp", entryCass = BaseWXPayActivity.class)
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
