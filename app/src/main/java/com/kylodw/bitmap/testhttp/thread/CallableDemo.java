package com.kylodw.bitmap.testhttp.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        //同样的结果会复用
        //多个线程启动FutureTask  只有一个结果
        //多个线程启动多个FutureTask  会有多个结果
        FutureTask<Integer> future=new FutureTask<>(new MyThread2());
        new Thread(future).start();
        try {
            future.run();
            Thread.sleep(1000);

            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
    }
    static class MyThread implements Runnable{

        @Override
        public void run() {

        }
    }
    static class MyThread2 implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 1024;
        }
    }
}
