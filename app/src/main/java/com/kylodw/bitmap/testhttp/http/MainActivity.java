package com.kylodw.bitmap.testhttp.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kylodw.bitmap.testhttp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoHttp(View view) {
        HttpUtils.with(this).url("https://space.bilibili.com/97139894/dynamic")
                .addParam("kylodw", "123")
                .addParam("321", "564")
                .get().execute(new EngineCallBack() {
            @Override
            public void onError(Exception e) {
                Log.e("HTTP", e.toString());
            }

            @Override
            public void onSuccess(String result) {
                Log.e("HTTP", result);
            }
        });
    }
}
