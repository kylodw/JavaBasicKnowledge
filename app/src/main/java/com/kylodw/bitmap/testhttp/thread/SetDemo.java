package com.kylodw.bitmap.testhttp.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class SetDemo {

    public static void main(String[] args) {
        //底层还是CopyOnWriteArrayList
        Set<String> strings = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                strings.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(strings);
            }).start();
        }
        //底层就是HashMap
        new ConcurrentHashMap<>();
        //HashSet为什么是一个参数  HashMap是两个？  map.put(e, PRESENT)  只有key value是恒定的一个Object的常量
        new HashSet<>();
    }
}
