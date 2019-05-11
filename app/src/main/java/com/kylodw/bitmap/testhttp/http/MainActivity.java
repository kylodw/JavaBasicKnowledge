package com.kylodw.bitmap.testhttp.http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.butterknife.ButterKnife;
import com.butterknife.Unbinder;
import com.butterknife.annotation.KBindView;
import com.kylodw.bitmap.testhttp.R;
import com.kylodw.bitmap.testhttp.point.CheckNet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    @KBindView(R.id.btn_test)
    Button mBtnTest;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
//        MainActivity_ViewBinding mainActivity_viewBinding = new MainActivity_ViewBinding(this);
        mBtnTest.setText("kylodw");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private <T> T viewfind(int viewId) {
        return (T) findViewById(viewId);
    }

    /**
     * 设置上限  他和他的子类
     *
     * @param clazz
     */
    public void startActivity(Class<? extends Activity> clazz) {
        Intent it = new Intent(this, clazz);
        startActivity(it);
    }

    /**
     * 设置下限  他和他的父类
     *
     * @param clazz
     */
    public void startActivitys(Class<? super MainActivity> clazz) {
        Intent it = new Intent(this, clazz);
        startActivity(it);
    }

    public void gotoIPC(View view) {


    }

    @CheckNet
    public void gotoHttp(View view) {
        AssetManager assetManager;
        try {
            assetManager = AssetManager.class.newInstance();
            @SuppressLint("PrivateApi")
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, "sdcard/..");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


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

//        HttpUtils.with(this).url("https://www.hmg100.com/hmcsapi/version/get_version_new")
//                .addParam("no", "3.4.1")
////                .addParam("touxiang",new File(Environment.getExternalStorageDirectory(),"kylodw.jpg"))
//                .post().execute(new EngineCallBack() {
//            @Override
//            public void onError(Exception e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                Log.e("HTTP", result);
//            }
//        });
    }


}
