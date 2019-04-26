package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.Semaphore;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 信号量
 * 多个线程抢多份资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //3个资源
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢占到资源");
                    Thread.sleep(3000);
                    System.out.println("释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            }, "thread-" + i).start();
        }
    }
}
