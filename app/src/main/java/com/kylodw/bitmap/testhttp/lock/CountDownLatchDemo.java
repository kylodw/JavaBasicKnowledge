package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.code);
        System.out.println(CountryEnum.ONE.message);
    }

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
