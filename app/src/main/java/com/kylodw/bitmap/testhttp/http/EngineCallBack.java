package com.kylodw.bitmap.testhttp.http;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/25
 */
public interface EngineCallBack {
    /**
     * 错误回调
     *
     * @param e
     */
    public void onError(Exception e);

    /**
     * 成功
     *
     * @param result
     */
    public void onSuccess(String result);

    public final EngineCallBack DEFAULT_CALL_BACK = new EngineCallBack() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };
}
