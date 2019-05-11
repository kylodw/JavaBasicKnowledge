package com.kylodw.bitmap.testhttp.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;


import java.util.Map;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/25
 */
public class HttpUtils {
    /**
     * 默认okhttp
     */
    private static IHttpEngine mHttpEngine = new OkHttpEngine();
    /**
     * 请求地址
     */
    private String mUrl;
    /**
     * 默认为post请求
     */
    private int mType = POST_TYPE;
    private static final int POST_TYPE = 0x0011;
    private static final int GET_TYPE = 0x0012;
    /**
     * 上下文环境
     */
    private Context mContext;
    private Map<String, Object> mParmas;
    private Map<String, String> mHeaders;

    private HttpUtils(Context mContext) {
        this.mContext = mContext;
        mParmas = new ArrayMap<>();
        mHeaders = new ArrayMap<>();
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }


    public HttpUtils post() {
        mType = POST_TYPE;
        return this;
    }

    public HttpUtils get() {
        mType = GET_TYPE;
        return this;
    }

    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    //还需要请求头
    public HttpUtils addHeader(String key, String value) {
        mHeaders.put(key, value);
        return this;
    }

    public HttpUtils addParam(String key, Object value) {
        mParmas.put(key, value);
        return this;
    }

    public HttpUtils addParams(Map<String, Object> parmas) {
        mParmas.putAll(parmas);
        return this;
    }

    public void execute(EngineCallBack callBack) {
        if (callBack == null) {
            callBack = EngineCallBack.DEFAULT_CALL_BACK;
        }
        if (mType == POST_TYPE) {
            post(mUrl,mHeaders, mParmas, callBack);
        }
        if (mType == GET_TYPE) {
            get(mUrl,mHeaders, mParmas, callBack);
        }
    }

    public void execute() {
        execute(null);
    }

    /**
     * 初始化引擎
     *
     * @param httpEngine
     */
    public static void init(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    /**
     * 改变引擎
     *
     * @param httpEngine
     */
    public void exchangeHttpEngine(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    private void get(String url, Map<String, String> mHeaders, Map<String, Object> params, EngineCallBack engineCallBack) {
        mHttpEngine.get(mContext, url, mHeaders,params, engineCallBack);
    }

    private void post(String url, Map<String, String> mHeaders, Map<String, Object> params, EngineCallBack engineCallBack) {
        mHttpEngine.post(mContext, url, mHeaders,params, engineCallBack);
    }

    public static String joinParams(String url, Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        int i = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            i++;
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            if (i != params.size()) {
                stringBuilder.append("&");
            }
            Log.e("OkHttpEngine", "" + i + "   size:" + params.size());
        }
        return stringBuilder.toString();
    }
}
