package com.kylodw.bitmap.testhttp.http;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kylodw.bitmap.testhttp.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoHttp(View view) {
//        HttpUtils.with(this).url("https://github.com/kylodw/JavaBasicKnowledge/blob/master/app/src/main/java/com/kylodw/bitmap/testhttp/lock/SpinLock.java")
//                .addHeader("Range", "byte=0-1024")
//                .get().execute(new EngineCallBack() {
//            @Override
//            public void onError(Exception e) {
//                Log.e("HTTP", e.toString());
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                Log.e("HTTP", result);
//            }
//        });

        HttpUtils.with(this).url("https://www.hmg100.com/hmcsapi/version/get_version_new")
                .addParam("no", "3.4.1")
//                .addParam("touxiang",new File(Environment.getExternalStorageDirectory(),"kylodw.jpg"))
                .post().execute(new EngineCallBack() {
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(String result) {
                Log.e("HTTP", result);
            }
        });
    }
}
