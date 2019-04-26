package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kylodw
 * @date 2019/04/26
 * 自旋锁
 * 尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，
 * 好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 */
public class SpinLock {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> {
            try {
                spinLock.myLock();
                Thread.sleep(5000);
                spinLock.unLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();
        Thread.sleep(1000);


        new Thread(() -> {
            try {
                spinLock.myLock();
                Thread.sleep(5000);
                spinLock.unLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-2").start();
    }

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t 进入加锁");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + "\t 我在等锁");
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        //解锁
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t 解锁");
    }
}
