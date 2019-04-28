package com.kylodw.bitmap.testhttp.lock;


/**
 * 死锁是指两个或者两个以上的线程在执行过程中，因争夺共享资源
 * 排查死锁问题
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="locka";
        String lockB="lockb";
        //jps -l 查看进程编号
        //jstack 22208 查看堆栈信息是否有死锁
        new Thread(new HoldLockThread(lockA,lockB),"thread_a").start();

        new Thread(new HoldLockThread(lockB,lockA),"thread_b").start();

        System.out.println("结束");

    }
    static class HoldLockThread implements Runnable{
        private String LockA;
        private String LockB;

        public HoldLockThread(String lockA, String lockB) {
            LockA = lockA;
            LockB = lockB;
        }

        @Override
        public void run() {
            synchronized (LockA){
                System.out.println(Thread.currentThread().getName()+"\t 持有，尝试获取别的"+LockA+"\t"+LockB);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (LockB){
                    System.out.println(Thread.currentThread().getName()+"\t 自己持有，尝试获取别的"+LockA+"\t"+LockB);
                }
            }
        }
    }
}
