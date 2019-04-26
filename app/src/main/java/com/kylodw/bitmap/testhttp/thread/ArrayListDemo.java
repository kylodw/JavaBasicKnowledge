package com.kylodw.bitmap.testhttp.thread;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());
//       ArrayList并发异常 java.util.ConcurrentModificationException

        CopyOnWriteArrayList<Integer> integers=new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                integers.add(new Random().nextInt(1000));
                System.out.println(integers);
            }).start();
        }

        /**
         * 故障现象
         *  ArrayList并发异常 java.util.ConcurrentModificationException
         *
         * 导致原因
         *
         * 解决方案
         * Vector
         * Collections.synchronizedList(new ArrayList<>())
         * CopyOnWriteArrayList(写时复制) 读写分离，扩容一个
         *
         * 优化建议
         */

    }
}
