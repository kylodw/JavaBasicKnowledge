package com.kylodw.bitmap.testhttp.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
    }

    private static void test3() {
        Random random = new Random();
        Event[] events = new Event[]{new Event(1), new Event(2)};


    }

    static class SourceTask implements Runnable {
        private Table table;

        public SourceTask(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            table.sourceCount = 10;
        }
    }

    static class Event {
        private int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> tables = new ArrayList<>();
        Table table = null;
        for (int i = 0; i < 10; i++) {
            table = new Table("table_" + event.id + "_" + i);
            tables.add(table);
        }
        return tables;
    }

    static class Table {
        String tableName;
        long sourceCount = 10;
        String columnSchema = "<table name='a'><column name='coll' type='varchar2'></table>";

        public Table(String tableName) {
            this.tableName = tableName;
        }
    }

    /**
     * 协同工作
     */
    private static void test2() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("处理事情");
                try {
                    Thread.sleep(1000);
                    countDownLatch.await();
                    System.out.println("处理完成，处理其他");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("准备数据");
                try {
                    Thread.sleep(2000);
                    System.out.println("准备完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }

            }
        }).start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 并行处理
     */
    private static void test() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
//        countDownLatch.
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 进入");
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t最后一个关闭");
    }

    /**
     * 枚举类
     */
    public enum CountryEnum {
        ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕");
        private int code;
        private String message;

        CountryEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public static CountryEnum for_each(int index) {
            CountryEnum[] countryEnums = CountryEnum.values();
            for (CountryEnum countryEnum : countryEnums) {
                if (index == countryEnum.code) {
                    return countryEnum;
                }
            }
            return null;
        }
    }
}
