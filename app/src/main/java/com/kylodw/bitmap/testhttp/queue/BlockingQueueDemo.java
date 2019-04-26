package com.kylodw.bitmap.testhttp.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author kylodw
 * @date 2019/04/26
 * <p>
 * 阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-2").start();
    }

    private static void arrayBlockingMethod() {
        //阻塞队列接口
//        BlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        //队列先进先出
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //add  队列满的时候 抛异常   remove  队列为空的时候 抛异常
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//
//        System.out.println(blockingQueue.element());
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        /*
         * offer   成功true  失败false
         * poll  成功有值   失败为null
         */
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));
//
//        System.out.println(blockingQueue.peek());
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

        /*
         *  put 队列满了直接阻塞
         *  take  消费
         */
//        try {
//            blockingQueue.put("a");
//            blockingQueue.put("b");
//            blockingQueue.put("c");
//            System.out.println("====================================");
//            blockingQueue.put("d");//这里超了 一直阻塞
//
////            blockingQueue.take();
////            blockingQueue.take();
////            blockingQueue.take();
////            blockingQueue.take();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
//            System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
//            System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
//            System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS)); //超过了  阻塞两秒钟
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//            blockingQueue.put("b");
//            blockingQueue.put("c");
    }
}
