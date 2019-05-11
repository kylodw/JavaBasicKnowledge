package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/05
 * 限时等待锁
 */
public class TimeLock implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName()+"\t 获取锁成功");
            } else {

                System.out.println(Thread.currentThread().getName()+"\t 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock, "thread_1");
        Thread t2 = new Thread(timeLock, "thread_2");
        t1.start();
        t2.start();
    }
}
