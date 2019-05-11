package com.kylodw.bitmap.testhttp.泛型;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
public interface HttpCallBack<T> {
    void onSuccess(T t);
}

class main {
    public <T> void add(T t) {

    }


    public static void main(String[] args) {
        new HttpCallBack<String>() {
            @Override
            public void onSuccess(String s) {

            }
        };
    }
}
