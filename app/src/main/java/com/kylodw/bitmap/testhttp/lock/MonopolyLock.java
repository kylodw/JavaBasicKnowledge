package com.kylodw.bitmap.testhttp.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * <p>
 * 独占  ReentrantLock   synchronized 都是独占锁
 * <p>
 * ReentrantReadWriteLock   读：共享锁   写：独占锁
 */
public class MonopolyLock {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> myCache.put("" + finalI, "" + finalI), "thread_" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> myCache.get("" + finalI), "thread_" + i).start();
        }
    }

    /**
     * 多线程操作资源类
     */
    static class MyCache {
        private volatile Map<String, Object> map = new HashMap<>();
        //可重入读写锁
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //        private Lock lock = null;
        /* 写操作：原子+独占 */
        void put(String key, Object value) {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " \t 正在写入。。。。" + key);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t 写入完成。。。。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
        void get(String key) {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " \t 正在读取。。。。" + key);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object result = map.get(key);
                System.out.println(Thread.currentThread().getName() + " \t读取完成：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

        public void clear() {
            map.clear();
        }
    }
}
