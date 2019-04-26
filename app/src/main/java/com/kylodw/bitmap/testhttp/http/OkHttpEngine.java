package com.kylodw.bitmap.testhttp.http;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/25
 */
public class OkHttpEngine implements IHttpEngine {
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void get(Context context, String url, Map<String, String> mHeaders, Map<String, Object> params, final EngineCallBack engineCallBack) {
        final String joinUrl = HttpUtils.joinParams(url, params);
        Log.e("OkHttpEngine", joinUrl);
        Request.Builder builder = new Request.Builder().url(url).tag(context);
        builder.method("GET", null);
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                engineCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                engineCallBack.onSuccess(response.body().string());
            }
        });
    }


    @Override
    public void post(Context context, String url, Map<String, String> mHeaders, Map<String, Object> params, final EngineCallBack engineCallBack) {
        RequestBody requestBody = appendBody(params);
        Request.Builder builder = new Request.Builder()
                .url(url)
                .tag(context)
                .post(requestBody);
        if (mHeaders != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addHeader(entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                engineCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //都不是在主线程中
                if (response.body() != null) {
                    engineCallBack.onSuccess(response.body().string());
                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() instanceof File) {

            }
            if (entry.getValue() instanceof String) {

            }
        }
        String value = null;
        return parseRequestBody(value);
    }

    /**
     * body 文本格式
     *
     * @param value
     * @return
     */
    public static RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    /**
     * body图片格式
     *
     * @param file
     * @return
     */
    public static RequestBody parseImageRequestBody(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }
}
