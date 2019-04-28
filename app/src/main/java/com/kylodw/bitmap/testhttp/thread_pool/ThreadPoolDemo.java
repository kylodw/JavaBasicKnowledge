package com.kylodw.bitmap.testhttp.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool(3);//固定数量的线程
//        Executors.newSingleThreadExecutor();//单线程
//        Executors.newCachedThreadPool();//多线程

//        ExecutorService executorService=Executors.newFixedThreadPool(5);
//        ExecutorService executorService=Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         *
         * 核心线程数满了->加入阻塞队列-> 阻塞队列也满了->开始普通线程的创建->所有线程都在运行和阻塞队列也满了->开始拒绝策略
         *
         * 1，核心线程数（常驻线程数）
         * 达到最大核心线程数时，会加入缓存队列中
         *
         * 2，最大线程数，必须大于等于1（包含核心线程数？）
         * 3，存活时间   只有线程池中的数大于核心线程时才有用
         * 4，时间单位
         * 5，阻塞队列
         * 6，线程工厂
         * 7，拒绝策略  默认 抛异常阻止系统正常运行
         * CallerRunsPolicy 不会抛弃任务 也不会抛异常
         * DiscardOldestPolicy 抛弃等待最久的任务
         * DiscardPolicy 直接丢弃任务
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                , Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        /*
         * 决定最大线程数
         * io 密集型
         * - 核数*2
         * - cpu核数/1-阻塞系数（0.8-0.9）之间
         *
         * cpu密集型   cpu核数+1个线程
         *
         */
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3)/*最大值为int_max*/,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i <= 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
