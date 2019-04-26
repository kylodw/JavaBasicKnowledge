package com.kylodw.bitmap.testhttp.http;

import android.content.Context;

import java.util.Map;

/**
 * @author kylodw
 * @description: 网络
 * @date 2019/04/25
 */
public interface IHttpEngine {

    void get(Context context, String url, Map<String, String> mHeaders, Map<String, Object> params, EngineCallBack engineCallBack);

    void post(Context context, String url, Map<String, String> mHeaders, Map<String, Object> params, EngineCallBack engineCallBack);

    //下载文件

    //上传文件

    //https添加证书
}
