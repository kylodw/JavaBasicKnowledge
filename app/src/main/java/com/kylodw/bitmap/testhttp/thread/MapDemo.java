package com.kylodw.bitmap.testhttp.thread;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> stringStringMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                stringStringMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(stringStringMap);
            }).start();
        }
    }
}
