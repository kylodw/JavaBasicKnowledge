package com.kylodw.bitmap.testhttp.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 使用阻塞队列实现生产者消费者
 */
public class ProducerConsumerQueueDemo {
    public static void main(String[] args) {
        SharedData sharedData=new SharedData(new ArrayBlockingQueue<>(10));

        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedData.myProd();
            }
        },"thread_prod1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedData.myProd();
            }
        },"thread_prod2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedData.myConsumer();
            }
        },"thread_consumer").start();



    }

    static class SharedData {
        private volatile boolean flag = true;
        private AtomicInteger atomicInteger = new AtomicInteger();

        BlockingQueue<String> blockingQueue = null;
        //传接口
        public SharedData(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
            System.out.println(blockingQueue.getClass().getName());
        }
        public void stop(){
            this.flag=false;
        }
        public void myProd(){
            String data=null;
            boolean returnValue=false;
            while (flag){
                data=atomicInteger.incrementAndGet()+"";//++i
                try {
                    returnValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(returnValue){
                    System.out.println(Thread.currentThread().getName()+"\t 插入成功");
                }else{
                    System.out.println(Thread.currentThread().getName()+"\t 插入失败");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产结束");
        }



        public void myConsumer(){
            String data=null;
            while (flag){
                try {
                    data=blockingQueue.poll(2L,TimeUnit.SECONDS);
                    if( null==data &&data.equalsIgnoreCase("")){
                        flag=false;
                        System.out.println("消费结束");
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t 消费队列成功");
            }
        }

    }
}
