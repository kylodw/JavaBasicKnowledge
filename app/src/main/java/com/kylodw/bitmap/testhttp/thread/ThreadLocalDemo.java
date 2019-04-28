package com.kylodw.bitmap.testhttp.thread;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/28
 */
public class ThreadLocalDemo {
    //不设初始值  默认为null
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            threadLocal.set("thread_1");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "\t get:" + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set("thread_2");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "\t get:" + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(threadLocal.get());
    }

}
